package dogTrio.arounDog.controller;

import dogTrio.arounDog.dto.CoorDto;
import dogTrio.arounDog.dto.UserCoordinateDogDto;
import dogTrio.arounDog.service.CoordinateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CoordinateController {

    private final CoordinateService coordinateService;



    @GetMapping("/coor/update")
    public Boolean update(@RequestParam List<Long> dogIdList, CoorDto coorDto) {
        return coordinateService.updateCoordinate(dogIdList, coorDto);
    }


    @GetMapping("/coor")
    public List<UserCoordinateDogDto> getList(@Valid @RequestParam String tile) {
        //타일을 기준으로 walking필드가 true인 Coordinate 가져오기
        return coordinateService.getAroundUser(tile);

    }

    @PostMapping("/coor/false")
    public Boolean setFalse(@RequestParam List<Long> dogIdList) {
        return coordinateService.setFalse(dogIdList);
    }

}
