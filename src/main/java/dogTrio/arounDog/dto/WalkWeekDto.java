package dogTrio.arounDog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WalkWeekDto {
    private Long second;
    private Long distance;

    private Long count;

    @Override
    public String toString() {
        return "WalkWeekDto{" +
                "second=" + second +
                ", distance=" + distance +
                ", count=" + count +
                '}';
    }
}
