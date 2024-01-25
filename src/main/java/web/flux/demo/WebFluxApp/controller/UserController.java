package web.flux.demo.WebFluxApp.controller;

import lombok.RequiredArgsConstructor;
import web.flux.demo.WebFluxApp.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import web.flux.demo.WebFluxApp.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public Flux<User> getAllUsers() {
        Flux<User> users =  userService.getAllUsers();
        return users;
    }

    @GetMapping("/{userId}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable String userId) {
        Mono<User> user = userService.findById(userId);
        return user.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
