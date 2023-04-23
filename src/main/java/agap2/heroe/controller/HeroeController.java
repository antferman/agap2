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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
public class HeroeController {

	@Autowired
	HeroeService heroeService;
	
	/**
	* Returns a list of all heroes available in the application 
	*
	* @return      the list of heroes
	*/
	@TrackExecutionTime
	@Operation(summary = "Get all heroes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Get all heroes worked fine", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "404", description = "Heroes not found", content = @Content) })
	@GetMapping("/heroe/getAll")
	public List<Heroe> getAllHeroes() {
		return heroeService.getAllHeroes();
	}

	/**
	* Returns the heroe with the id specified 
	*
	* @param  id  the id to be seached
	* @return      the heroe with the id given
	*/
	@TrackExecutionTime
	@Operation(summary = "Find heroe by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Heroe found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Heroe not found", content = @Content) })
	@GetMapping("/heroe/{id}")
	public Heroe getHeroe(@PathVariable("id") int id) throws CustomException {
		return heroeService.getHeroeById(id);
	}

	/**
	* Returns a list of all heroes available in the application that matches the word given
	*
	* @param  filter  set of letter to be matched. For example "man" will return if available "Spiderman or Manolito el fuerte"
	* @return      the list of heroes
	*/
	@TrackExecutionTime
	@Operation(summary = "Find heroes that contains a set of letters")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Heroes found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid letters supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Heroes not found", content = @Content) })
	@GetMapping("/heroe/getWithFilter/{filter}")
	public List<Heroe> getHeroes(@PathVariable("filter") String filter) {
		return heroeService.getHeroesWithFilter(filter);
	}

	/**
	* Delete a heroe 
	*
	* @param  id  the id of the heroe to be deleted
	*/
	@TrackExecutionTime
	@Operation(summary = "Delete a heroe by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Heroe deleted", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Heroe deleted method not found", content = @Content) })
	@DeleteMapping("/heroe/{id}")
	public void deleteHeroe(@PathVariable("id") int id) throws CustomException {
		heroeService.delete(id);
	}

	/**
	* Create a heroe
	*
	* @param  heroe  the heroe to be created
	* @return      the id of the heroe created
	*/
	@TrackExecutionTime
	@Operation(summary = "Create a heroe")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Heroe created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid heroe supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Heroe create method not found", content = @Content) })
	@PostMapping("heroe/create")
	public int saveHeroe(@RequestBody Heroe heroe) throws CustomException {
		heroeService.saveOrUpdate(heroe);
		return heroe.getId();
	}
}
