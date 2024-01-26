package web.flux.kafka.demo.WebFluxKafkaApp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import web.flux.kafka.demo.WebFluxKafkaApp.model.User;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {


}