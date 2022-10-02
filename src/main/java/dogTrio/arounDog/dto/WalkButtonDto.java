package dogTrio.arounDog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WalkButtonDto {
    private String userId;
    private Long walkId;
    private String button;
}
