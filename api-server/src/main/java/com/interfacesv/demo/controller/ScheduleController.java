package com.interfacesv.demo.controller;

import com.interfacesv.demo.domain.schedule.Schedule;
import com.interfacesv.demo.dto.ScheduleDTO;
import com.interfacesv.demo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(value = "/")
    public ResponseEntity<List<ScheduleDTO>> findAllSchedule() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ScheduleDTO> create(@RequestBody Map<String, String> param){
        Long id = Long.parseLong(param.get("id"));
        String div = param.get("div");
        String content = param.get("content");
        LocalDateTime start_date = LocalDateTime.parse(param.get("start_date"));
        LocalDateTime end_date = LocalDateTime.parse(param.get("end_date"));
        Long all_day = Long.parseLong(param.get("all_day"));

        Schedule schedule = new Schedule(id, div, content, start_date,end_date,all_day);
        ScheduleDTO scheduleDTO = new ScheduleDTO(schedule);

        scheduleService.saveSchedule(scheduleDTO);

        return ResponseEntity.ok(scheduleDTO);
    }
}
