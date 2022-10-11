package dogTrio.arounDog.controller;

import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.UserDog;
import dogTrio.arounDog.dto.ImgDto;
import dogTrio.arounDog.dto.LoginDto;
import dogTrio.arounDog.dto.UserAndDogDto;
import dogTrio.arounDog.repository.DogImgRepository;
import dogTrio.arounDog.repository.UserDogRepository;
import dogTrio.arounDog.repository.UserRepository;
import dogTrio.arounDog.service.DogImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LoginController {

    private final UserRepository userRepository; //간단한 작업이라 비스에 추가하지 않음
    private final DogImgService dogImgService;
    private final UserDogRepository userDogRepository; //간단한 작업이라 서비스 추가하지 않음

    @GetMapping("/login")

    public List<UserAndDogDto> login(@Valid @RequestParam String userId, @Valid @RequestParam String password) {
        List<UserAndDogDto> result = new ArrayList<>();

        Optional<User> findUser = userRepository.findByUserId(userId);
        if (findUser.isPresent()) {
            if(findUser.get().getPassword().equals(password)) {//비밀번호 일치
                //강아지 찾기
                List<UserDog> findUserDogList = userDogRepository.findByUserId(findUser.get());

                if (findUserDogList.isEmpty()) {//강아지가 없을때
                    UserAndDogDto userAndDogDto = new UserAndDogDto(findUser.get(), true);
                    result.add(userAndDogDto);
                } else {//강아지가 있을때
                    for (UserDog userDog : findUserDogList) {
                        List<ImgDto> imgList = dogImgService.getImgList(userDog.getId());
                        UserAndDogDto userAndDogDto = new UserAndDogDto(findUser.get(), userDog, true);
                        userAndDogDto.addImgs(imgList);
                        result.add(userAndDogDto);
                    }
                }
                return result;
            }
        }
        //findUser 없을때, 비밀번호 일치하지 않을때
        return UserAndDogDto.loginFail();
    }

    @GetMapping("/login/idValidate")
    public Boolean idValidate(@Valid @RequestParam String userId) {
        Optional<User> findUser = userRepository.findByUserId(userId);
        if (findUser.isPresent()) {
            return true;
        }
        return false;
    }


}
