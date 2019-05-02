package dwsc.interfazGestor.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import dwsc.interfazGestor.domain.Game;
import dwsc.interfazGestor.domain.Player;


@Controller
public class InterfazGestorController 
{
	private static String gestionJuegosUrl = "http://localhost:8081";
	private static String gestionJugadoresUrl = "http://localhost:8082";
	private static String asignarJuegosUrl = "http://localhost:8083";
	private static String asignarSeguidoresUrl = "http://localhost:8084";
	
	/**********************************************************
	 **********************************************************
	 ******************PÁGINAS DE LISTAR***********************
	 **********************************************************
	 **********************************************************/
	
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
	
	/**********************************************************
	 **********************************************************
	 ******************PÁGINAS DE CREAR***********************
	 **********************************************************
	 **********************************************************/
	
	@RequestMapping("/addPlayer")
	public String addPlayer(Map<String, Player> model) 
	{
		Player player = new Player();
		model.put("player", player);
		
		return "playercreation";
	}
	
	@RequestMapping("/addGame")
	public String addGame(Map<String, Game> model)
	{
		Game game = new Game();
		model.put("game", game);
		
		return "gameCreation";	
	}
	
	
	/**********************************************************
	 **********************************************************
	 ******************PÁGINAS DE EDITAR***********************
	 **********************************************************
	 **********************************************************/
	
	@RequestMapping("/editPlayer/{id}")
	public String editPlayer(Map<String, Player> model, @PathVariable int id) 
	{
		Player player = (new RestTemplate()).getForEntity(gestionJugadoresUrl + "/players/" + id, Player.class).getBody();
		model.put("player", player);
		
		return "playercreation";
	}
	
	@RequestMapping("/editGame/{id}")
	public String editGame(Map<String, Game> model, @PathVariable int id)
	{
		Game game = (new RestTemplate()).getForEntity(gestionJuegosUrl + "/games/" + id, Game.class).getBody();
		model.put("game", game);
		
		return "gameCreation";	
	}

}

