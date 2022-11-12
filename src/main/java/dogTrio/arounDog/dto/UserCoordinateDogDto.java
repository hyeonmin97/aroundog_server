package dogTrio.arounDog.dto;

import dogTrio.arounDog.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCoordinateDogDto {

    //user
    //userId, userName
    String userId;
    String userName;
    Integer userAge;
    int userImage;

    //coordinate
    //latitude, longitude
    Double latitude;
    Double longitude;
    Long dogId;

    //dog_breed
    String dogName;
    Integer dogAge;
    Gender dogGender;
    Double dogWeight;
    DogBreed dogBreed;


    public UserCoordinateDogDto(Coordinate coordinate, UserDog userDog) {
        this.userId = userDog.getUser().getUserId();
        this.userName = userDog.getUser().getUserName();
        this.userAge = userDog.getUser().getAge();
        this.userImage = userDog.getUser().getImage();
        this.dogName = userDog.getName();
        this.dogAge = userDog.getAge();
        this.dogGender = userDog.getGender();
        this.dogWeight = userDog.getWeight();
        this.dogBreed = userDog.getDog().getBreed();

        this.dogId = coordinate.getUserDog().getId();
        this.latitude = coordinate.getLatitude();
        this.longitude = coordinate.getLongitude();

    }
}
