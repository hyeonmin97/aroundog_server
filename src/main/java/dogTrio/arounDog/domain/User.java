package dogTrio.arounDog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;
    private String password;
    private Integer age;
    private int image;
    private String userName;
    private String phone;
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public User(String userId, String userName, String password, Integer age, int image, String phone, String email, Gender gender) {
        this.userId = userId;
        this.password = password;
        this.age = age;
        this.image = image;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }

}
