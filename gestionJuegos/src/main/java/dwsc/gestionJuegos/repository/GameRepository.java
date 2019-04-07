package dwsc.gestionJuegos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import dwsc.gestionJuegos.domain.Game;

@RestResource(path="games", rel="games")
public interface GameRepository extends JpaRepository<Game, Integer> 
{
	Game findByName(@Param("name") String name);
}

