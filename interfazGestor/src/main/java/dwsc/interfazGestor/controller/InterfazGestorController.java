package dwsc.interfazGestor.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

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
	
	//PÁGINA PRINCIPAL
	@RequestMapping("/")
	public String home()
	{
		System.out.println(System.getProperty("user.dir"));
		return "redirect:" + "/gameList";
	}
	
	//PÁGINA DE LISTA DE JUEGOS
	@RequestMapping("/gameList")
	public String gameList(Map<String, List<Game>> gameModel, Map<String, List<Game>> favouriteGamesModel)
	{
		ArrayList<Game> games = new ArrayList<Game>();
		ArrayList<Game> favouriteGames = new ArrayList<Game>();
		try 
		{
			games.addAll(new ArrayList<Game>(Arrays.asList((new RestTemplate()).getForEntity(gestionJuegosUrl + "/games", Game[].class).getBody())));
			favouriteGames.addAll(new ArrayList<Game>(Arrays.asList((new RestTemplate()).getForEntity(asignarJuegosUrl + "players/1/games", Game[].class).getBody())));
			
		}catch(Exception e)
		{
			System.out.println(e);
		}

		gameModel.put("games", games);
		favouriteGamesModel.put("favouriteGames", favouriteGames);
		return "gametable";	
	}
	
	//PÁGINA DE LISTA DE JUGADORES
	@RequestMapping("/playerList")
	public String playerList(Map<String, List<Player>> playerModel, Map<String, List<Player>> followingModel) 
	{
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Player> followingPeople = new ArrayList<Player>();
		
		try 
		{
			players.addAll(new ArrayList<Player>(Arrays.asList((new RestTemplate()).getForEntity(gestionJugadoresUrl + "/players", Player[].class).getBody())));
			followingPeople.addAll(new ArrayList<Player>(Arrays.asList((new RestTemplate()).getForEntity(asignarSeguidoresUrl + "players/1/followed", Player[].class).getBody())));
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		playerModel.put("players", players);
		followingModel.put("followingPeople", followingPeople);
		
		return "playerTable";
	}
	
	/**********************************************************
	 **********************************************************
	 ******************PÁGINAS DE CREAR***********************
	 **********************************************************
	 **********************************************************/
	
	//PÁGINA DE CREAR JUGADOR
	@RequestMapping("/addPlayer")
	public String addPlayer(Map<String, Player> model) 
	{
		Player player = new Player();
		model.put("player", player);
		
		return "playercreation";
	}
	
	//PÁGINA DE CREAR JUEGO
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
	
	//PÁGINA DE EDITAR JUGADOR
	@RequestMapping("/editPlayer/{id}")
	public String editPlayer(Map<String, Player> model, @PathVariable int id) 
	{
		Player player = (new RestTemplate()).getForEntity(gestionJugadoresUrl + "/players/" + id, Player.class).getBody();
		model.put("player", player);
		
		return "playercreation";
	}
	
	//PÁGINA DE EDITAR JUEGO
	@RequestMapping("/editGame/{id}")
	public String editGame(Map<String, Game> model, @PathVariable int id)
	{
		Game game = (new RestTemplate()).getForEntity(gestionJuegosUrl + "/games/" + id, Game.class).getBody();
		model.put("game", game);
		
		return "gameCreation";	
	}
	
	/**********************************************************
	 **********************************************************
	 ************************EXTRA*****************************
	 **********************************************************
	 **********************************************************/
	
	// Subida de imágenes al servidor
	@PostMapping("/imageUpload")
	public ResponseEntity<Object> fileUpload(@RequestParam("imgInp") MultipartFile file) throws IOException 
	{
		   String pathGestor = System.getProperty("user.dir") + "\\target\\classes\\static\\images\\";
		   String pathUsuario = pathGestor.replace("interfazGestor", "interfaz");
		// Save file on system
		      if (!file.getOriginalFilename().isEmpty()) {
		    	  //Crear la imagen en el gestor.
		         BufferedOutputStream outputStream = new BufferedOutputStream(
		               new FileOutputStream(
		                     new File(pathGestor, file.getOriginalFilename())));
		         outputStream.write(file.getBytes());
		         outputStream.flush();
		         outputStream.close();
		         
		         //Crear la imagen en el jugador.
		         outputStream = new BufferedOutputStream(
			               new FileOutputStream(
			                     new File(pathUsuario, file.getOriginalFilename())));
			         outputStream.write(file.getBytes());
			         outputStream.flush();
			         outputStream.close();
		         
		      }else{
		         return new ResponseEntity<>("Invalid file.",HttpStatus.BAD_REQUEST);
		      }
		      
		   return new ResponseEntity<>("File Uploaded Successfully.",HttpStatus.OK);
	}

}

