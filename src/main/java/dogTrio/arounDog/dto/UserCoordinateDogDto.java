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

    //dog_breed
    Long dogId;
    String dogName;
    Integer dogAge;
    Gender dogGender;
    Double dogWeight;
    DogBreed dogBreed;


    public UserCoordinateDogDto(Coordinate coordinate, UserDog userDog) {
        this.userId = coordinate.getUser().getUserId();
        this.userName = coordinate.getUser().getUserName();
        this.userAge = coordinate.getUser().getAge();
        this.userImage = coordinate.getUser().getImage();
        this.latitude = coordinate.getLatitude();
        this.longitude = coordinate.getLongitude();
        this.dogId = userDog.getId();
        this.dogName = userDog.getName();
        this.dogAge = userDog.getAge();
        this.dogGender = userDog.getGender();
        this.dogWeight = userDog.getWeight();
        this.dogBreed = userDog.getDog().getBreed();

    }
}
