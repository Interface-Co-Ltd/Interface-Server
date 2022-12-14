package com.interfacesv.demo.dto;

import com.interfacesv.demo.domain.schedule.Schedule;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ScheduleDto {
    private final Long id;
    private final String div;
    private final String content;
    private final String start_date;
    private final String end_date;
    private final Long all_day;

    @Builder
    public ScheduleDto(Schedule schedule){
        this.id = schedule.getId();
        this.div = schedule.getDiv();
        this.content = schedule.getContent();
        this.start_date = schedule.getStart_date();
        this.end_date = schedule.getEnd_date();
        this.all_day = schedule.getAll_day();
    }

    public ScheduleDto(Long id, String div, String content, String start_date, String end_date, Long all_day){
        this.id = id;
        this.div = div;
        this.content = content;
        this.start_date = start_date;
        this.end_date = end_date;
        this.all_day = all_day;
    }
}
