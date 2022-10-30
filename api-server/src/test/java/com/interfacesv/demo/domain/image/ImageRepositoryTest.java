package com.interfacesv.demo.domain.image;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageRepositoryTest {

    @Autowired
    ImageRepository imageRepository;

    @After
    public void cleanUp(){
        imageRepository.deleteAll();
    }

    @Test
    public void 저장조회() {
        String uuid = "test_uuid";
        String uploadPath = "upload_test";
        String fileName = "fileName";

        imageRepository.save(Image.builder()
                        .uuid(uuid)
                        .uploadPath(uploadPath)
                        .fileName(fileName)
                        .build());

        List<Image> imageList = imageRepository.findAll();
        Image image = imageList.get(0);

        assertThat(image.getUuid()).isEqualTo(uuid);
        assertThat(image.getFileName()).isEqualTo(fileName);
        assertThat(image.getUploadPath()).isEqualTo(uploadPath);
    }

}