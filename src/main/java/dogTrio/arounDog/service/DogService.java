package dogTrio.arounDog.service;

import dogTrio.arounDog.domain.Coordinate;
import dogTrio.arounDog.domain.Dog;
import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.UserDog;
import dogTrio.arounDog.dto.UserDogDto;
import dogTrio.arounDog.dto.UserDogWithBreedDto;
import dogTrio.arounDog.dto.UserDto;
import dogTrio.arounDog.repository.CoordinateRepository;
import dogTrio.arounDog.repository.DogRepository;
import dogTrio.arounDog.repository.UserDogRepository;
import dogTrio.arounDog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DogService {

    private final UserRepository userRepository;
    private final DogRepository dogRepository;
    private final UserDogRepository userDogRepository;
    private final CoordinateRepository coordinateRepository;

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
        Optional<Coordinate> findCoor = coordinateRepository.findByUserDog(findUserDog);
        if (findCoor.isPresent()) {
            coordinateRepository.delete(findCoor.get());
        }
        userDogRepository.remove(findUserDog);
        UserDog dog = userDogRepository.find(dogId);
        if (dog == null) {
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updateDogInfo(Long id, UserDogWithBreedDto userDogWithBreedDto) {
        try {
            List<UserDog> findUserDogList = userDogRepository.findWithDog(id);//?????? ?????????
            for (UserDog userDog : findUserDogList) {
                Dog updateBreed = dogRepository.find(userDogWithBreedDto.getBreed());//????????? ???
                userDog.updateData(userDogWithBreedDto, updateBreed);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
