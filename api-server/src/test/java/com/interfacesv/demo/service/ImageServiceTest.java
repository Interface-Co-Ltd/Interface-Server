package com.interfacesv.demo.service;

import com.interfacesv.demo.domain.image.ImageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ImageService imageService;

    @Test
    public void findAllByBoard() {
    }

    @Test
    public void findByUuid() {
    }
}