package dogTrio.arounDog.dto;

import dogTrio.arounDog.domain.Gender;
import dogTrio.arounDog.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter @Setter
public class UserDto {
    private String userId;
    private String password;
    private Integer age;
    private int image;
    private String userName;
    private String phone;
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public static User createUser(UserDto userDto) {
        return new User(userDto.userId, userDto.userName, userDto.password, userDto.age, userDto.image, userDto.phone, userDto.email, userDto.gender);

    }
}
