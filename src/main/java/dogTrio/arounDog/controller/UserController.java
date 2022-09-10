package dogTrio.arounDog.controller;

import dogTrio.arounDog.domain.Gender;
import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.dto.UserDto;
import dogTrio.arounDog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/user/register")
    public Boolean register(UserDto userDto) {
        User user = UserDto.createUser(userDto);
        String userId = userService.join(user);
        if (userId != null) {
            return true;
        }
        return false;
    }
}

