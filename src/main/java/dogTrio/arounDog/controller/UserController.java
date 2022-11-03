package dogTrio.arounDog.controller;

import dogTrio.arounDog.domain.Gender;
import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.dto.UserDto;
import dogTrio.arounDog.repository.UserRepository;
import dogTrio.arounDog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.standard.expression.MessageExpression;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Value("${spring.mail.username}")
    private String fromMail;
    @PostMapping("/user/register")
    public Boolean register(UserDto userDto) {
        User user = UserDto.createUser(userDto);
        String userId = userService.join(user);
        if (userId != null) {
            return true;
        }
        return false;
    }

    //유저 정보 수정
    @PatchMapping("/user/{userId}")
    public Boolean updateUser(@PathVariable String userId, UserDto userDto) {
        return userService.updateUser(userId, userDto);
    }

    //기피 강아지 종 확인
    @GetMapping("/hate/{userId}")
    public String getHateDog(@PathVariable String userId) {
        return userService.getHateDog(userId);
    }

    //기피 강아지 종 설정
    @PostMapping("/hate/{userId}")
    public Boolean setHateDog(@PathVariable String userId, String hateDog) {
        return userService.updateHateDog(userId, hateDog);
    }

    //아이디찾기
    private final JavaMailSender javaMailSender;

    @GetMapping("/user/findId")
    public Boolean findId(@Valid @RequestParam String userName, @Valid @RequestParam String userEmail) {
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        if (findUser.isPresent()) {
            String findName = findUser.get().getUserName();
            if (findName.equals(userName)) {
                if (findUser.get().getEmail() != null) {
                    //이메일 전송 코드
                    try {
                        sendMail(findUser, true);
                        return true;
                    }catch (Exception e ){
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }



    //비밀번호 찾기
    @GetMapping("/user/findPw")
    public Boolean findPw(@Valid @RequestParam String userId,@Valid @RequestParam String userName, @Valid @RequestParam String userEmail) {
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        if (findUser.isPresent()) {
            String findUserId = findUser.get().getUserId();
            String findUserName = findUser.get().getUserName();
            if (findUserId.equals(userId) && findUserName.equals(userName)) {
                if (findUser.get().getEmail() != null) {
                    //이메일 전송 코드
                    try {
                        sendMail(findUser, false);
                        return true;
                    }catch (Exception e ){
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }


    /**
     * 이메일 전송
     * @param findUser 유저
     * @param isId 아이디 찾기 여부
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    private void sendMail(Optional<User> findUser, Boolean isId) throws MessagingException, UnsupportedEncodingException {
        String message;
        String subject;
        if (isId) {
            message = findUser.get().getUserName() + "님의 아이디는 " + findUser.get().getUserId() + " 입니다.";
            subject = "[arounDog] 아이디 찾기 안내";
        }else {
            message = findUser.get().getUserName() + "님의 비밀번호는 " + findUser.get().getPassword() + " 입니다.";
            subject = "[arounDog] 비밀번호 찾기 안내";
        }

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        mimeMessageHelper.setFrom(new InternetAddress(fromMail, "arounDog", "utf-8"));//발신자
        mimeMessageHelper.setTo(findUser.get().getEmail());//수신자
        mimeMessageHelper.setSubject(subject);//제목
        mimeMessageHelper.setText(message);

        javaMailSender.send(mimeMessage);
    }
}

