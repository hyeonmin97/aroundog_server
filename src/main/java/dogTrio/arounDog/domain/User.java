package dogTrio.arounDog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String password;
    private Integer age;
    private int image;
    private String userName;
    private String phone;
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public User(String userName, String password, Integer age, int image, String phone, String email, Gender gender) {
        this.password = password;
        this.age = age;
        this.image = image;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }

}
