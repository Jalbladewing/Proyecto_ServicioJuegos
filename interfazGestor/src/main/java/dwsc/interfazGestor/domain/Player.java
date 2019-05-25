package dwsc.interfazGestor.domain;

import java.util.ArrayList;
import java.util.Set;

public class Player 
{
	private int id;
	
	private String name;
	private String lastname;
	private int age;
	private String dni;

	private Set<Game> favouriteGames;

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
	
	public String getLastname() 
	{
		return lastname;
	}
	
	public void setLastname(String lastname) 
	{
		this.lastname = lastname;
	}
	
	public int getAge() 
	{
		return age;
	}
	
	public void setAge(int age) 
	{
		this.age = age;
	}
	
	public String getDni() 
	{
		return dni;
	}
	
	public void setDni(String dni)
	{
		this.dni = dni;
	}

	public Set<Game> getFavouriteGames() 
	{
		return favouriteGames;
	}

	public void setFavouriteGames(Set<Game> favouriteGames) 
	{
		this.favouriteGames = favouriteGames;
	}
	
	public boolean isFollowing(ArrayList<Player> followers)
	{
		for(int i = 0; i < followers.size(); i++)
		{
			if(getId() == followers.get(i).getId()) return true;
		}
		
		return false;
	}
	
}
