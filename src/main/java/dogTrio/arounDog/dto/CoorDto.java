package dogTrio.arounDog.dto;

import dogTrio.arounDog.domain.Coordinate;
import dogTrio.arounDog.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CoorDto {

    private String userId;
    private Double latitude;
    private Double longitude;
    private String tile;
    private Boolean walking;
    private LocalDateTime timeStamp;


    public void setWalkTime(Boolean walking) {
        this.walking = walking;
        this.timeStamp = LocalDateTime.now();
    }

    public static Coordinate createCoordinate(User user, CoorDto coorDto) {
       return new Coordinate(user, coorDto.getLatitude(), coorDto.getLatitude(), coorDto.getTile(), coorDto.getWalking(), coorDto.getTimeStamp());
    }
}
