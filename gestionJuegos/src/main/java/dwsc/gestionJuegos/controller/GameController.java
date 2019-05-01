package dwsc.gestionJuegos.controller;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dwsc.gestionJuegos.domain.Game;
import dwsc.gestionJuegos.repository.GameRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class GameController 
{
	@Autowired
	GameRepository gameRepo;
	
	@GetMapping("/games")
	public ResponseEntity<List<Game>> findGames()
	{
		ArrayList<Game> game = new ArrayList<Game>(gameRepo.findAll());
		
		if(!game.isEmpty())
		{
			return ResponseEntity.ok(game);
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	
	//Juego especifico devuelve 200 o 404 (NOT FOUND)
	@GetMapping("/games/name/{name}")
	public ResponseEntity<Game> findGameByName(@PathVariable String name)
	{
		Game game = gameRepo.findByName(name);
		
		if(game != null)
		{
			return ResponseEntity.ok(game);
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	
	@GetMapping("/games/{id}")
	public ResponseEntity<Game> findGameById(@PathVariable int id)
	{
		Game game;
		
		try
		{
			game = gameRepo.findById(id).get();
			
		}catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(game);
		
	}
	
	@PostMapping("/games")
	public ResponseEntity<Game> addGame(Game game) 
	{
		try
		{
			gameRepo.save(game);
			
		}catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
		}
		
		URI location  =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(game.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/games/{id}")
	public ResponseEntity<Game> editGameById(Game game, @PathVariable int id) 
	{
		Game dbGame;
		
		try
		{
			dbGame = gameRepo.findById(id).get();
			dbGame = game;
			gameRepo.save(dbGame);
			
		}catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(dbGame);
	}
	
	@DeleteMapping("/games/{id}")
	public ResponseEntity<Game> deleteGameById(@PathVariable int id) 
	{
		try
		{
			gameRepo.deleteById(id);
			
		}catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(null);
	}
	
	
	
	
	
	
	
	

	@PostMapping("/addGame")
	public String addGame(Game game, Map<String, List<Game>> model/*, @RequestParam("imageFile") MultipartFile file*/) 
	{
		/*ClassLoader classLoader = getClass().getClassLoader();
		//File fil = new File(classLoader.getResource("static/images").getFile() + "/" + file.getOriginalFilename());
		Path fileNameAndPath = Paths.get(classLoader.getResource("static/images").getFile() + "/" +file.getOriginalFilename());
		try 
		{
			File fil = new File(classLoader.getResource("static/images").getFile() + "/" +file.getOriginalFilename());
			//Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) 
		{
				e.printStackTrace();
			}
		
		/*try {
		
			if (fil.createNewFile()) {
			    System.out.println("File is created!");
			} else {
			    System.out.println("File already exists.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		// UriComponents uriComponents = b.path("/customers/{id}").buildAndExpand(id);

		try
		{
			gameRepo.save(game);
			
		}catch(Exception e)
		{
			//return new ResponseEntity<String>("gameTable", HttpStatus.ACCEPTED);
			//return "gameTable";
		}
		
		ArrayList<Game> games = new ArrayList<Game>(gameRepo.findAll());
		model.put("games", games);
		//return new ResponseEntity<Game>(game, HttpStatus.CREATED);
        return "gameTable";
	}
	
	@PutMapping("/editGame")
	public String editGame(Game game, Map<String, Game> model) 
	{
		try
		{
			gameRepo.save(game);
			
		}catch(Exception e)
		{
			//return new ResponseEntity<String>("gameTable", HttpStatus.ACCEPTED);
			//return "gameTable";
		}
		
		model.put("game", game);
		//return new ResponseEntity<Game>(game, HttpStatus.CREATED);
        return "gamecreation";
	}
	
	
	@DeleteMapping("/deleteGame/")
	public String deleteGame(Game game, Map<String, List<Game>> model) 
	{
		gameRepo.delete(game);
		
		ArrayList<Game> games = new ArrayList<Game>(gameRepo.findAll());
		model.put("games", games);
        return "gameTable";
	}
	

}

