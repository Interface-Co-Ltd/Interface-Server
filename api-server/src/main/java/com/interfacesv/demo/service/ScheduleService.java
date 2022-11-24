package com.interfacesv.demo.service;

import com.interfacesv.demo.domain.schedule.Schedule;
import com.interfacesv.demo.domain.schedule.ScheduleRepository;
import com.interfacesv.demo.dto.BoardDto;
import com.interfacesv.demo.dto.ScheduleDTO;
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
    public List<ScheduleDTO> findAll(){
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        List<Schedule> scheduleList = new ArrayList<>();

        for(Schedule schedules: scheduleList){
            scheduleDTOList.add(new ScheduleDTO(schedules));
        }

        return scheduleDTOList;
    }

    // 일정 create
    @Transactional
    public void saveSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = Schedule.builder()
                .id(scheduleDTO.getId())
                .div(scheduleDTO.getDiv())
                .content(scheduleDTO.getContent())
                .all_day(scheduleDTO.getAll_day())
                .start_date(scheduleDTO.getStart_date())
                .end_date(scheduleDTO.getEnd_date())
                .build();

        scheduleRepository.save(schedule);
    }

    //일정 update
    @Transactional
    public ScheduleDTO update(ScheduleDTO scheduleDTO){
        Schedule schedule = scheduleRepository.findById(scheduleDTO.getId()).get();
        schedule.update(scheduleDTO.getDiv(), scheduleDTO.getContent(), scheduleDTO.getStart_date(), scheduleDTO.getEnd_date(), scheduleDTO.getAll_day());

        return new ScheduleDTO(schedule);
    }

    //일정 delete

    //날짜별 일정 검색

}
