package dwsc.gestionJugadores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import dwsc.gestionJugadores.domain.Player;

@RestResource(path="players", rel="players")
public interface PlayerRepository extends JpaRepository<Player, Integer> 
{
	List<Player> findByName(@Param("name") String name);
	List<Player> findByLastname(@Param("lastname") String lastname);
	List<Player> findByAge(@Param("age") int age);
	
	Player findByDni(@Param("dni") String dni);
}

