package com.interfacesv.demo.domain.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.interfacesv.demo.domain.BaseTimeEntity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@Entity
public class Schedule extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String div;

    @Column
    private String content;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime start_date;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime end_date;

    @Column
    private Long all_day;

    @Builder
    public Schedule(String div, String content, LocalDateTime start_date, LocalDateTime end_date, Long all_day) {
        this.div = div;
        this.content = content;
        this.start_date = start_date;
        this.end_date = end_date;
        this.all_day = all_day;
    }

    public void update(String div, String content, LocalDateTime start_date, LocalDateTime end_date, Long all_day) {
        this.div = div;
        this.content = content;
        this.start_date = start_date;
        this.end_date = end_date;
        this.all_day = all_day;
    }

}
