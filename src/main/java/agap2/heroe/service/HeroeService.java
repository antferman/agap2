package agap2.heroe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agap2.heroe.model.Heroe;
import agap2.heroe.repository.HeroeRepository;

@Service
public class HeroeService {

	@Autowired
	HeroeRepository heroeRepository;

	public List<Heroe> getAllHeroes() {
		List<Heroe> heroes = new ArrayList<>();
		heroeRepository.findAll().forEach(heroe -> heroes.add(heroe));
		return heroes;
	}

	public Heroe getHeroeById(int id) {
		return heroeRepository.findById(id).get();
	}

	public void saveOrUpdate(Heroe heroe) {
		heroeRepository.save(heroe);
	}

	public void delete(int id) {
		heroeRepository.deleteById(id);
	}
	
	public List<Heroe> getHeroesWithFilter(String filter){
		List<Heroe> heroes = new ArrayList<>();
		heroeRepository.getHeroesWithFilter(filter.toUpperCase()).forEach(heroe->heroes.add(heroe));
		return heroes;
	}
}
