package web.flux.demo.WebFluxApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class WebFluxAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFluxAppApplication.class, args);
	}

}
