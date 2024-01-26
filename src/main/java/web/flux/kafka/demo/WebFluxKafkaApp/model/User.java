package web.flux.kafka.demo.WebFluxKafkaApp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@EqualsAndHashCode(of = {"id", "firstname", "lastname", "email"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(value = "user")
public class User {

    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private String email;
}
