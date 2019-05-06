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
	
	@RequestMapping("/")
	public String home()
	{
		return "redirect:" + "http://localhost:8080/ProductorConsumidor/news";
	}
	
	@RequestMapping("/gameList")
	public String gameList(Map<String, List<Game>> gameModel, Map<String, List<Game>> favouriteGamesModel)
	{
		ArrayList<Game> games = new ArrayList<Game>();
		ArrayList<Game> favouriteGames = new ArrayList<Game>();
		try 
		{
			games.addAll(new ArrayList<Game>(Arrays.asList((new RestTemplate()).getForEntity(gestionJuegosUrl + "/games", Game[].class).getBody())));
			favouriteGames.addAll(new ArrayList<Game>(Arrays.asList((new RestTemplate()).getForEntity(asignarJuegosUrl + "/players/1/games", Game[].class).getBody())));
			
		}catch(Exception e)
		{
			System.out.println(e);
		}

		gameModel.put("games", games);
		favouriteGamesModel.put("favouriteGames", favouriteGames);
		return "gametable";	
	}
	
	@RequestMapping("/playerList")
	public String playerList(Map<String, List<Player>> playerModel, Map<String, List<Player>> followingModel) 
	{
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Player> followingPeople = new ArrayList<Player>();
		
		try 
		{
			players.addAll(new ArrayList<Player>(Arrays.asList((new RestTemplate()).getForEntity(gestionJugadoresUrl + "/players", Player[].class).getBody())));
			followingPeople.addAll(new ArrayList<Player>(Arrays.asList((new RestTemplate()).getForEntity(asignarSeguidoresUrl + "/players/1/followed", Player[].class).getBody())));
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		playerModel.put("players", players);
		followingModel.put("followingPeople", followingPeople);
		
		return "playerTable";
	}
	
	@RequestMapping("/gameList/name/{name}")
	public String gameListByName(Map<String, List<Game>> gameModel, Map<String, List<Game>> favouriteGamesModel, @PathVariable String name) 
	{
		ArrayList<Game> games = new ArrayList<Game>();
		ArrayList<Game> favouriteGames = new ArrayList<Game>();
		try 
		{
			games.addAll(new ArrayList<Game>(Arrays.asList((new RestTemplate()).getForEntity(gestionJuegosUrl + "/games/name/" + name, Game[].class).getBody())));
			favouriteGames.addAll(new ArrayList<Game>(Arrays.asList((new RestTemplate()).getForEntity(asignarJuegosUrl + "/players/1/games", Game[].class).getBody())));
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		gameModel.put("games", games);
		favouriteGamesModel.put("favouriteGames", favouriteGames);
		return "gametable";	
	}
	
	@RequestMapping("/playerList/name/{name}")
	public String playerListByName(Map<String, List<Player>> playerModel, Map<String, List<Player>> followingModel, @PathVariable String name) 
	{
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Player> followingPeople = new ArrayList<Player>();
		
		try 
		{
			players.addAll((Arrays.asList((new RestTemplate()).getForEntity(gestionJugadoresUrl + "/players/name/" + name, Player[].class).getBody())));
			followingPeople.addAll(new ArrayList<Player>(Arrays.asList((new RestTemplate()).getForEntity(asignarSeguidoresUrl + "/players/1/followed", Player[].class).getBody())));
			
		}catch(Exception e)
		{
			System.out.println(e);
		}

		playerModel.put("players", players);
		followingModel.put("followingPeople", followingPeople);
		
		return "playerTable";
	}
	
	@RequestMapping("/playerList/lastname/{lastname}")
	public String playerListByLastname(Map<String, List<Player>> playerModel, Map<String, List<Player>> followingModel, @PathVariable String lastname) 
	{
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Player> followingPeople = new ArrayList<Player>();
		
		try 
		{
			players.addAll((Arrays.asList((new RestTemplate()).getForEntity(gestionJugadoresUrl + "/players/lastname/" + lastname, Player[].class).getBody())));
			followingPeople.addAll(new ArrayList<Player>(Arrays.asList((new RestTemplate()).getForEntity(asignarSeguidoresUrl + "/players/1/followed", Player[].class).getBody())));
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		playerModel.put("players", players);
		followingModel.put("followingPeople", followingPeople);
		
		return "playerTable";
	}
	
	@RequestMapping("/playerList/age/{age}")
	public String playerListByAge(Map<String, List<Player>> playerModel, Map<String, List<Player>> followingModel, @PathVariable String age) 
	{
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Player> followingPeople = new ArrayList<Player>();
		
		try 
		{
			players.addAll((Arrays.asList((new RestTemplate()).getForEntity(gestionJugadoresUrl + "/players/age/" + age, Player[].class).getBody())));
			followingPeople.addAll(new ArrayList<Player>(Arrays.asList((new RestTemplate()).getForEntity(asignarSeguidoresUrl + "/players/1/followed", Player[].class).getBody())));
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		playerModel.put("players", players);
		followingModel.put("followingPeople", followingPeople);
		
		return "playerTable";
	}
	
	@RequestMapping("/playerList/dni/{dni}")
	public String playerListByDni(Map<String, List<Player>> playerModel, Map<String, List<Player>> followingModel, @PathVariable String dni) 
	{
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Player> followingPeople = new ArrayList<Player>();
		
		try 
		{
			players.add(((new RestTemplate()).getForEntity(gestionJugadoresUrl + "/players/dni/" + dni, Player.class).getBody()));
			followingPeople.addAll(new ArrayList<Player>(Arrays.asList((new RestTemplate()).getForEntity(asignarSeguidoresUrl + "/players/1/followed", Player[].class).getBody())));
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		playerModel.put("players", players);
		followingModel.put("followingPeople", followingPeople);
		
		return "playerTable";
	}
}
