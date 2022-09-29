package com.example.codebase.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

@Component
@RequiredArgsConstructor
@Slf4j
@Getter
public class OpenApiUtil {

    private String DATA_GO_KR_API_KEY = "uU1G9QTy4mvQuTyMhriCiFA%2FsmElVfgsXTcxU4jvss2aLcvWWj9Z2oUtJZ3tEgUAyOORHpjlU3NHBWypB%2BmVeQ%3D%3D";

    private String DATA_GO_KR_API_KEY2 = "oByNod5bOO4W4mKFWzg5Z2olQYL388ETYV3XjY02BMMsYtzqEFT6AkRZjxxaR9uZTgLBKAdYwW3IccuDwPJpsA==";


    // 기준시간을 기준으로 전날 데이터를 가져올지 결정하는 메소드
    public LinkedHashMap getDateTime(String datePattern){
        // 기준 시간 (0500 부터 예보조회가 가능함)
        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(5, 0, 0));
        // 현재 시각
        LocalDateTime nowDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());

        String date, time;
        if (startDateTime.isBefore(nowDateTime)){
            // 오늘 0500 이전이면 전날 최신 데이터를 가져온다
            date = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(23, 0, 0))
                    .format(DateTimeFormatter.ofPattern(datePattern));
            time = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(23, 0, 0))
                    .format(DateTimeFormatter.ofPattern("HHmm"));
        } else {
            // 오늘 0500 이후이면 0500 데이터를 가져온다
            date = LocalDateTime.of(LocalDate.now(), LocalTime.of(05, 0, 0))
                    .format(DateTimeFormatter.ofPattern(datePattern));
            time = LocalDateTime.of(LocalDate.now(), LocalTime.of(05, 0, 0))
                    .format(DateTimeFormatter.ofPattern("HHmm"));
        }
        log.info("시각: " + date + ", " + time);
        LinkedHashMap result = new LinkedHashMap<>();
        result.put("date", date);
        result.put("time", time);
        return result;
    }

}