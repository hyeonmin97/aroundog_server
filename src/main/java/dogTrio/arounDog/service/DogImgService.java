package dogTrio.arounDog.service;

import dogTrio.arounDog.domain.DogImg;
import dogTrio.arounDog.dto.ImgDto;
import dogTrio.arounDog.repository.DogImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DogImgService {

    private final DogImgRepository dogImgRepository;

    @Transactional
    public List<ImgDto> getImgList(Long dogId) {
        List<ImgDto> imgList = new ArrayList<>();
        List<DogImg> findDogImgs = dogImgRepository.findDogImgs(dogId);//이미지 경로 불러오기

        for (DogImg dogImg : findDogImgs) {
            String path = dogImg.getPath();
            Optional<byte[]> img = getImg(path);//경로에 있는 이미지 저장

            ImgDto imgDto = new ImgDto(path, img);
            imgList.add(imgDto);
        }
        return imgList;
    }
    private Optional<byte[]> getImg(String path) {
        BufferedImage img;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] byteImg = null;
        try {
            File file = new File(path);
            if (file.exists()) {
                img = ImageIO.read(file);
                ImageIO.write(img, "jpg", bos);
                bos.flush();
                byteImg = bos.toByteArray();
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        try {
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(byteImg);
    }
}
