package dwsc.gestionJuegos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dwsc.gestionJuegos.domain.Game;
import dwsc.gestionJuegos.repository.GameRepository;

@RestController
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
	
	/*@RequestMapping("/gamestable")
	public String getGamesTab(Map<String, List<Game>> model) 
	{
		ArrayList<Game> games = new ArrayList<Game>(gameRepo.findAll());
		model.put("games", games);
		
		return "gametemplate";
	}*/

}

