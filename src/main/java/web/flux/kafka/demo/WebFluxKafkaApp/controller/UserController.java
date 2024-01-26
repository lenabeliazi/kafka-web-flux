package web.flux.kafka.demo.WebFluxKafkaApp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import web.flux.kafka.demo.WebFluxKafkaApp.kafka.KafkaProducer;
import web.flux.kafka.demo.WebFluxKafkaApp.model.User;
import web.flux.kafka.demo.WebFluxKafkaApp.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final KafkaProducer kafkaProducer;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestBody User user) {
        return userService.createUser(user).
                doOnSuccess(userCreated -> kafkaProducer.sendMessage("User created: " + userCreated.getFirstname() + " " + userCreated.getLastname()));
    }


    // method simply to find the id of a user to use on the method getUserById
    @GetMapping
    public Flux<User> getAllUsers() {
        Flux<User> users = userService.getAllUsers();
        return users;
    }

    @GetMapping("/{userId}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable String userId) {
        Mono<User> user = userService.findById(userId)
                .doOnSuccess(userFound -> {
                    if (userFound != null) {
                        kafkaProducer.sendMessage("User found:" + userFound);
                    }
                });

        return user.map(user1 -> ResponseEntity.ok().body(user1))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
