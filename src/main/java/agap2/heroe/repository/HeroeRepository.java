package agap2.heroe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import agap2.heroe.model.Heroe;
import agap2.heroe.util.CustomException;

public interface HeroeRepository extends CrudRepository<Heroe,Integer>{

	@Query("SELECT h FROM Heroe h where UPPER(h.name) LIKE %:filter%")
	List<Heroe> getHeroesWithFilter(@Param("filter")String filter);
}
