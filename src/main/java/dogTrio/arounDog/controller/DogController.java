package dogTrio.arounDog.controller;

import dogTrio.arounDog.domain.DogImg;
import dogTrio.arounDog.dto.ImgDto;
import dogTrio.arounDog.dto.UserDogDto;
import dogTrio.arounDog.repository.DogImgRepository;
import dogTrio.arounDog.service.DogImgService;
import dogTrio.arounDog.service.DogService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/dog")
//    public List<UserDogDto> findUserDog(@RequestParam String userId) {
//
//    }

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
