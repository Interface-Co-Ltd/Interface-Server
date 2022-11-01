package com.interfacesv.demo.dto;

import com.interfacesv.demo.domain.board.Board;
import com.interfacesv.demo.domain.image.Image;
import lombok.Builder;
import lombok.Data;

@Data
public class ImageDto {

    public final String uuid;
    public final String uploadPath;
    public final String fileName;
    public final Board board;

    @Builder
    public ImageDto(Image image){
        this.uuid = image.getUuid();
        this.uploadPath = image.getUploadPath();
        this.fileName = image.getFileName();
        this.board = image.getBoard();
    }
}
