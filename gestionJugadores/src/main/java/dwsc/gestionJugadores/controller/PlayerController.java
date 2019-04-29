package dwsc.gestionJugadores.controller;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import dwsc.gestionJugadores.domain.Player;
import dwsc.gestionJugadores.repository.PlayerRepository;

@RestController
public class PlayerController 
{
	@Autowired
	PlayerRepository playerRepo;
		
	@GetMapping("/players")
	public ResponseEntity<List<Player>> findPlayers()
	{
		ArrayList<Player> player = new ArrayList<Player>(playerRepo.findAll());
		
		if(!player.isEmpty())
		{
			return ResponseEntity.ok(player);
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	
	@GetMapping("/players/{id}")
	public ResponseEntity<Player> findPlayerById(@PathVariable int id)
	{
		Player player;
		
		try
		{
			player = playerRepo.findById(id).get();
			
		}catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(player);
		
	}
	
	/*@GetMapping("/players/{name}")
	public ResponseEntity<List<Player>> findPlayerByName(@PathVariable String name)
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
	
	/*@RequestMapping("/player/{dni}")
	public ResponseEntity<Player> findPlayerByDNI(@PathVariable String dni)
	{
		Player player = playerRepo.findByDni(dni);
		
		if(player != null)
		{
			return ResponseEntity.ok(player);
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}*/
	
	@PostMapping("/players")
	public ResponseEntity<Player> addPlayer(Player player) 
	{
		try
		{
			playerRepo.save(player);
			
		}catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
		}
		
		/*ArrayList<Player> players = new ArrayList<Player>(playerRepo.findAll());
		model.put("players", players);
        return "playerTable";*/
		
		URI location  =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(player.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/players/{id}")
	public ResponseEntity<Player> editPlayerById(Player player, @PathVariable int id) 
	{
		Player dbPlayer;
		
		try
		{
			dbPlayer = playerRepo.findById(id).get();
			dbPlayer = player;
			playerRepo.save(dbPlayer);
			
		}catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(dbPlayer);
		/*model.put("player", player);
		return ResponseEntity.ok("playercreation");*/
		
	}
	
	@DeleteMapping("/players/{id}")
	public ResponseEntity<Player> deletePlayerById(@PathVariable int id) 
	{
		try
		{
			playerRepo.deleteById(id);
			
		}catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		
		/*ArrayList<Player> players = new ArrayList<Player>(playerRepo.findAll());
		model.put("players", players);*/
		return ResponseEntity.ok(null);
	}
	
	
	
	
	
	
	@RequestMapping("/playerstable")
	public String getPlayersTab(Map<String, List<Player>> model) 
	{
		ArrayList<Player> players = new ArrayList<Player>(playerRepo.findAll());
		model.put("players", players);
		
		return "playerTable";
	}
	
	@RequestMapping("/playercreation")
	public String getPlayerCreation(Map<String, List<Player>> model) 
	{
		ArrayList<Player> players = new ArrayList<Player>(playerRepo.findAll());
		model.put("players", players);
		
		return "playercreation";
	}
	
	@PutMapping("/editPlayer")
	public String editPlayer(Player player, Map<String, Player> model) 
	{
		try
		{
			playerRepo.save(player);
			
		}catch(Exception e)
		{
			//return new ResponseEntity<String>("gameTable", HttpStatus.ACCEPTED);
			//return "gameTable";
		}
		
		model.put("player", player);
		//return new ResponseEntity<Game>(game, HttpStatus.CREATED);
        return "playercreation";
	}
	
	@DeleteMapping("/deletePlayer/")
	public String deletePlayer(Player player, Map<String, List<Player>> model) 
	{
		playerRepo.delete(player);
		
		ArrayList<Player> players = new ArrayList<Player>(playerRepo.findAll());
		model.put("players", players);
        return "playerTable";
	}
}

