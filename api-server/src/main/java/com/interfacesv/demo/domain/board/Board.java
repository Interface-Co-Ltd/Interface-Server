package com.interfacesv.demo.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(length = 6000)
    private String content;

    @Column
    private String type;

    @Builder
    public Board(String title, String content, String type) {
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public void update(String title, String content, String type) {
        this.title = title;
        this.content = content;
        this.type = type;
    }

}
