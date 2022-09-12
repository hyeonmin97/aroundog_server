package dogTrio.arounDog.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DogBreed breed;
}
