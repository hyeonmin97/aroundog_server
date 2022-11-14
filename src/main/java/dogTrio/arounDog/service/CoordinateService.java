package dogTrio.arounDog.service;

import dogTrio.arounDog.domain.Coordinate;
import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.UserDog;
import dogTrio.arounDog.dto.CoorDto;
import dogTrio.arounDog.dto.UserCoordinateDogDto;
import dogTrio.arounDog.repository.CoordinateRepository;
import dogTrio.arounDog.repository.UserDogRepository;
import dogTrio.arounDog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoordinateService {

    private final CoordinateRepository coordinateRepository;
    private final UserRepository userRepository;
    private final UserDogRepository userDogRepository;
    

    @Transactional
    public Boolean updateCoordinate(List<Long> dogIdList, CoorDto coorDto) {
        coorDto.setWalkTime(true);

        if (!dogIdList.isEmpty()) {
            //insert를 무조건 실행하지말고 테이블에 있는지 확인해야함
            for (Long dogId : dogIdList) {
                UserDog userDog = userDogRepository.find(dogId);
                if (userDog != null) {
                    Optional<Coordinate> findCoor = coordinateRepository.findByUserDog(userDog);

                    if (findCoor.isPresent()) {//이미 존재하면 업데이트
                        findCoor.get().updateCoor(coorDto.getLatitude(), coorDto.getLongitude(), coorDto.getTile(), coorDto.getWalking(), coorDto.getTimeStamp());
                    } else {//없으면 추가
                        Coordinate coordinate = CoorDto.createCoordinate(userDog, coorDto);
                        coordinateRepository.save(coordinate);
                    }
                } else {
                    return false;//userDog없으면 false
                }
            }
            return true;
        }
        return false;
    }

    @Transactional
    public List<UserCoordinateDogDto> getAroundUser(String tile) {
        List<UserCoordinateDogDto> userCoordinateDogDtoList = new ArrayList<>();
        List<Object[]> tileAndDogs = coordinateRepository.findTileAndDog(tile);
        for (Object[] tileAndDog : tileAndDogs) {
            Coordinate coordinate = (Coordinate) tileAndDog[0];
            UserDog userDog = (UserDog) tileAndDog[1];
            userCoordinateDogDtoList.add(new UserCoordinateDogDto(coordinate, userDog));
        }
        return userCoordinateDogDtoList;
    }

    @Transactional
    public Boolean setFalse(List<Long> dogIdList) {
        for (Long dogId : dogIdList) {
            UserDog userDog = userDogRepository.find(dogId);
            if (userDog != null) {
                Optional<Coordinate> findCoor = coordinateRepository.findByUserDog(userDog);

                if (findCoor.isPresent()) {
                    Coordinate coordinate = findCoor.get();
                    coordinate.endWalking();
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
