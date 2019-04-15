package dwsc.gestionJugadores.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dwsc.gestionJugadores.domain.Player;
import dwsc.gestionJugadores.repository.PlayerRepository;

@Controller
public class PlayerController 
{
	@Autowired
	PlayerRepository playerRepo;
		
	@RequestMapping("/player/{name}")
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
	
	@PostMapping("/addPlayer")
	public String addPlayer(Player player, Map<String, List<Player>> model) 
	{
		playerRepo.save(player);
		
		ArrayList<Player> players = new ArrayList<Player>(playerRepo.findAll());
		model.put("players", players);
        return "playerTable";
	}
	
	@DeleteMapping("/deletePlayer/")
	public String deletePlayer(Player player, Map<String, List<Player>> model) 
	{
		playerRepo.delete(player);
		
		ArrayList<Player> players = new ArrayList<Player>(playerRepo.findAll());
		model.put("players", players);
        return "playerTable";
	}
	
	@DeleteMapping("/deletePlayer/{id}")
	public String deletePlayerById(@PathVariable int id, Map<String, List<Player>> model) 
	{
		playerRepo.deleteById(id);
		
		ArrayList<Player> players = new ArrayList<Player>(playerRepo.findAll());
		model.put("players", players);
        return "playerTable";
	}

}

