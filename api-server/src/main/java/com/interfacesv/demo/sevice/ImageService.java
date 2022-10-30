package com.interfacesv.demo.sevice;

import com.interfacesv.demo.domain.image.Image;
import com.interfacesv.demo.domain.image.ImageRepository;
import com.interfacesv.demo.dto.ImageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ImageService {

    @Autowired
    private final ImageRepository imageRepository;

    @Transactional(readOnly = true)
    public List<ImageResponseDto> findAllByBoard(String boardId){
        List<Image> imageList = imageRepository.findAllById(Collections.singleton(boardId));

        return imageList.stream()
                .map(ImageResponseDto::new)
                .collect(Collectors.toList());
    }

    public Image findByUuid(String uuid){
        Image image = imageRepository.findAllByBoardId(uuid);

        return image;
    }
}
