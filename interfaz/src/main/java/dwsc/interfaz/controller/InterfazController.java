package dwsc.interfaz.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import dwsc.interfaz.domain.Game;


@Controller
public class InterfazController 
{
	@RequestMapping("/gameList")
	public String gameList(Map<String, List<Game>> model)
	{
		ArrayList<Game> games = new ArrayList<Game>(Arrays.asList((new RestTemplate()).getForEntity("http://localhost:8081/games", Game[].class).getBody()));
		model.put("games", games);
		
		return "gametable";	
	}
	
	@RequestMapping("/addGame")
	public String addGame(Map<String, List<Game>> model)
	{
		ArrayList<Game> games = new ArrayList<Game>(Arrays.asList((new RestTemplate()).getForEntity("http://localhost:8081/games", Game[].class).getBody()));
		model.put("games", games);
		
		return "gameCreation";	
	}
}
