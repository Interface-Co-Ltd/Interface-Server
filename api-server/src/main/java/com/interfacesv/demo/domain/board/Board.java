package com.interfacesv.demo.domain.board;

import com.interfacesv.demo.domain.BaseTimeEntity.BaseTimeEntity;
import com.interfacesv.demo.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(length = 6000)
    private String content;

    @Column
    private String type;

    @OneToOne
    @JoinColumn(name = "user") //Board 테이블에서의 칼럼명 : user
    private User user;

    @Builder
    public Board(String title, String content, String type, User user) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.user = user;
    }

    public void update(String title, String content, String type, User user) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.user = user;
    }

}
