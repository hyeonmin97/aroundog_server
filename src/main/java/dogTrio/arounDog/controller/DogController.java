package dogTrio.arounDog.controller;

import dogTrio.arounDog.dto.UserDogDto;
import dogTrio.arounDog.service.DogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DogController {

    private final DogService dogService;

    @PostMapping("/dog")
    public Boolean addDog(UserDogDto userDogDto) {
        return dogService.addDog(userDogDto);
    }

//    @GetMapping("/dog")
//    public List<UserDogDto> findUserDog(@RequestParam String userId) {
//
//    }

    @DeleteMapping("/dog/{dogId}")
    public Boolean deleteDogImg(@PathVariable("dogId") Long dogId) {
        return dogImgService.deleteDogImgById(dogId);
    }
}
