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

//    @GetMapping("/user")
//    @Transactional
//    public User getUser() {
//        User user1 = new User("park", "1234", 20, 1, "010", "hye@naver.com", Gender.MAN);
//        em.persist(user1);
//
//        User findUser = em.find(User.class, 1L);
//        log.debug("findUser {}", findUser);
//        return findUser;
//    }

    @PostMapping("/user")
    public Long join(@RequestBody User user) {

        Long userId = userService.join(user);
        return userId;
    }



}

