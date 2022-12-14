package dogTrio.arounDog.dto;

import dogTrio.arounDog.domain.Gender;
import dogTrio.arounDog.domain.User;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
public class LoginDto {
    private String userId;
    private String password;
    private Integer age;
    private int image;
    private String userName;
    private String phone;
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Boolean success;

    public LoginDto(User user, Boolean success) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.age = user.getAge();
        this.image = user.getImage();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.success = success;
    }

    public LoginDto() {

    }

    public static LoginDto loginFalse() {
        LoginDto loginDto = new LoginDto();
        loginDto.success = false;
        return loginDto;
    }
}
