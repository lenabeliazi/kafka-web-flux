package web.flux.demo.WebFluxApp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import web.flux.demo.WebFluxApp.model.User;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {


}