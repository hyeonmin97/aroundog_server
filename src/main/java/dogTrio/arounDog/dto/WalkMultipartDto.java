package dogTrio.arounDog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dogTrio.arounDog.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;
import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class WalkMultipartDto {
    @Lob
    private String course;
    private String courseCenter;

    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime endTime;

    private MultipartFile image;

    private String tile;

    private Long second;

    private Long distance;

    public WalkDto makeWalkDto() {
        WalkDto walkDto = WalkDto.makeWalkDto(course, courseCenter, startTime, endTime, tile, second, distance);
        return walkDto;
    }
}
