package dwsc.asignarJuegos.controller;


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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dwsc.asignarJuegos.domain.Game;
import dwsc.asignarJuegos.domain.Player;
import dwsc.asignarJuegos.repository.GameRepository;
import dwsc.asignarJuegos.repository.PlayerRepository;


@Controller
public class PlayerController 
{
	@Autowired
	PlayerRepository playerRepo;
	
	@Autowired
	GameRepository gameRepo;
		
	/*@RequestMapping("/players/{id}/games")
	public ResponseEntity<List<Player>> findFollowedGamesById(@PathVariable String name)
	{
		ArrayList<Player> player = new ArrayList<Player>(playerRepo.findByName(name));
		
		if(!player.isEmpty())
		{
			return ResponseEntity.ok(player);
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}*/
	
	//Get Favourite Games By Name
	@RequestMapping("/players/{name}/games")
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
		
	}
	
	/*//Get Favourite Games By Id
	@RequestMapping("/players/{id}/games")
	public ResponseEntity<Set<Game>> findFavouriteGamesById(@PathVariable int id)
	{
		if(playerRepo.findById(id).isPresent())
		{
			return ResponseEntity.ok(playerRepo.findById(id).get().getFavouriteGames());
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}*/
	
	@PostMapping("/players/{playerId}/addGame/{gameId}")
	public ResponseEntity<String> addFavouriteGameById(@PathVariable int playerId, @PathVariable int gameId, Map<String, Player> model) 
	{
		Player player;
		Game game;
		
		try
		{
			player = playerRepo.findById(playerId).get();
			game = gameRepo.findById(gameId).get();
			
			player.getFavouriteGames().add(game);
			playerRepo.save(player);
			
		}catch(NoSuchElementException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("playercreation");
		}
		
		
		model.put("player", player);
		return ResponseEntity.ok("playercreation");
	}
	
	@DeleteMapping("/players/{playerId}/deleteGame/{gameId}")
	public ResponseEntity<String> deleteFavouriteGameById(@PathVariable int playerId, @PathVariable int gameId, Map<String, Player> model) 
	{
		Player player;
		Game game;
		
		try
		{
			player = playerRepo.findById(playerId).get();
			game = gameRepo.findById(gameId).get();
			
			player.getFavouriteGames().remove(game);
			playerRepo.save(player);
			
		}catch(NoSuchElementException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("playercreation");
		}
		
		
		model.put("player", player);
		return ResponseEntity.ok("playercreation");
	}

}

