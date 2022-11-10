package dogTrio.arounDog.dto;

import lombok.Getter;

import java.util.Optional;

@Getter
public class ImgDto {
    private Long id;
    private String path;
    private String fileName;
    public ImgDto(Long id, String path, String fileName) {
        this.id = id;
        this.path = path;
        this.fileName = fileName;
    }
}

