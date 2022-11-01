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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DogController {

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
