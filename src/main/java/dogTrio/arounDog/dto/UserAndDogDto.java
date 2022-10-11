package dogTrio.arounDog.dto;

import dogTrio.arounDog.domain.Dog;
import dogTrio.arounDog.domain.Gender;
import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.UserDog;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class UserAndDogDto {


    private String userId;
    private String password;
    private Integer userAge;
    private int image;
    private String userName;
    private String phone;
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender userGender;

    private Long dogId;
    private String dogName;
    private Integer dogAge;
    private Double dogWeight;
    private Double dogHeight;
    @Enumerated(EnumType.STRING)
    private Gender dogGender;
    private Long breed;

    private List<ImgDto> dogImgList = new ArrayList();

    private Boolean success;

    public void addImgs(List<ImgDto> list){
        this.dogImgList.addAll(list);
    }
    public UserAndDogDto(Boolean success) {
        this.success = success;
    }
    public UserAndDogDto(User user, Boolean success) {
        setUser(user);
        this.success = success;
    }
    public UserAndDogDto(User user, UserDog userDog, Boolean success) {
        setUser(user);
        setUserDog(userDog);
        this.success = success;
    }

    private void setUser(User user) {
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.userAge = user.getAge();
        this.image = user.getImage();
        this.userName = user.getUserName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.userGender = user.getGender();
    }

    private void setUserDog(UserDog userDog) {
        this.dogId = userDog.getId();
        this.dogName = userDog.getName();
        this.dogAge = userDog.getAge();
        this.dogWeight = userDog.getWeight();
        this.dogHeight = userDog.getHeight();
        this.dogGender = userDog.getGender();
        this.breed = userDog.getDog().getId();
    }

    public static List<UserAndDogDto> loginFail() {
        List<UserAndDogDto> list = new ArrayList<>();
        list.add(new UserAndDogDto(false));
        return list;
    }
}
