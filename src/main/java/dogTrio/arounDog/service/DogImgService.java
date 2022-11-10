package dogTrio.arounDog.service;

import dogTrio.arounDog.domain.DogImg;
import dogTrio.arounDog.domain.UserDog;
import dogTrio.arounDog.dto.ImgDto;
import dogTrio.arounDog.dto.UpdateDogImageDto;
import dogTrio.arounDog.repository.DogImgRepository;
import dogTrio.arounDog.repository.UserDogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DogImgService {

    private final DogImgRepository dogImgRepository;
    private final UserDogRepository userDogRepository;

    @Value("${baseImgPath}")
    String basePath;
    @Transactional
    public UpdateDogImageDto updateDogImage(Long userDogId, MultipartFile image) {
        UpdateDogImageDto updateDogImageDto = new UpdateDogImageDto();
        try {
            String path = basePath + "/" + userDogId;
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();//부모 디렉터리 없으면 부모까지 생성
            }

            //파일 저장
            //getName : 멀티파트 안의 이름
            //getOriginalFilename: 실제 파일 이름

            //파일의 이름으로 사용할 날짜
            String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd_HHmmss"));

            //파일 확장자 추출
            String extension = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));

            //파일이름 설정
            String fileName = dateStr + extension;
            String imagePath = path + "/" + fileName;
            File imageFile = new File(imagePath);
            image.transferTo(imageFile);

            //db에 업데이트
            UserDog userDog = userDogRepository.find(userDogId);
            DogImg dogImg = new DogImg(userDog, imagePath, fileName);
            dogImgRepository.save(dogImg);
            updateDogImageDto.setDogImgId(dogImg.getId());
            updateDogImageDto.setFilename(fileName);

        } catch (Exception e) {
            updateDogImageDto.setDogImgId(-100L);
            e.printStackTrace();
        } finally {
            return updateDogImageDto;
        }
    }

    @Transactional
    public boolean deleteDogImgById(Long dogImgId) {
        DogImg dogImg = dogImgRepository.findDogImg(dogImgId);
        return dogImgRepository.delete(dogImg);
    }

    public List<ImgDto> getImgList(Long dogId) {
        List<ImgDto> imgList = new ArrayList<>();
        List<DogImg> findDogImgs = dogImgRepository.findDogImgs(dogId);//이미지 경로 불러오기

        for (DogImg dogImg : findDogImgs) {
            Long id = dogImg.getId();
            String path = dogImg.getPath();
            String fileName = dogImg.getFileName();
            Optional<byte[]> img = getImg(path);//경로에 있는 이미지 저장

            ImgDto imgDto = new ImgDto(id, path, fileName, img);
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
                String extension = path.substring(path.lastIndexOf(".") + 1);

                img = ImageIO.read(file);
                ImageIO.write(img, extension, bos);
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
