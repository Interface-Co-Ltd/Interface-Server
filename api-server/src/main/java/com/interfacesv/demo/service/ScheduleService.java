package com.interfacesv.demo.service;

import com.interfacesv.demo.domain.schedule.Schedule;
import com.interfacesv.demo.domain.schedule.ScheduleRepository;
import com.interfacesv.demo.dto.ScheduleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    @Autowired
    final private ScheduleRepository scheduleRepository;

    @Transactional
    public List<ScheduleDto> findAll(){
        List<ScheduleDto> scheduleDtoList = new ArrayList<>();
        List<Schedule> scheduleList = scheduleRepository.findAll();

        for(Schedule schedules: scheduleList){
            scheduleDtoList.add(new ScheduleDto(schedules));
        }

        return scheduleDtoList;
    }

    // 일정 create
    @Transactional
    public ScheduleDto save(ScheduleDto scheduleDto){
        Schedule schedule = Schedule.builder()
                .div(scheduleDto.getDiv())
                .content(scheduleDto.getContent())
                .all_day(scheduleDto.getAll_day())
                .start_date(scheduleDto.getStart_date())
                .end_date(scheduleDto.getEnd_date())
                .build();

        schedule = scheduleRepository.save(schedule);

        return new ScheduleDto(schedule);
    }

    //일정 update
    @Transactional
    public ScheduleDto update(ScheduleDto scheduleDto){
        Schedule schedule = scheduleRepository.findById(scheduleDto.getId()).get();
        schedule.update(scheduleDto.getDiv(), scheduleDto.getContent(), scheduleDto.getStart_date(), scheduleDto.getEnd_date(), scheduleDto.getAll_day());

        return new ScheduleDto(schedule);
    }

    //일정 delete
    @Transactional
    public void deleteById(Long id){
        scheduleRepository.deleteById(id);
    }

    //일정 검색
    @Transactional
    public ScheduleDto findById(Long id){
        Schedule schedule = scheduleRepository.getById(id);
        ScheduleDto scheduleDto = new ScheduleDto(schedule);

        return scheduleDto;
    }
}
