package dogTrio.arounDog.dto;

import dogTrio.arounDog.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@AllArgsConstructor
public class UserDogWithBreedDto {
    private Long dogId;
    private String name;
    private Integer age;
    private Double weight;
    private Double height;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Long breed;
}
