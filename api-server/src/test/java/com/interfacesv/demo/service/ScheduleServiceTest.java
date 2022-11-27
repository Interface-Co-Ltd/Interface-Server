package com.interfacesv.demo.service;

import com.interfacesv.demo.domain.schedule.Schedule;
import com.interfacesv.demo.domain.schedule.ScheduleRepository;
import com.interfacesv.demo.dto.ScheduleDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleServiceTest {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    ScheduleService scheduleService;

    @Before
    public void before(){
        scheduleRepository.deleteAll();

        //given
        Long id = 5L;
        String div = "세종대";
        String content = "중간고사";
        LocalDateTime start_date = LocalDateTime.now();
        LocalDateTime end_date = LocalDateTime.now();
        Long all_day = 5L;

        Schedule schedule = Schedule.builder()
                .div(div)
                .content(content)
                .start_date(start_date)
                .end_date(end_date)
                .all_day(all_day)
                .build();

        ScheduleDto scheduleDTO = new ScheduleDto(schedule);
        scheduleService.save(scheduleDTO);
    }

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

        ScheduleDto scheduleDTO = new ScheduleDto(schedule);
        System.out.println("schedule id : "+scheduleDTO.getId());
        scheduleService.save(scheduleDTO);

        //when
        List<ScheduleDto> scheduleDtoList = scheduleService.findAll();
        ScheduleDto schedule1 = scheduleDtoList.get(0);

        //then
        assertThat(schedule1.getDiv()).isEqualTo("세종대");
        assertThat(schedule1.getContent()).isEqualTo("중간고사");
        //assertThat(schedule1.getStart_date()).isEqualTo(start_date);
        //assertThat(schedule1.getEnd_date()).isEqualTo(end_date);
        System.out.println("Start_date() : "+start_date);
        System.out.println("End_date() : "+end_date);
    }

    @Test
    public void 일정업데이트(){
        //given
        String content = "기말고사";
        String div = "sejong";
        Schedule schedule = scheduleRepository.findAll().get(0);
        ScheduleDto newDto = new ScheduleDto(schedule.getId(), div, content, schedule.getStart_date()
        , schedule.getEnd_date(), schedule.getAll_day());

        scheduleService.update(newDto);

        //when
        List<ScheduleDto> scheduleDtoList = scheduleService.findAll();
        ScheduleDto schedule1 = scheduleDtoList.get(0);

        //then
        assertThat(schedule1.getDiv()).isEqualTo(div);
        assertThat(schedule1.getContent()).isEqualTo(content);
    }

}
