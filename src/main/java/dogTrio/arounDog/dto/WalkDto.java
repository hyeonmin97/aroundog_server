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

    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime endTime;

    public WalkDto() {

    }

    public WalkDto(String course, String courseCenter, LocalDateTime startTime, LocalDateTime endTime) {
        this.course = course;
        this.courseCenter = courseCenter;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static WalkDto makeWalkDto(String course, String courseCenter, LocalDateTime startTime, LocalDateTime endTim) {
        WalkDto walkDto = new WalkDto(course, courseCenter, startTime, endTim);
        return walkDto;
    }

    public void setUserAndPath(User user, String path) {
        this.user = user;
        this.img = path;
    }

    public Walk makeEntity() {
        return new Walk(user, course, courseCenter, 0, 0, img, startTime, endTime);
    }
}
