package agap2.heroe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import agap2.heroe.model.Heroe;
import agap2.heroe.service.HeroeService;
import agap2.heroe.util.CustomException;
import agap2.heroe.util.TrackExecutionTime;

@RestController
public class HeroeController {

	@Autowired
	HeroeService heroeService;

	@TrackExecutionTime
	@GetMapping("/heroe/getAll")
	public List<Heroe> getAllHeroes() {
		return heroeService.getAllHeroes();
	}

	@TrackExecutionTime
	@GetMapping("/heroe/{id}")
	public Heroe getHeroe(@PathVariable("id") int id) throws CustomException {
		return heroeService.getHeroeById(id);
	}
	
	@TrackExecutionTime
	@GetMapping("/heroe/getWithFilter/{filter}")
	public List<Heroe> getHeroes(@PathVariable("filter") String filter) {
		return heroeService.getHeroesWithFilter(filter);
	}

	@TrackExecutionTime
	@DeleteMapping("/heroe/{id}")
	public void deleteHeroe(@PathVariable("id") int id) throws CustomException {
		heroeService.delete(id);
	}

	@TrackExecutionTime
	@PostMapping("heroe/create")
	public int saveHeroe(@RequestBody Heroe heroe) throws CustomException {
		heroeService.saveOrUpdate(heroe);
		return heroe.getId();
	}
}
