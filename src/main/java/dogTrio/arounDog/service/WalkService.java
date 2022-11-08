package dogTrio.arounDog.service;

import dogTrio.arounDog.domain.*;
import dogTrio.arounDog.dto.*;
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
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WalkService {

    private final WalkRepository walkRepository;
    private final UserRepository userRepository;

    private final GoodBadRepository goodBadRepository;

    public WalkInfoDto getWalkInfo(Long walkId) {
        Walk findWalk = walkRepository.findOne(walkId);
        byte[] img = getImg(findWalk);
        WalkInfoDto walkInfoDto = new WalkInfoDto(findWalk, img);
        return walkInfoDto;
    }

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

    public AllInformationDto getAllWalkData(String userId) {
        //토탈 데이터 불러와서 토탈 저장하고 월별로 나눠줄까? 아니면 쿼리를 따로 날릴까
        //일단 한번에
        List<Walk> findIdList = walkRepository.findById(userId);
        if (!findIdList.isEmpty()) {
            Long[] summaryTotalData = new Long[3];//시간, 거리, 횟수
            LinkedHashMap<YearMonth, Long[]> summaryMonthData = new LinkedHashMap<>();
            LinkedHashMap<YearMonth, ArrayList<MonthInformationDto>> allMonthData = new LinkedHashMap<>();

            summaryTotalData[0] = Long.valueOf(0);
            summaryTotalData[1] = Long.valueOf(0);
            summaryTotalData[2] = Long.valueOf(0);
            for (Walk walk : findIdList) {
                //전체
                summaryTotalData[0] += walk.getSecond();
                summaryTotalData[1] += walk.getDistance();
                summaryTotalData[2] += 1;

                //월 계산
                LocalDateTime startTime = walk.getStartTime();//산책기록의 시간
                YearMonth yearMonth = YearMonth.of(startTime.getYear(), startTime.getMonth());
                if (!summaryMonthData.containsKey(yearMonth)) {
                    summaryMonthData.put(yearMonth, new Long[]{walk.getSecond(), walk.getDistance(), 1L});
                } else {
                    Long[] data = summaryMonthData.get(yearMonth);
                    data[0] += walk.getSecond();
                    data[1] += walk.getDistance();
                    data[2] += 1;
                }

                if (!allMonthData.containsKey(yearMonth)) {
                    ArrayList<MonthInformationDto> monthInformationDtos = new ArrayList<>();
                    monthInformationDtos.add(new MonthInformationDto(walk.getId(), walk.getStartTime(), walk.getEndTime(), walk.getSecond(), walk.getDistance()));
                    allMonthData.put(yearMonth, monthInformationDtos);
                } else {
                    allMonthData.get(yearMonth).add(new MonthInformationDto(walk.getId(), walk.getStartTime(), walk.getEndTime(), walk.getSecond(), walk.getDistance()));
                }
                //반복문 돌때마다 년도, 월 비교해서 달라지면 변수에 저장하고
                //년,월 같으면 allMonthData의 리스트에 추가하면 됨

            }
            return new AllInformationDto(summaryTotalData, summaryMonthData, allMonthData, true);
        } else {
            return AllInformationDto.walkDataIsEmpty();
        }
    }

    public WalkWeekDto walkWeekData(String userId){
        LocalDateTime now = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999999999);//오늘의 마지막 시간
        LocalDateTime past = now.minusDays(7).withHour(0).withMinute(0).withSecond(0).withNano(0);//7일 전 날짜는 시간에 상관없이 같은 날짜면 다 불러올수 있도록 시간, 분 초기화
        List<WalkWeekDto> weekData = walkRepository.findWeekData(userId, past, now);
        return weekData.get(0);
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
