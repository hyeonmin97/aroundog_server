package dogTrio.arounDog.dto;

import lombok.Getter;

import java.util.Optional;

@Getter
public class ImgDto {
    private String path;
    private Optional<byte[]> img;

    public ImgDto(String path, Optional<byte[]> img) {
        this.path = path;
        this.img = img;
    }
}

