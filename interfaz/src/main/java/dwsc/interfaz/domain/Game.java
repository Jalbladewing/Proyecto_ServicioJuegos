package dwsc.interfaz.domain;

import java.util.ArrayList;

public class Game 
{
	private int id;
	
	private String name;
	private String description;
	private String url;
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public String getUrl() 
	{
		return url;
	}
	
	public void setUrl(String url) 
	{
		this.url = url;
	}
	
	public boolean isFavouriteGame(ArrayList<Game> favouriteGames)
	{
		for(int i = 0; i < favouriteGames.size(); i++)
		{
			if(getId() == favouriteGames.get(i).getId()) return true;
		}
		
		return false;
	}
	
}
