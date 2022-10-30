package com.interfacesv.demo.dto;

import com.interfacesv.demo.domain.image.Image;
import lombok.Getter;

@Getter
public class ImageResponseDto {
    private String fileId;

    public ImageResponseDto(Image entity){
        this.fileId = entity.getUuid();
    }

}
