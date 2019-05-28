package dwsc.gestionJugadores.controller;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import dwsc.gestionJugadores.service.PlayerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PlayerController 
{
	@Autowired
	PlayerRepository playerRepo;
	
	@Autowired PlayerService playerService;
		
	
	/**
	 * GET LIST OF ALL PLAYERS
	 * @return 200 IF FOUND PLAYERS, 
	 * 			404 IF NOT FOUND PLAYERS
	 */
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
	
	/**
	 * GET A PLAYER BY ID
	 * @param id
	 * @return 200 IF FOUND THE PLAYER, 
	 * 			404 IF NOT FOUND THE PLAYER
	 */
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
	
	/**
	 * GET A PLAYER BY NAME
	 * @param name
	 * @return 200 IF FOUND THE PLAYER, 
	 * 			404 IF NOT FOUND THE PLAYER
	 */
	@GetMapping("/players/name/{name}")
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
		
	}
	
	/**
	 * GET A PLAYER BY LASTNAME
	 * @param lastname
	 * @return 200 IF FOUND THE PLAYER, 
	 * 			404 IF NOT FOUND THE PLAYER
	 */
	@GetMapping("/players/lastname/{lastname}")
	public ResponseEntity<List<Player>> findPlayerByLastname(@PathVariable String lastname)
	{
		ArrayList<Player> player = new ArrayList<Player>(playerRepo.findByLastname(lastname));
		
		if(!player.isEmpty())
		{
			return ResponseEntity.ok(player);
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	
	/**
	 * GET A PLAYER BY AGE
	 * @param age
	 * @return 200 IF FOUND THE PLAYER, 
	 * 			404 IF NOT FOUND THE PLAYER
	 */
	@GetMapping("/players/age/{age}")
	public ResponseEntity<List<Player>> findPlayerByAge(@PathVariable int age)
	{
		ArrayList<Player> player = new ArrayList<Player>(playerRepo.findByAge(age));
		
		if(!player.isEmpty())
		{
			return ResponseEntity.ok(player);
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	
	/**
	 * GET A PLAYER BY DNI
	 * @param dni
	 * @return 200 IF FOUND THE PLAYER, 
	 * 			404 IF NOT FOUND THE PLAYER
	 */
	@GetMapping("/players/dni/{dni}")
	public ResponseEntity<Player> findPlayerByDni(@PathVariable String dni)
	{
		Player player = playerRepo.findByDni(dni);
		
		if(player != null)
		{
			return ResponseEntity.ok(player);
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	
	
	/**
	 * ADD A PLAYER TO THE LIST OF PLAYERS
	 * @param player
	 * @return 201 IF THE PLAYER IS CREATED SUCCESFULLY, 
	 * 			202 IF THE PLAYER ALREADY EXISTS
	 */
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
		
		URI location  =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(player.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	/**
	 * EDIT THE INFORMATION OF A PLAYER
	 * @param player
	 * @param id
	 * @return 200 IF THE PLAYER IS EDITED SUCCESFULLY, 
	 * 			404 IF THERE'S NO PLAYER WITH THAT ID OR IF THE PLAYER ALREADY EXISTS
	 */
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
	}
	
	/**
	 * DELETE A PLAYER
	 * @param id
	 * @return 200 IF IT'S DELETED SUCCESFULLY,
	 * 			404 IF THERE'S NO PLAYER WITH THAT ID
	 */
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
		
		return ResponseEntity.ok(null);
	}
	

	/**
	 * FEIGN CALL TO MICROSERVICE verificarDatos TO VERIFY THE DNI
	 * @param dni
	 * @return true if the DNI is valid,
	 * 			false if the DNI is invalid.
	 */
	@RequestMapping("/checkDNI/{dni}")
	public boolean checkDNI(@PathVariable String dni)
	{
		return playerService.checkPlayerData(dni);
	}
}

