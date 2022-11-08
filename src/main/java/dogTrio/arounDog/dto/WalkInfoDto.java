package dogTrio.arounDog.dto;

import dogTrio.arounDog.domain.Walk;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Lob;
import java.time.LocalDateTime;

@Getter
public class WalkInfoDto {

    private Long walkId;
    @Lob
    private String course;
    private byte[] img;
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime endTime;
    private Long second;
    private Long distance;

    public WalkInfoDto (Walk walk, byte[] img) {
        this.walkId = walk.getId();
        this.course = walk.getCourse();
        this.img = img;
        this.startTime = walk.getStartTime();
        this.endTime = walk.getEndTime();
        this.second = walk.getSecond();
        this.distance = walk.getDistance();
    }
}
