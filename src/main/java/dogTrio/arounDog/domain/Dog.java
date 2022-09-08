package dogTrio.arounDog.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Dog {

    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private DogBreed breed;
}
