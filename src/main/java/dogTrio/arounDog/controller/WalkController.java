package dogTrio.arounDog.controller;

import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.Walk;
import dogTrio.arounDog.dto.WalkButtonDto;
import dogTrio.arounDog.dto.WalkDto;
import dogTrio.arounDog.dto.WalkListDto;
import dogTrio.arounDog.dto.WalkMultipartDto;
import dogTrio.arounDog.service.UserService;
import dogTrio.arounDog.service.WalkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WalkController {

    private final UserService userService;
    private final WalkService walkService;

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
        String path = "C:/Image/" + dto.getStartTime().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + "#" + userId+".jpg";
        File temp = new File(path);
        dto.getImage().transferTo(temp);
        WalkDto walkDto = dto.makeWalkDto();
        walkService.addWalk(userId, walkDto, path);
    }

    //주변 산책로 추천순
    @GetMapping("/walk/good")
    public List<WalkListDto> walkListOrderByGood(@RequestParam int start, @RequestParam int size) {
        ArrayList<WalkListDto> walkListDtos = new ArrayList<WalkListDto>();
        List<Walk> walkListOrderedByGood = walkService.getWalkListOrderedByGood(start, size);
        for (Walk walk : walkListOrderedByGood) {
            //파일 가져오기
            BufferedImage img;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                File file = new File(walk.getImg());
                if (file.exists()) {
                    img = ImageIO.read(file);
                    ImageIO.write(img, "jpg", bos);
                    bos.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] byteImg = bos.toByteArray();
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            walkListDtos.add(new WalkListDto(walk.getId(), walk.getUserId().getUserId(), walk.getCourseCenter(), walk.getGood(), walk.getBad(), byteImg, walk.getStartTime(), walk.getEndTime()));
        }
        return walkListDtos;
    }

    //산책리스트 좋아요/싫어요 버튼 클릭
    @PostMapping("/walk/button")
    public void clickButton(WalkButtonDto walkButtonDto) {
        walkService.clickButton(walkButtonDto);
    }
}
