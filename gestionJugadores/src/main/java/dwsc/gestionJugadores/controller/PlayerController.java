package dwsc.gestionJugadores.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dwsc.gestionJugadores.domain.Player;
import dwsc.gestionJugadores.repository.PlayerRepository;

@RestController
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

}

