package dogTrio.arounDog.dto;

import dogTrio.arounDog.domain.Coordinate;
import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.UserDog;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class CoorDto {

    private Double latitude;
    private Double longitude;
    private String tile;
    private Boolean walking;
    private LocalDateTime timeStamp;


    public void setWalkTime(Boolean walking) {
        this.walking = walking;
        this.timeStamp = LocalDateTime.now();
    }

    public static Coordinate createCoordinate(UserDog userDog, CoorDto coorDto) {
       return new Coordinate(userDog, coorDto.getLatitude(), coorDto.getLatitude(), coorDto.getTile(), coorDto.getWalking(), coorDto.getTimeStamp());
    }
}
