package web.flux.kafka.demo.WebFluxKafkaApp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import web.flux.kafka.demo.WebFluxKafkaApp.controller.UserController;
import web.flux.kafka.demo.WebFluxKafkaApp.kafka.KafkaProducer;
import web.flux.kafka.demo.WebFluxKafkaApp.model.User;
import web.flux.kafka.demo.WebFluxKafkaApp.repository.UserRepository;
import web.flux.kafka.demo.WebFluxKafkaApp.service.UserService;

import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private UserService userService;

	@MockBean
	KafkaProducer kafkaProducer;

	@MockBean
	private KafkaTemplate<String, String> kafkaTemplate;

	@Test
	public void testPostUser() {
		// Mock user data

		// Mock UserService behavior
		User user = new User();
		user.setFirstname("test");
		user.setLastname("user");
		user.setEmail("testUser@gmail.com");

		// Mock UserRepository behavior
		when(userService.createUser(any(User.class))).thenReturn(Mono.just(user));

		// Perform the request and verify the response
		webTestClient.post().uri("/users")
				.bodyValue(user)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(User.class)
				.isEqualTo(user);


		// Verify that Kafka message is sent
		verify(kafkaProducer, times(1)).sendMessage(any());
	}

	@Test
	public void testGetUserById() {
		// Mock user data
		User mockUser = new User();
		mockUser.setId("5561c111-1366-4c4c-98c0-c494f7feec76");
		mockUser.setFirstname("test");
		mockUser.setLastname("user");
		mockUser.setEmail("testUser@gmail.com");

		// Mock UserRepository behavior
		when(userService.findById("5561c111-1366-4c4c-98c0-c494f7feec76")).thenReturn(Mono.just(mockUser));

		// Perform the request and verify the response
		webTestClient.get().uri("/users/5561c111-1366-4c4c-98c0-c494f7feec76")
				.exchange()
				.expectStatus().isOk()
				.expectBody(User.class)
				.isEqualTo(mockUser);

		// Verify that Kafka message is sent
		verify(kafkaProducer, times(1)).sendMessage(any());
	}

	@Test
	public void testGetAllUsers() {
		// Mock user data
		User user1 = new User("1","Léna", "Beliazi", "l@gmail.com");
		User user2 = new User("2", "John", "Medo", "j@gmail.com");


		// Mock UserService behavior
		when(userService.getAllUsers()).thenReturn(Flux.just(user1, user2));

		// Perform the request and verify the response
		webTestClient.get().uri("/users")
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(User.class)
				.contains(user1, user2);

	}
}
