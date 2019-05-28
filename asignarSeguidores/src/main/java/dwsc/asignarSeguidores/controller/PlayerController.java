package dwsc.asignarSeguidores.controller;


import java.net.URI;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dwsc.asignarSeguidores.domain.Player;
import dwsc.asignarSeguidores.repository.PlayerRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PlayerController 
{
	@Autowired
	PlayerRepository playerRepo;
		
	/**
	 * GET FOLLOWING PLAYERS BY PLAYER ID
	 * @param id
	 * @return 200 IF FOUND THE PLAYER, 
	 * 			404 IF NOT FOUND THE PLAYER
	 */
	@GetMapping("/players/{id}/followed")
	public ResponseEntity<Set<Player>> findFollowersById(@PathVariable int id)
	{
		if(playerRepo.findById(id).isPresent())
		{
			return ResponseEntity.ok(playerRepo.findById(id).get().getFollowingPlayers());
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
				
	}
		
	/**
	 * START FOLLOWING PLAYER
	 * @param playerId
	 * @param follower
	 * @return 201 IF PUT THE PLAYER SUCCESFULLY AS FOLLOWING,
	 * 			202 IF THE PLAYER OR FOLLOWING PLAYER IS NOT FOUND.
	 */
	@PostMapping("/players/{playerId}/followers")
	public ResponseEntity<Player> addFollowerById(@PathVariable int playerId, Player follower) 
	{
		Player player;
			
		try
		{
			player = playerRepo.findById(playerId).get();
				
			player.getFollowingPlayers().add(follower);
			playerRepo.save(player);
				
		}catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
		}
				
		URI location  =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{followerId}").buildAndExpand(follower.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
		
	/**
	 * STOP FOLLOWING PLAYER
	 * @param playerId
	 * @param followerId
	 * @return 200 IF DELETED SUCCESFULLY
	 * 			404 IF THE PLAYER OR FOLLOWER DOESN'T EXIST
	 */
	@DeleteMapping("/players/{playerId}/followers/{followerId}")
	public ResponseEntity<Player> deleteFollowerById(@PathVariable int playerId, @PathVariable int followerId) 
	{
		Player player, follower;
			
		try
		{
			player = playerRepo.findById(playerId).get();
			follower = playerRepo.findById(followerId).get();
			
			if(!player.getFollowingPlayers().remove(follower))
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

