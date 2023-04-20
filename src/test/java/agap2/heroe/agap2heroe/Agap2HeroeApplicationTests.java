package agap2.heroe.agap2heroe;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import agap2.heroe.model.Heroe;
import agap2.heroe.service.HeroeService;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class Agap2HeroeApplicationTests {

	@LocalServerPort
	private int port;
	
    @MockBean
    private HeroeService service;

	@Test
	void testGetAllHeroes() {
		Heroe heroe = new Heroe(1, "Heroe1");
		List<Heroe> list = new ArrayList<>();
		list.add(heroe);
	    when(service.getAllHeroes()).thenReturn(list);
		RestAssured.given().get("http://localhost:"+port+"/heroe/getAll").then().assertThat()
	      .statusCode(HttpStatus.OK.value()).body(containsString("Heroe1"));
	}

}
