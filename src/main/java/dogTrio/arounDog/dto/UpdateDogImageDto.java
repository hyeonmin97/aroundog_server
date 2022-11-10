package dogTrio.arounDog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateDogImageDto {
    Long dogImgId;
    String filename;
}
