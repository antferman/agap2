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

@RestController
public class HeroeController {

	@Autowired
	HeroeService heroeService;

	@GetMapping("/heroe/getAll")
	private List<Heroe> getAllHeroes() {
		return heroeService.getAllHeroes();
	}

	@GetMapping("/heroe/{id}")
	private Heroe getHeroe(@PathVariable("id") int id) {
		return heroeService.getHeroeById(id);
	}
	
	@GetMapping("/heroe/getWithFilter/{filter}")
	private List<Heroe> getHeroes(@PathVariable("filter") String filter) {
		return heroeService.getHeroesWithFilter(filter);
	}

	@DeleteMapping("/heroe/{id}")
	private void deleteHeroe(@PathVariable("id") int id) {
		heroeService.delete(id);
	}

	@PostMapping("heroe/create")
	private int saveStudent(@RequestBody Heroe heroe) {
		heroeService.saveOrUpdate(heroe);
		return heroe.getId();
	}
}
