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
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PlayerController 
{
	@Autowired
	PlayerRepository playerRepo;
	
	@Autowired
	GameRepository gameRepo;
	
	
	/**
	 * GET FAVOURITE GAMES BY PLAYER ID
	 * @param id
	 * @return 200 IF FOUND THE PLAYER, 
	 * 			404 IF NOT FOUND THE PLAYER
	 */
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
	
	
	/**
	 * PUT A GAME AS FAVOURITE
	 * @param playerId
	 * @param game
	 * @return 201 IF PUT THE GAME SUCCESFULLY AS FAVOURITE,
	 * 			202 IF THE PLAYER OR GAME IS NOT FOUND.
	 */
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
	
	/**
	 * QUIT/DELETE GAME FROM FAVOURITE GAMES
	 * @param playerId
	 * @param gameId
	 * @return 200 IF DELETED SUCCESFULLY
	 * 			404 IF THE PLAYER OR GAME DOESN'T EXIST
	 */
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

