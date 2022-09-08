package dogTrio.arounDog.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter(AccessLevel.PRIVATE)
public class UserDog {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;

    private String name;
    private Integer age;
    private Integer weight;
    private Integer height;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public static UserDog createUserDog(User user, Dog dog, String name, Integer age, Integer weight, Integer height) {
        UserDog userDog = new UserDog();
        userDog.setUser(user);
        userDog.setDog(dog);
        userDog.setName(name);
        userDog.setAge(age);
        userDog.setWeight(weight);
        userDog.setHeight(height);
        return userDog;
    }



}
