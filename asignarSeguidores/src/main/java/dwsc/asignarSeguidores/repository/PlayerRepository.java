package dwsc.asignarSeguidores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import dwsc.asignarSeguidores.domain.Player;


@RestResource(path="players", rel="players")
public interface PlayerRepository extends JpaRepository<Player, Integer> 
{
	List<Player> findByName(@Param("name") String name);
	
	Player findByDni(@Param("dni") String dni);
	
}

