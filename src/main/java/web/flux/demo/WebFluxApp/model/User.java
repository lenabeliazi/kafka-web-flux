package web.flux.demo.WebFluxApp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@EqualsAndHashCode(of = {"id","first","last", "email"})
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
    private String first;

    @Getter
    @Setter
    private String last;

    @Getter
    @Setter
    private String email;
}
