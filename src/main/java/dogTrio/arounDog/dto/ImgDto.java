package dogTrio.arounDog.dto;

import lombok.Getter;

import java.util.Optional;

@Getter
public class ImgDto {
    private Long id;
    private String path;
    private String fileName;
    private Optional<byte[]> img;

    public ImgDto(Long id, String path, String fileName, Optional<byte[]> img) {
        this.id = id;
        this.path = path;
        this.fileName = fileName;
        this.img = img;
    }
}

