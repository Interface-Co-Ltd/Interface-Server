package com.interfacesv.demo.domain.schedule;

import com.interfacesv.demo.domain.Cooperation.Cooperation;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleRepositoryTest {
    @Autowired
    ScheduleRepository scheduleRepository;

    @After
    public void cleanUp(){
        scheduleRepository.deleteAll();
    }

    @Test
    public void 스케줄조회(){
        //given
        String div = "인페";
        String content = "개강총회";
        Long all_day = 1L;

        Schedule save = scheduleRepository.save(Schedule.builder()
                .div(div)
                .content(content)
                .all_day(all_day)
                .build());

        //when
        List<Schedule> scheduleList = scheduleRepository.findAll();

        //then
        Schedule schedule = scheduleList.get(0);

        assertThat(schedule.getDiv()).isEqualTo("인페");
        assertThat(schedule.getContent()).isEqualTo("개강총회");
        assertThat(schedule.getAll_day()).isEqualTo(1L);

    }
}