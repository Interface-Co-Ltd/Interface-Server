package com.interfacesv.demo.service;

import com.interfacesv.demo.component.FileHandler;
import com.interfacesv.demo.domain.image.Image;
import com.interfacesv.demo.domain.image.ImageRepository;
import com.interfacesv.demo.dto.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService {

    @Autowired
    private final ImageRepository imageRepository;

    private final FileHandler fileHandler = new FileHandler();

    public void save(Image imageDto, List<MultipartFile> files) throws Exception{
        Image image = Image.builder()
                .fileName(imageDto.getUuid())
                .fileName(imageDto.getFileName())
                .uploadPath(imageDto.getUploadPath())
                .build();

        imageRepository.save(image);

        List<Image> imageList = fileHandler.parseFileInfo(files);

        List<ImageDto> imageDtos;
    }

}
