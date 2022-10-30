package com.interfacesv.demo.dto;

import com.interfacesv.demo.domain.board.Board;
import com.interfacesv.demo.domain.user.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
public class BoardDto {
    public final Long id;
    public final String title;
    public final String content;
    public final String type;
    public final String user;

    @Builder
    public BoardDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.type = board.getType();
        this.user = board.getUser().getStudentId();
    }

    public BoardDto(Long id, String title, String content, String type, String user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        this.user = user;
    }

    public BoardDto from(Board board) {
        return new BoardDto(board);
    }
}
