package dogTrio.arounDog.dto;

import dogTrio.arounDog.domain.Bad;
import dogTrio.arounDog.domain.Good;
import dogTrio.arounDog.domain.Walk;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class WalkWithGoodBad {
    private String userId;
    private String courseCenter;

    private Long walkId;

    private byte[] img;
//    private String strImg;

    private Integer good;
    private Integer bad;

    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime endTime;

    private Boolean checkGood = false;
    private Boolean checkBad = false;

    public WalkWithGoodBad(Walk walk, byte[] img, Good checkGood, Bad checkBad) {
        this.userId = walk.getUser().getUserId();
        this.walkId = walk.getId();
        this.courseCenter = walk.getCourseCenter();
        this.img = img;
//        this.strImg = walk.getImg();
        this.good = walk.getGood();
        this.bad = walk.getBad();
        this.startTime = walk.getStartTime();
        this.endTime = walk.getEndTime();
        if (checkGood != null) {
            this.checkGood = true;
        }
        if (checkBad != null) {
            this.checkBad = true;
        }
    }

//    public WalkTestDto(Long userId, String courseCenter, String img, LocalDateTime startTime, LocalDateTime endTime, Good good, Bad bad) {
//        this.userId = userId;
//        this.courseCenter = courseCenter;
//        this.img = img;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.good = good;
//        this.bad = bad;
//    }
}
