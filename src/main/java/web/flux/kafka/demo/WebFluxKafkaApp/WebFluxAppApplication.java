package web.flux.kafka.demo.WebFluxKafkaApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class WebFluxAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFluxAppApplication.class, args);
	}

}
