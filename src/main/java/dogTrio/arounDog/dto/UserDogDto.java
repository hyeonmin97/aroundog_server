package dogTrio.arounDog.dto;

import dogTrio.arounDog.domain.Gender;
import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.UserDog;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDogDto {
    private String userId;
    private Long dogId;
    private String name;
    private Integer age;
    private Double weight;
    private Double height;
    private Gender gender;

}
