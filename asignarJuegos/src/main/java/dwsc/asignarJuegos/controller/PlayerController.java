package dwsc.asignarJuegos.controller;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dwsc.asignarJuegos.domain.Game;
import dwsc.asignarJuegos.domain.Player;
import dwsc.asignarJuegos.repository.GameRepository;
import dwsc.asignarJuegos.repository.PlayerRepository;


@RestController
public class PlayerController 
{
	@Autowired
	PlayerRepository playerRepo;
	
	@Autowired
	GameRepository gameRepo;
	
	/*//Get Favourite Games By Name
	@GetMapping("/players/{name}/games")
	public ResponseEntity<Set<Game>> findFavouriteGamesByName(@PathVariable String name)
	{
		ArrayList<Player> player = new ArrayList<Player>(playerRepo.findByName(name));
		
		if(!player.isEmpty())
		{
			return ResponseEntity.ok(player.get(0).getFavouriteGames());
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}*/
	
	//Get Favourite Games By Id
	@GetMapping("/players/{id}/games")
	public ResponseEntity<Set<Game>> findFavouriteGamesById(@PathVariable int id)
	{
		if(playerRepo.findById(id).isPresent())
		{
			return ResponseEntity.ok(playerRepo.findById(id).get().getFavouriteGames());
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	
	@PostMapping("/players/{playerId}/games")
	public ResponseEntity<Game> addFavouriteGameById(@PathVariable int playerId, Game game) 
	{
		Player player;
		
		try
		{
			player = playerRepo.findById(playerId).get();
			
			player.getFavouriteGames().add(game);
			playerRepo.save(player);
			
		}catch(NoSuchElementException e)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
		}
		
		URI location  =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{gameId}").buildAndExpand(game.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/players/{playerId}/games/{gameId}")
	public ResponseEntity<Game> deleteFavouriteGameById(@PathVariable int playerId, @PathVariable int gameId) 
	{
		Player player;
		Game game;
		
		try
		{
			player = playerRepo.findById(playerId).get();
			game = gameRepo.findById(gameId).get();
			
			if(!player.getFavouriteGames().remove(game))
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			playerRepo.save(player);
			
		}catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		
		return ResponseEntity.ok(null);
	}

}

