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
import dwsc.interfaz.domain.Player;


@Controller
public class InterfazController 
{
	private static String gestionJuegosUrl = "http://localhost:8081";
	private static String gestionJugadoresUrl = "http://localhost:8082";
	private static String asignarJuegosUrl = "http://localhost:8083";
	private static String asignarSeguidoresUrl = "http://localhost:8084";
	
	@RequestMapping("/gameList")
	public String gameList(Map<String, List<Game>> gameModel, Map<String, List<Game>> favouriteGamesModel)
	{
		ArrayList<Game> games = new ArrayList<Game>(Arrays.asList((new RestTemplate()).getForEntity(gestionJuegosUrl + "/games", Game[].class).getBody()));
		ArrayList<Game> favouriteGames = new ArrayList<Game>(Arrays.asList((new RestTemplate()).getForEntity(asignarJuegosUrl + "players/1/games", Game[].class).getBody()));
		gameModel.put("games", games);
		favouriteGamesModel.put("favouriteGames", favouriteGames);
		return "gametable";	
	}
	
	@RequestMapping("/playerList")
	public String playerList(Map<String, List<Player>> playerModel, Map<String, List<Player>> followingModel) 
	{
		ArrayList<Player> players = new ArrayList<Player>(Arrays.asList((new RestTemplate()).getForEntity(gestionJugadoresUrl + "/players", Player[].class).getBody()));
		ArrayList<Player> followingPeople = new ArrayList<Player>(Arrays.asList((new RestTemplate()).getForEntity(asignarSeguidoresUrl + "players/1/followed", Player[].class).getBody()));
		playerModel.put("players", players);
		followingModel.put("followingPeople", followingPeople);
		
		return "playerTable";
	}
	
	@RequestMapping("/editPlayer")
	public String editPlayer(Map<String, List<Player>> model) 
	{
		ArrayList<Player> players = new ArrayList<Player>(Arrays.asList((new RestTemplate()).getForEntity(gestionJugadoresUrl + "/players", Player[].class).getBody()));
		model.put("players", players);
		
		return "playercreation";
	}
	
	@RequestMapping("/addGame")
	public String addGame(Map<String, List<Game>> model)
	{
		ArrayList<Game> games = new ArrayList<Game>(Arrays.asList((new RestTemplate()).getForEntity(gestionJuegosUrl + "/games", Game[].class).getBody()));
		model.put("games", games);
		
		return "gameCreation";	
	}
	
	@RequestMapping("/asdaf")
	public void testo()
	{
		System.out.println("Auch!");
	}
}
