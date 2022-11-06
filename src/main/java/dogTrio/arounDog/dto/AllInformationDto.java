package dogTrio.arounDog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Getter
@AllArgsConstructor
public class AllInformationDto {

    //시간, 거리, 횟수
    private Long[] summaryTotalData;

    //월별 요약 데이터
    //월, 요약정보
    private LinkedHashMap<YearMonth, Long[]> summaryMonthData;

    //전체 데이터
    // 5월, 5월 산책 리스트
    private LinkedHashMap<YearMonth, ArrayList<MonthInformationDto>> monthData;

    private boolean hasWalkData;

    private AllInformationDto(boolean hasWalkData) {
        this.hasWalkData = hasWalkData;
    }

    public static AllInformationDto walkDataIsEmpty() {
        AllInformationDto dto = new AllInformationDto(false);
        return dto;
    }
}
