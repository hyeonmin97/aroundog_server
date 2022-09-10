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

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final UserRepository userRepository;


    @GetMapping("/login")
    public LoginDto login(@Valid @RequestParam Long id, @Valid @RequestParam String password) {
        User findUser = userRepository.findOne(id);
        if (findUser.getPassword().equals(password)) {
            return new LoginDto(findUser, true);
        }
        return new LoginDto(findUser, false);
    }

    @GetMapping("/login/idValidate")
    public Boolean idValidate(@Valid @RequestParam Long id) {
        Optional<User> user = Optional.ofNullable(userRepository.findOne(id));
        if()
    }
}
