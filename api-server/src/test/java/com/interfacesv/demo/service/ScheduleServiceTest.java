package com.interfacesv.demo.service;

import com.interfacesv.demo.domain.schedule.Schedule;
import com.interfacesv.demo.domain.schedule.ScheduleRepository;
import com.interfacesv.demo.dto.ScheduleDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ScheduleServiceTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleService scheduleService;

    @Test
    public void 일정등록() {
        //given
        Long id = 1L;
        String div = "interface";
        String content = "개강총회";
        LocalDateTime start_date = LocalDateTime.now();
        LocalDateTime end_date = LocalDateTime.now();
        Long all_day = 1L;

        Schedule schedule = Schedule.builder()
                .div(div)
                .content(content)
                .start_date(start_date)
                .end_date(end_date)
                .all_day(all_day)
                .build();

        ScheduleDTO scheduleDTO = new ScheduleDTO(schedule);
        scheduleRepository.save(schedule);

        //when
        List<Schedule> scheduleList = scheduleRepository.findAll();
        Schedule schedule1 = scheduleList.get(0);

        //then
        assertThat(schedule1.getDiv()).isEqualTo(div);
        assertThat(schedule1.getContent()).isEqualTo(content);
        assertThat(schedule1.getStart_date()).isEqualTo(start_date);
        assertThat(schedule1.getEnd_date()).isEqualTo(end_date);

    }

}
