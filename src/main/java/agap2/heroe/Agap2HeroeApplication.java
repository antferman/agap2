package agap2.heroe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class Agap2HeroeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Agap2HeroeApplication.class, args);
	}
}
