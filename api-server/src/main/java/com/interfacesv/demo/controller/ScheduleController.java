package com.interfacesv.demo.controller;

import com.interfacesv.demo.dto.ScheduleDto;
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

    @GetMapping(value = "")
    public ResponseEntity<List<ScheduleDto>> findAllSchedule() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ScheduleDto> create(@RequestBody Map<String, String> param){
        String div = param.get("div");
        String content = param.get("content");
        LocalDateTime start_date = LocalDateTime.parse(param.get("start_date"));
        LocalDateTime end_date = LocalDateTime.parse(param.get("end_date"));
        Long all_day = Long.parseLong(param.get("all_day"));

        ScheduleDto scheduleDto = new ScheduleDto(0L, div, content, start_date,end_date,all_day);

        scheduleDto = scheduleService.save(scheduleDto);

        return ResponseEntity.ok(scheduleDto);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ScheduleDto> update(@RequestBody Map<String, String> param){
        String idString = param.get("id");
        Long id = Long.parseLong(idString);
        String div = param.get("div");
        String content = param.get("content");
        String start_date_string = param.get("start_date");
        LocalDateTime start_date = LocalDateTime.parse(start_date_string);
        String end_date_string = param.get("end_date");
        LocalDateTime end_date = LocalDateTime.parse(end_date_string);
        String all_day_string = param.get("all_day");
        Long all_day = Long.parseLong(all_day_string);

        ScheduleDto scheduleDto = new ScheduleDto(id, div, content, start_date,end_date, all_day);

        scheduleDto = scheduleService.update(scheduleDto);

        return ResponseEntity.ok(scheduleDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id")Long id){
        scheduleService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/findById")
    public ResponseEntity<ScheduleDto> findById(@RequestParam("id")Long id){
        return ResponseEntity.ok(scheduleService.findById(id));
    }
}
