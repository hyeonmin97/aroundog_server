package dogTrio.arounDog.controller;

import dogTrio.arounDog.dto.CoorDto;
import dogTrio.arounDog.dto.UserCoordinateDogDto;
import dogTrio.arounDog.service.CoordinateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CoordinateController {

    private final CoordinateService coordinateService;


    @PostMapping("/coor/insert")
    public Boolean insert(CoorDto coorDto) {
        coorDto.setWalkTime(true);
        return coordinateService.insertCoordinate(coorDto);
    }

    @GetMapping("/coor/update")
    public Boolean update(CoorDto coorDto) {
        coorDto.setWalkTime(true);
        return coordinateService.updateCoordinate(coorDto);

    }


    @GetMapping("/coor")
    public List<UserCoordinateDogDto> getList(@Valid @RequestParam String tile) {
        //타일을 기준으로 walking필드가 true인 Coordinate 가져오기
        return coordinateService.getAroundUser(tile);

    }

    @GetMapping("/coor/false")
    public Boolean setFalse(@RequestParam String userId) {
        return coordinateService.setFalse(userId);
    }

}
