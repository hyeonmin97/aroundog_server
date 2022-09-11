package dogTrio.arounDog.service;

import dogTrio.arounDog.domain.Coordinate;
import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.UserDog;
import dogTrio.arounDog.dto.CoorDto;
import dogTrio.arounDog.dto.UserCoordinateDogDto;
import dogTrio.arounDog.repository.CoordinateRepository;
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

    @Transactional
    public Boolean insertCoordinate(CoorDto coorDto) {
        //insert를 무조건 실행하지말고 테이블에 있는지 확인해야함
        Optional<User> findUser = userRepository.findByUserId(coorDto.getUserId());
        if (findUser.isPresent()) {
            Optional<Coordinate> existCoor = coordinateRepository.findOne(findUser.get());
            if (existCoor.isPresent()) {//있을때
                existCoor.get().updateCoor(coorDto.getLatitude(), coorDto.getLongitude(), coorDto.getTile(), coorDto.getWalking(), coorDto.getTimeStamp());

            } else { //없을때
                Coordinate coordinate = CoorDto.createCoordinate(findUser.get(), coorDto);
                coordinateRepository.save(coordinate);
            }
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean updateCoordinate(CoorDto coorDto) {
        Optional<User> findUser = userRepository.findByUserId(coorDto.getUserId());
        if (findUser.isPresent()) {
            Optional<Coordinate> coordinate = coordinateRepository.findOne(findUser.get());
            if (coordinate.isPresent()) {
                coordinate.get().updateCoor(coorDto.getLatitude(), coorDto.getLongitude(), coorDto.getTile(), coorDto.getWalking(), coorDto.getTimeStamp());
                return true;
            }
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
    public Boolean setFalse(String userId) {
        Optional<User> findUser = userRepository.findByUserId(userId);
        if (findUser.isPresent()) {
            Optional<Coordinate> findCoor = coordinateRepository.findOne(findUser.get());
            if (findCoor.isPresent()) {
                Coordinate coordinate = findCoor.get();
                coordinate.endWalking();
                return true;
            }
        }
        return false;
    }
}
