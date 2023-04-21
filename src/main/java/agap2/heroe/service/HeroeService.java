package agap2.heroe.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import agap2.heroe.model.CustomException;
import agap2.heroe.model.Heroe;
import agap2.heroe.repository.HeroeRepository;

@Service
public class HeroeService {

	Logger logger = LoggerFactory.getLogger(HeroeService.class);
	
	@Autowired
	HeroeRepository heroeRepository;

	public List<Heroe> getAllHeroes() {
		List<Heroe> heroes = new ArrayList<>();
		heroeRepository.findAll().forEach(heroe -> heroes.add(heroe));
		return heroes;
	}

	public Heroe getHeroeById(int id) throws CustomException {
		Heroe heroe = new Heroe();
		try {
			heroe = heroeRepository.findById(id).get();
		}catch(IllegalArgumentException e){
			throw new CustomException("Given id is null");
		}
		return heroe;
	}

	public void saveOrUpdate(Heroe heroe) throws CustomException {
		try {
			heroeRepository.save(heroe);
		}catch(IllegalArgumentException e) {
			throw new CustomException("Given entity is null");
		}catch(OptimisticLockingFailureException e) {
			throw new CustomException("the entity uses optimistic locking and has a version attribute with a different value"
					+ " from that found in the persistence store or the entity is assumed to bepresent but does not exist in the database");
		}
	}

	public void delete(int id) throws CustomException {
		try {
			heroeRepository.deleteById(id);
		}catch(IllegalArgumentException e){
			throw new CustomException("Given id is null");
		}

	}
	
	public List<Heroe> getHeroesWithFilter(String filter){
		List<Heroe> heroes = new ArrayList<>();
		heroeRepository.getHeroesWithFilter(filter.toUpperCase()).forEach(heroe->heroes.add(heroe));
		return heroes;
	}
}
