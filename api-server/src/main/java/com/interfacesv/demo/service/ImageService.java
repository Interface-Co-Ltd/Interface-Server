package com.interfacesv.demo.service;

import com.interfacesv.demo.component.FileHandler;
import com.interfacesv.demo.domain.board.Board;
import com.interfacesv.demo.domain.image.Image;
import com.interfacesv.demo.domain.image.ImageRepository;
import com.interfacesv.demo.dto.ImageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ImageService {

    @Autowired
    private final ImageRepository imageRepository;

    private final FileHandler fileHandler = new FileHandler();



}
