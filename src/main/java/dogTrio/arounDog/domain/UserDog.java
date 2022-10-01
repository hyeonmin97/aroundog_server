package dogTrio.arounDog.domain;

import dogTrio.arounDog.dto.UserDogDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserDog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_id")
    private Dog dog;

    private String name;
    private Integer age;
    private Double weight;
    private Double height;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public UserDog(User user, Dog dog, UserDogDto userDogDto) {
        this.user = user;
        this.dog = dog;
        this.name = userDogDto.getName();
        this.age = userDogDto.getAge();
        this.weight = userDogDto.getWeight();
        this.height = userDogDto.getHeight();
        this.gender = userDogDto.getGender();
    }



}
