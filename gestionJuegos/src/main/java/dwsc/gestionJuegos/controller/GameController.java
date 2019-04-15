package dwsc.gestionJuegos.controller;


import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dwsc.gestionJuegos.domain.Game;
import dwsc.gestionJuegos.repository.GameRepository;

@Controller
public class GameController 
{
	@Autowired
	GameRepository gameRepo;
	
	/*@RequestMapping("/student")
	public ResponseEntity<Object> findStudents()
	{
		return ResponseEntity.ok(studentRepo.findAll());
	}
	
	@RequestMapping("/students/{name}")
	public ResponseEntity<Object> findStudentByName(@PathVariable String name)
	{
		Student student = studentRepo.findByName(name);
		if(student != null)
		{
			return ResponseEntity.ok(student);
		}else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}*/
	
	@RequestMapping("/game/{name}")
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
	
	@RequestMapping("/gamestable")
	public String getGamesTab(Map<String, List<Game>> model) 
	{
		ArrayList<Game> games = new ArrayList<Game>(gameRepo.findAll());
		model.put("games", games);
		
		return "gameTable";
	}
	
	@RequestMapping("/gamecreation")
	public String getGameCreation(Map<String, List<Game>> model) 
	{
		ArrayList<Game> games = new ArrayList<Game>(gameRepo.findAll());
		model.put("games", games);
		
		return "gamecreation";
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

		gameRepo.save(game);
		
		ArrayList<Game> games = new ArrayList<Game>(gameRepo.findAll());
		model.put("games", games);
        return "gameTable";
	}
	
	@DeleteMapping("/deleteGame/")
	public String deletePlayer(Game game, Map<String, List<Game>> model) 
	{
		gameRepo.delete(game);
		
		ArrayList<Game> games = new ArrayList<Game>(gameRepo.findAll());
		model.put("games", games);
        return "gameTable";
	}
	
	@DeleteMapping("/deleteGame/{id}")
	public String deleteGameById(@PathVariable int id, Map<String, List<Game>> model) 
	{
		gameRepo.deleteById(id);
		
		ArrayList<Game> games = new ArrayList<Game>(gameRepo.findAll());
		model.put("games", games);
        return "gameTable";
	}

}

