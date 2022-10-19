package dogTrio.arounDog.service;

import dogTrio.arounDog.domain.Dog;
import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.UserDog;
import dogTrio.arounDog.dto.UserDogDto;
import dogTrio.arounDog.dto.UserDto;
import dogTrio.arounDog.repository.DogRepository;
import dogTrio.arounDog.repository.UserDogRepository;
import dogTrio.arounDog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DogService {

    private final UserRepository userRepository;
    private final DogRepository dogRepository;
    private final UserDogRepository userDogRepository;

    @Transactional
    public Long addDog(UserDogDto userDogDto) {
        Optional<User> findUser = userRepository.findByUserId(userDogDto.getUserId());
        if (findUser.isPresent()) {
            Dog dog = dogRepository.find(userDogDto.getDogId());
            if (dog != null) {
                UserDog userDog = new UserDog(findUser.get(), dog, userDogDto);
                userDogRepository.save(userDog);
                return userDog.getId();
            }
        }
        return -100L;
    }

    @Transactional
    public Boolean deleteDog(Long dogId) {
        UserDog findUserDog = userDogRepository.find(dogId);
        userDogRepository.remove(findUserDog);
        UserDog dog = userDogRepository.find(dogId);
        if (dog == null) {
            return true;
        }
        return false;
    }
}
