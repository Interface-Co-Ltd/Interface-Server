package com.interfacesv.demo.dto;

import com.interfacesv.demo.domain.board.Board;
import com.interfacesv.demo.domain.user.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Data
public class BoardDto {
    public final Long id;
    public final String title;
    public final String content;
    public final String type;
    public final String user;

    public final String created_date;

    public final String modified_date;

    @Builder
    public BoardDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.type = board.getType();
        this.user = board.getUser().getStudentId();
        this.created_date = board.getCreated_date().toString();
        this.modified_date = board.getModified_date().toString();
    }

    public BoardDto(Long id, String title, String content, String type, String user, String created_date, String modified_date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        this.user = user;
        this.created_date = created_date;
        this.modified_date = modified_date;
    }

    public BoardDto from(Board board) {
        return new BoardDto(board);
    }
}
