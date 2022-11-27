package com.interfacesv.demo.dto;

import com.interfacesv.demo.domain.board.Board;
import com.interfacesv.demo.domain.image.Image;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BoardDto {
    public final Long id;
    public final String title;
    public final String content;
    public final String type;
    public final String user;

    private List<ImageDto> images;

    public final String created_date;

    public final String modified_date;

    @Builder
    public BoardDto(Board board, List<ImageDto> imageDtoList) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.type = board.getType();
        this.user = board.getUser().getStudentId();
        this.images = imageDtoList;
        this.created_date = board.getCreated_date().toString();
        this.modified_date = board.getModified_date().toString();
    }

    public List<ImageDto> toDtoList(List<Image> imageList){
        List<ImageDto> imageDtoList = new ArrayList<>();

        for(Image image1: imageList){
            imageDtoList.add(new ImageDto(image1));
        }

        return imageDtoList;
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

    public BoardDto from(Board board, List<ImageDto> imageDtoList) {
        return new BoardDto(board, imageDtoList);
    }
}
