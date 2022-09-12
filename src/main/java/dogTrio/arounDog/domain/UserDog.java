package dogTrio.arounDog.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter(AccessLevel.PRIVATE)
public class UserDog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;

    private String name;
    private Integer age;
    private Double weight;
    private Double height;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public static UserDog createUserDog(User user, Dog dog, String name, Integer age, Double weight, Double height) {
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
