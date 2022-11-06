package dogTrio.arounDog.controller;

import dogTrio.arounDog.domain.Walk;
import dogTrio.arounDog.dto.*;
import dogTrio.arounDog.service.UserService;
import dogTrio.arounDog.service.WalkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WalkController {

    private final UserService userService;
    private final WalkService walkService;

    @Value("${basePath}")
    String basePath;

    @GetMapping("/walk/{userId}") //사용자의 전체 산책 기록
    public List<Walk> userWalkList(@PathVariable Long userId) {
        List<Walk> walks = userService.userWalkList(userId);
        return walks;
    }

    @GetMapping("/walk/{userId}/{walkId}") //사용자 산책 상세정보
    public void walk(@PathVariable Long userId, @PathVariable Long walkId) {
//        User user = userService.findOne(userId);
//
//        return walks;
    }

    @PostMapping("/walk/{userId}/add")//산책 추가
    public void addWalk(@PathVariable String userId, @ModelAttribute WalkMultipartDto dto) throws IOException {
        String path = basePath + dto.getStartTime().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + "#" + userId + ".jpg";

        File temp = new File(path);
        dto.getImage().transferTo(temp);
        WalkDto walkDto = dto.makeWalkDto();
        walkService.addWalk(userId, walkDto, path);
    }

    //사용자 근처 산책로 추천순
    @GetMapping("/walk/good")
    public List<WalkWithGoodBad> walkListOrderByGood(@RequestParam String userId, @RequestParam String tile, @RequestParam int start, @RequestParam int size) {

        return walkService.walkListOrderedByGood(userId, tile, start, size);

    }


    //산책리스트 좋아요/싫어요 버튼 클릭
    @PostMapping("/walk/button")
    public void clickButton(WalkButtonDto walkButtonDto) {
        walkService.clickButton(walkButtonDto);
    }

    @GetMapping("/walk/week")
    public WalkWeekDto week(@RequestParam String userId) {
        WalkWeekDto walkWeekDto = walkService.walkWeekData(userId);
        return walkWeekDto;
    }

    @GetMapping("/walk/{userId}/allInfo")
    public AllInformationDto allInfo(@PathVariable String userId) {
        return walkService.getAllWalkData(userId);
    }
}
