package dwsc.asignarSeguidores.controller;


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

import dwsc.asignarSeguidores.domain.Player;
import dwsc.asignarSeguidores.repository.PlayerRepository;



@Controller
public class PlayerController 
{
	@Autowired
	PlayerRepository playerRepo;
		
	//Get Followers By Name
	@RequestMapping("/players/{name}/followers")
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
			
	}
	
	//Get Followed By Name
	@RequestMapping("/players/{name}/followed")
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
			
	}
		
	/*	//Get Followers By Id
	@RequestMapping("/players/{id}/games")
	public ResponseEntity<Set<Player>> findFollowersById(@PathVariable int id)
	{
		if(playerRepo.findById(id).isPresent())
		{
			return ResponseEntity.ok(playerRepo.findById(id).get().getFollowingPlayers());
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
			
	}*/
		
	@PostMapping("/players/{playerId}/addFollower/{followerId}")
	public ResponseEntity<String> addFollowerById(@PathVariable int playerId, @PathVariable int followerId, Map<String, Player> model) 
	{
		Player player, follower;
			
		try
		{
			player = playerRepo.findById(playerId).get();
			follower = playerRepo.findById(followerId).get();
				
			player.getFollowingPlayers().add(follower);
			playerRepo.save(player);
				
		}catch(NoSuchElementException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("playercreation");
		}
			
			
		model.put("player", player);
		return ResponseEntity.ok("playercreation");
	}
		
	@DeleteMapping("/players/{playerId}/deleteFollower/{followerId}")
	public ResponseEntity<String> deleteFollowerById(@PathVariable int playerId, @PathVariable int followerId, Map<String, Player> model) 
	{
		Player player, follower;
			
		try
		{
			player = playerRepo.findById(playerId).get();
			follower = playerRepo.findById(followerId).get();
				
			player.getFollowingPlayers().remove(follower);
			playerRepo.save(player);
				
		}catch(NoSuchElementException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("playercreation");
		}
			
			
		model.put("player", player);
		return ResponseEntity.ok("playercreation");
	}

}

