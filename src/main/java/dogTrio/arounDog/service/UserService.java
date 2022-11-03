package dogTrio.arounDog.service;

import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.Walk;
import dogTrio.arounDog.dto.UserDto;
import dogTrio.arounDog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String join(User user) {
//        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getUserId();
    }
//    @Transactional
//    public Long join(UserDto userdto) {
////        validateDuplicateUser(user);
//        userRepository.save(userdto);
//        return userdto.getId();
//    }

    public Optional<User> findOne(String userId) {
        return userRepository.findByUserId(userId);
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
    @Transactional
    public boolean updateUser(String userId, UserDto userDto) {
        try {
            Optional<User> findUser = userRepository.findByUserId(userId);
            if (findUser.isPresent()) {
                findUser.get().updateUser(userDto);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public String getHateDog(String id) {
        List<String> hateDogs = userRepository.findHateDog(id);
        if (!hateDogs.isEmpty()) {
            return hateDogs.get(0);
        }
        return "";
    }

    @Transactional
    public Boolean updateHateDog(String id, String hateDog) {
        Optional<User> findUser = userRepository.findByUserId(id);
        if (findUser.isPresent()) {
            findUser.get().updateHateDog(hateDog);
            return true;
        }
        return false;
    }
}
