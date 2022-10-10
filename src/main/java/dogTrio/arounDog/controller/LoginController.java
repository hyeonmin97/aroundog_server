package dogTrio.arounDog.controller;

import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.UserDog;
import dogTrio.arounDog.dto.ImgDto;
import dogTrio.arounDog.dto.LoginDto;
import dogTrio.arounDog.dto.UserAndDogDto;
import dogTrio.arounDog.repository.DogImgRepository;
import dogTrio.arounDog.repository.UserRepository;
import dogTrio.arounDog.service.DogImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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
public class LoginController {

    private final UserRepository userRepository;
    private final DogImgService dogImgService;


    @GetMapping("/login")
    public List<UserAndDogDto> login(@Valid @RequestParam String userId, @Valid @RequestParam String password) {
        List<UserAndDogDto> result = new ArrayList<>();
        List<Object[]> findUserAndDog = userRepository.findUserAndDog(userId);
        if (findUserAndDog.size() > 0) {//유저를 찾으면
            for (Object[] objects : findUserAndDog) {
                User user = (User) objects[0];
                UserDog userDog = (UserDog) objects[1];
                if (password.equals(user.getPassword())) {//비밀번호 같으면
                    List<ImgDto> imgList = dogImgService.getImgList(userDog.getId());//강아지의 이미지들 추가
                    UserAndDogDto userAndDogDto = new UserAndDogDto(user, userDog, true);
                    userAndDogDto.addImgs(imgList);
                    result.add(userAndDogDto);
                } else {
                    return UserAndDogDto.loginFail();
                }
            }
            return result;
        }
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
