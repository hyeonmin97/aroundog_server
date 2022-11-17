package dogTrio.arounDog.dto;

import dogTrio.arounDog.domain.Bad;
import dogTrio.arounDog.domain.Good;
import dogTrio.arounDog.domain.Walk;
import dogTrio.arounDog.domain.WalkDeduplication;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class WalkWithGoodBad {

    private Long walkId;

    private byte[] img;

    private Integer good;
    private Integer bad;
    private Long second;
    private Long distance;

    private Boolean checkGood = false;
    private Boolean checkBad = false;

    public WalkWithGoodBad(WalkDeduplication walk, byte[] img, Good checkGood, Bad checkBad) {
        this.walkId = walk.getId();
        this.img = img;
        this.good = walk.getGood();
        this.bad = walk.getBad();
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
