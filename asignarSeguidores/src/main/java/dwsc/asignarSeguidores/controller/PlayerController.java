package dwsc.asignarSeguidores.controller;


import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dwsc.asignarSeguidores.domain.Player;
import dwsc.asignarSeguidores.repository.PlayerRepository;



@Controller
public class PlayerController 
{
	@Autowired
	PlayerRepository playerRepo;
		
	@GetMapping("/players/{id}/followers")
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
	
	@GetMapping("/players/{id}/followed")
	public ResponseEntity<Set<Player>> findFollowedById(@PathVariable int id)
	{
		if(playerRepo.findById(id).isPresent())
		{
			return ResponseEntity.ok(playerRepo.findById(id).get().getFollowingPlayers());
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
				
	}
	
	//Get Followers By Name
	/*@GetMapping("/players/{name}/followers")
	public ResponseEntity<Set<Player>> findFollowersByName(@PathVariable String name)
	{
		ArrayList<Player> player = new ArrayList<Player>(playerRepo.findByName(name));
			
		if(!player.isEmpty())
		{
			return ResponseEntity.ok(player.get(0).getFollowingPlayers());
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
			
	}*/
	
	/*//Get Followed By Name
	@GetMapping("/players/{name}/followed")
	public ResponseEntity<Set<Player>> findFollowedByName(@PathVariable String name)
	{
		ArrayList<Player> player = new ArrayList<Player>(playerRepo.findByName(name));
			
		if(!player.isEmpty())
		{
			return ResponseEntity.ok(player.get(0).getFollowedPlayers());
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
			
	}*/
		
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

