package dogTrio.arounDog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MonthInformationDto {
    private Long walkId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long second;
    private Long distance;
}
