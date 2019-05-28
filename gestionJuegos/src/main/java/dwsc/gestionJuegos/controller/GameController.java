package dwsc.gestionJuegos.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dwsc.gestionJuegos.domain.Game;
import dwsc.gestionJuegos.repository.GameRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class GameController 
{
	@Autowired
	GameRepository gameRepo;
	
	/**
	 * GET LIST OF ALL GAMES
	 * @return 200 IF FOUND GAMES, 
	 * 			404 IF NOT FOUND GAMES
	 */
	@GetMapping("/games")
	public ResponseEntity<List<Game>> findGames()
	{
		ArrayList<Game> game = new ArrayList<Game>(gameRepo.findAll());
		
		if(!game.isEmpty())
		{
			return ResponseEntity.ok(game);
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	
	/**
	 * GET A GAME BY NAME
	 * @param name
	 * @return 200 IF FOUND THE GAME, 
	 * 			404 IF NOT FOUND THE GAME
	 */
	@GetMapping("/games/name/{name}")
	public ResponseEntity<List<Game>> findGameByName(@PathVariable String name)
	{
		ArrayList<Game> game = new ArrayList<Game>(gameRepo.findByName(name));
		
		if(!game.isEmpty())
		{
			return ResponseEntity.ok(game);
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	
	/**
	 * GET A GAME BY ID
	 * @param id
	 * @return 200 IF FOUND THE GAME, 
	 * 			404 IF NOT FOUND THE GAME
	 */
	@GetMapping("/games/{id}")
	public ResponseEntity<Game> findGameById(@PathVariable int id)
	{
		Game game;
		
		try
		{
			game = gameRepo.findById(id).get();
			
		}catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(game);
		
	}
	
	/**
	 * ADD A GAME TO THE LIST OF GAMES
	 * @param game
	 * @return 201 IF THE GAME IS CREATED SUCCESFULLY, 
	 * 			202 IF THE GAME ALREADY EXISTS
	 */
	@PostMapping("/games")
	public ResponseEntity<Game> addGame(Game game) 
	{
		try
		{
			gameRepo.save(game);
			
		}catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
		}
		
		URI location  =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(game.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	/**
	 * EDIT THE INFORMATION OF A GAME
	 * @param game
	 * @param id
	 * @return 200 IF THE GAME IS EDITED SUCCESFULLY, 
	 * 			404 IF THERE'S NO GAME WITH THAT ID OR IF THE GAME ALREADY EXISTS
	 */
	@PutMapping("/games/{id}")
	public ResponseEntity<Game> editGameById(Game game, @PathVariable int id) 
	{
		Game dbGame;
		
		try
		{
			dbGame = gameRepo.findById(id).get();
			dbGame = game;
			gameRepo.save(dbGame);
			
		}catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(dbGame);
	}
	
	/**
	 * DELETE A GAME
	 * @param id
	 * @return 200 IF IT'S DELETED SUCCESFULLY,
	 * 			404 IF THERE'S NO GAME WITH THAT ID
	 */
	@DeleteMapping("/games/{id}")
	public ResponseEntity<Game> deleteGameById(@PathVariable int id) 
	{
		try
		{
			gameRepo.deleteById(id);
			
		}catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(null);
	}

}

