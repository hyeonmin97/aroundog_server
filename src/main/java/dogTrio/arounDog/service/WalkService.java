package dogTrio.arounDog.service;

import dogTrio.arounDog.domain.*;
import dogTrio.arounDog.dto.WalkButtonDto;
import dogTrio.arounDog.dto.WalkDto;
import dogTrio.arounDog.dto.WalkWithGoodBad;
import dogTrio.arounDog.repository.GoodBadRepository;
import dogTrio.arounDog.repository.UserRepository;
import dogTrio.arounDog.repository.WalkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WalkService {

    private final WalkRepository walkRepository;
    private final UserRepository userRepository;

    private final GoodBadRepository goodBadRepository;

    @Transactional
    public void addWalk(String userId, WalkDto walkDto, String path) {
        Optional<User> user = userRepository.findByUserId(userId);
        walkDto.setUserAndPath(user.get(), path);
        walkRepository.save(walkDto);
    }

    public List<WalkWithGoodBad> walkListOrderedByGood(String userId, String tile, int start, int size) {
        ArrayList<WalkWithGoodBad> walkWithGoodBadList = new ArrayList<>();

        //유저 검색
        Optional<User> findUser = userRepository.findByUserId(userId);

        //tile 설정
        ArrayList<String> tiles = getTiles(tile);

        //주변 산책로 검색
        List<Object[]> walkList = walkRepository.findWithTile(findUser.get().getId(), tiles, start, size);

        //object[0] : Walk / object[1] : Good / object[2] : Bad
        for (Object[] walk : walkList) {
            Walk findWalk = (Walk) walk[0];
            Good good = (Good) walk[1];
            Bad bad = (Bad) walk[2];

            byte[] byteImg = getImg(findWalk);

            walkWithGoodBadList.add(new WalkWithGoodBad(findWalk, byteImg, good, bad));
        }
        return walkWithGoodBadList;
    }

    @Transactional
    public void clickButton(WalkButtonDto walkButtonDto) {
        Optional<User> findUser = userRepository.findByUserId(walkButtonDto.getUserId());
        Walk findWalk = walkRepository.findOne(walkButtonDto.getWalkId());
        Button button;
        if (walkButtonDto.getButton().equals("good")) {
            button = new Good(findUser.get(), findWalk);
            findWalk.clickButton("good");

        } else {
            button = new Bad(findUser.get(), findWalk);
            findWalk.clickButton("bad");
        }
        goodBadRepository.save(button);
    }

    private static byte[] getImg(Walk findWalk) {
        BufferedImage img;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            File file = new File(findWalk.getImg());
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
        return byteImg;
    }

    public static ArrayList<String> getTiles(String tile) {
        ArrayList<String> tiles = new ArrayList<>();
        String[] latLng = tile.split(":");
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                String temp = Integer.parseInt(latLng[0]) + x +":"+(Integer.parseInt(latLng[1])+y);
                tiles.add(temp);
            }
        }
        return tiles;
    }
}
