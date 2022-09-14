package dogTrio.arounDog.dto;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
public class WalkListDto {

    private Long id;
    private String userId;
    private String courseCenter;
    private int good;
    private int bad;
    private byte[] img;
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime endTime;
    private Long walkSecond;

    public WalkListDto(Long id, String userId, String courseCenter, int good, int bad, byte[] img, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.userId = userId;
        this.courseCenter = courseCenter;
        this.good = good;
        this.bad = bad;
        this.img = img;
        this.startTime = startTime;
        this.endTime = endTime;
        this.walkSecond = Duration.between(startTime, endTime).getSeconds();
    }

}
