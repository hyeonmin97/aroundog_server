package dogTrio.arounDog.domain;

import dogTrio.arounDog.dto.UserDogDto;
import dogTrio.arounDog.dto.UserDogWithBreedDto;
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
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog")
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

    public void updateData(UserDogWithBreedDto userDogWithBreedDto, Dog updateBreed) {
        if(!this.name.equals(userDogWithBreedDto.getName()))
            this.name = userDogWithBreedDto.getName();
        if (this.age != userDogWithBreedDto.getAge())
            this.age = userDogWithBreedDto.getAge();
        if (this.height!= userDogWithBreedDto.getHeight())
            this.height = userDogWithBreedDto.getHeight();
        if (this.weight!= userDogWithBreedDto.getWeight())
            this.weight = userDogWithBreedDto.getWeight();
        if (!this.gender.equals(userDogWithBreedDto.getGender()))
            this.gender = userDogWithBreedDto.getGender();
        if (!this.dog.equals(updateBreed))
            this.dog = updateBreed;

    }

}
