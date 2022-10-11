package dogTrio.arounDog.dto;

import lombok.Getter;

import java.util.Optional;

@Getter
public class ImgDto {
    private Long id;
    private String path;
    private Optional<byte[]> img;

    public ImgDto(Long id, String path, Optional<byte[]> img) {
        this.id = id;
        this.path = path;
        this.img = img;
    }
}

