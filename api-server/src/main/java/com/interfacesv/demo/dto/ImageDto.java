package com.interfacesv.demo.dto;

import com.interfacesv.demo.domain.board.Board;
import lombok.Builder;
import lombok.Data;

@Data
public class ImageDto {

    public final String uuid;
    public final String uploadPath;
    public final String fileName;
    public final Board board;

    @Builder
    public ImageDto(String uuid, String uploadPath, String fileName, Board board){
        this.uuid = uuid;
        this.uploadPath = uploadPath;
        this.fileName = fileName;
        this.board = board;
    }
}
