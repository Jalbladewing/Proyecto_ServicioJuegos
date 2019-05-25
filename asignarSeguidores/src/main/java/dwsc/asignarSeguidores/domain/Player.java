package dwsc.asignarSeguidores.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	//Players following the player
	@JsonManagedReference
	@ManyToMany
	@JoinTable(
			  name = "Player_follows_Player", 
			  joinColumns = @JoinColumn(name = "Player_following"), 
			  inverseJoinColumns = @JoinColumn(name = "Player_followed"))
	private Set<Player> followingPlayers;
	
	//Players being followed by the players
	@JsonBackReference
	@ManyToMany
	@JoinTable(
			  name = "Player_follows_Player", 
			  joinColumns = @JoinColumn(name = "Player_followed"), 
			  inverseJoinColumns = @JoinColumn(name = "Player_following"))
	private Set<Player> followedPlayers;
	
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

	public Set<Player> getFollowingPlayers() 
	{
		return followingPlayers;
	}

	public void setFollowingPlayers(Set<Player> followingPlayers) 
	{
		this.followingPlayers = followingPlayers;
	}

	public Set<Player> getFollowedPlayers() 
	{
		return followedPlayers;
	}

	public void setFollowedPlayers(Set<Player> followedPlayers) 
	{
		this.followedPlayers = followedPlayers;
	}


	
	
	
}
