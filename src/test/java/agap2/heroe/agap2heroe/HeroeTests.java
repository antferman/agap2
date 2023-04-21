package agap2.heroe.agap2heroe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import agap2.heroe.model.Heroe;

@SpringBootTest()
public class HeroeTests {

	@Test
	void testSetIdAndName(){
		int id=1;
		String name = "Spiderman";
		Heroe heroe = new Heroe();
		heroe.setId(id);
		heroe.setName(name);
		assertEquals(name, heroe.getName());
		assertEquals(id, heroe.getId());
	}
	
	@Test
	void testConstructor() {
		int id=1;
		String name = "Spiderman";
		Heroe heroe = new Heroe(id,name);
		assertEquals(name, heroe.getName());
		assertEquals(id, heroe.getId());
	}
}
