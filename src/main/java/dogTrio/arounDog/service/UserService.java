package dogTrio.arounDog.service;

import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.Walk;
import dogTrio.arounDog.dto.UserDto;
import dogTrio.arounDog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user) {
//        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }
//    @Transactional
//    public Long join(UserDto userdto) {
////        validateDuplicateUser(user);
//        userRepository.save(userdto);
//        return userdto.getId();
//    }

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    public List<Walk> userWalkList(Long id) {
        return userRepository.userWalkList(id);
    }

    private void validateDuplicateUser(User user) {
        List<User> findUsers = userRepository.findByPhone(user.getPhone());
        if (!findUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
