package dogTrio.arounDog.controller;

import dogTrio.arounDog.domain.DogImg;
import dogTrio.arounDog.dto.ImgDto;
import dogTrio.arounDog.dto.UpdateDogImageDto;
import dogTrio.arounDog.dto.UserDogDto;
import dogTrio.arounDog.dto.UserDogWithBreedDto;
import dogTrio.arounDog.repository.DogImgRepository;
import dogTrio.arounDog.service.DogImgService;
import dogTrio.arounDog.service.DogService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DogController {
    @Value("${basePath}")
    String basePath;
    private final DogService dogService;
    private final DogImgService dogImgService;

    @PostMapping("/dog")
    public Long addDog(UserDogDto userDogDto) {
        return dogService.addDog(userDogDto);
    }

    @PatchMapping("/dog/{dogId}")
    public boolean findDog(@PathVariable Long dogId, UserDogWithBreedDto userDogWithBreedDto) {
        return dogService.updateDogInfo(dogId, userDogWithBreedDto);
    }

//    @GetMapping("/dog")
//    public List<UserDogDto> findUserDog(@RequestParam String userId) {
//
//    }

    //    서버에서 해당 이미지만
    @ResponseBody
    @GetMapping("/image/{dogId}/{filename}")
    public byte[] getDogImg(@PathVariable Long dogId, @PathVariable String filename) throws IOException {
        String path = basePath + "dog/" + dogId + "/" + filename;

        return getImage(path);

    }

    @ResponseBody
    @GetMapping("/image/{dogId}")
    public byte[] getDogImg(@PathVariable Long dogId) throws IOException {
        File dir = new File(basePath + "dog/" + dogId);
        String[] list = dir.list();

        String path = basePath + "dog/" + dogId + "/" + list[0];

        return getImage(path);

    }

    private static byte[] getImage(String path) {
        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try{
            fis = new FileInputStream(path);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

        int readCount = 0;
        byte[] buffer = new byte[1024];
        byte[] fileArray = null;

        try{
            while((readCount = fis.read(buffer)) != -1){
                baos.write(buffer, 0, readCount);
            }
            fileArray = baos.toByteArray();
            fis.close();
            baos.close();
        } catch(IOException e){
            throw new RuntimeException("File Error");
        }
        return fileArray;

    }

    @PostMapping("/dogImg/{dogId}")
    public UpdateDogImageDto updateDogImage(@PathVariable("dogId") Long dogId, MultipartFile image) {
        return dogImgService.updateDogImage(dogId, image);
    }

    @DeleteMapping("/dogImg/{dogImgId}")
    public Boolean deleteDogImg(@PathVariable("dogImgId") Long dogImgId) {
        return dogImgService.deleteDogImgById(dogImgId);
    }

    @DeleteMapping("/dog/{dogId}")
    public Boolean deleteDog(@PathVariable("dogId") Long dogId) {
        return dogService.deleteDog(dogId);
    }

}
