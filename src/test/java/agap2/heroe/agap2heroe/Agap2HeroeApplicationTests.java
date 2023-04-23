package agap2.heroe.agap2heroe;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import agap2.heroe.model.Heroe;
import agap2.heroe.service.HeroeService;
import agap2.heroe.util.CustomException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles(profiles = "test")
class Agap2HeroeApplicationTests {

	@LocalServerPort
	private int port;
	
    @MockBean
    private HeroeService service;
    
    private static List<Heroe> listHeroes = new ArrayList<>();
    
    static Heroe heroe;

    @BeforeAll
    static void setUp() {
    	heroe = new Heroe(1, "Heroe1");
    	listHeroes.add(heroe);
    	heroe = new Heroe(2, "Superman");
    	listHeroes.add(heroe);
    	heroe = new Heroe(3, "Spiderman");
    	listHeroes.add(heroe);
    	heroe = new Heroe(4, "Manolito el fuerte");
    	listHeroes.add(heroe);
    }
    @Test
	void testGetAllHeroes() {
	    when(service.getAllHeroes()).thenReturn(listHeroes);
		RestAssured.given().get("http://localhost:"+port+"/heroe/getAll").then().assertThat()
	      .statusCode(HttpStatus.OK.value()).body(containsString("Heroe1"));
	}
	
	@Test
	void testGetHeroe() throws CustomException {
		int id = 1;
		Heroe heroe = new Heroe(id, "Heroe1");
	    when(service.getHeroeById(id)).thenReturn(heroe);
		RestAssured.given().get("http://localhost:"+port+"/heroe/"+id).then().assertThat()
	      .statusCode(HttpStatus.OK.value()).body(containsString("Heroe1"));
	}
	
	@Test
	void testGetHeroesWithFilter() {
		String filter = "man";
		List<Heroe> sublist = listHeroes.subList(1, listHeroes.size());
	    when(service.getHeroesWithFilter(filter)).thenReturn(sublist);
		RestAssured.given().get("http://localhost:"+port+"/heroe/getWithFilter/"+filter).then().assertThat()
	      .statusCode(HttpStatus.OK.value()).body(containsString("Superman"), containsString("Spiderman"), containsString("Manolito el fuerte"), not(containsString("Heroe1")));
	}
	
	@Test
	@Order(2)
	void testDeleteHeroe() {
		int id =1;
		RestAssured.given().delete("http://localhost:"+port+"/heroe/"+id).then().assertThat()
	      .statusCode(HttpStatus.OK.value());
	}
	
	@Test
	@Order(1)
	void testCreateHeroe() {
		String jsonBody = "{\"id\":1, \"name\":\"Spiderman\"}";
		RestAssured.given().body(jsonBody).contentType(ContentType.JSON).post("http://localhost:"+port+"/heroe/create").then().assertThat().statusCode(200).body(is("1"));
	}

}
