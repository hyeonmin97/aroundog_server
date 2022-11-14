package dogTrio.arounDog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.Walk;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Lob;
import java.time.LocalDateTime;

@Getter
public class WalkDto {
    private User user;
    @Lob
    private String course;
    private String courseCenter;
    private int good;
    private int bad;
    private String img;
    private String v;

    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime endTime;

    private String tile;

    private Long second;

    private Long distance;
    private String dogIds;

    public WalkDto() {

    }

    public WalkDto(String course, String courseCenter, LocalDateTime startTime, LocalDateTime endTime, String tile, Long second, Long distance, String dogIds) {
        this.course = course;
        this.courseCenter = courseCenter;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tile = tile;
        this.second = second;
        this.distance = distance;
        this.dogIds = dogIds;
    }

    public static WalkDto makeWalkDto(String course, String courseCenter, LocalDateTime startTime, LocalDateTime endTim, String tile, Long second, Long distance, String dogIds) {
        WalkDto walkDto = new WalkDto(course, courseCenter, startTime, endTim, tile, second, distance, dogIds);
        return walkDto;
    }

    public void setUserAndPath(User user, String path) {
        this.user = user;
        this.img = path;
    }

    public Walk makeEntity() {
        return new Walk(user, course, courseCenter, 0, 0, img, startTime, endTime, tile, second, distance, dogIds);
    }
}
