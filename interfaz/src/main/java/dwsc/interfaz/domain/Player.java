package dwsc.interfaz.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@XmlRootElement
@Entity
public class Player 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String lastname;
	private int age;
	private String dni;
	
	@ManyToMany
	@JoinTable(
			  name = "Player_has_game", 
			  joinColumns = @JoinColumn(name = "Player_id"), 
			  inverseJoinColumns = @JoinColumn(name = "Game_id"))
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
