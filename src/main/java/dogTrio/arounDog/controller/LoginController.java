package dogTrio.arounDog.controller;

import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.dto.LoginDto;
import dogTrio.arounDog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final UserRepository userRepository;


    @GetMapping("/login")
    public LoginDto login(@Valid @RequestParam String userId, @Valid @RequestParam String password) {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isPresent()) {
            User findUser = user.get();
            if (findUser.getPassword().equals(password)) {
                return new LoginDto(findUser, true);
            }
        }
        return LoginDto.loginFalse();
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
