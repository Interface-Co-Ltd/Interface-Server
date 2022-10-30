package com.interfacesv.demo.domain.image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, String> {
    List<Image> findAll();
    Image findAllByBoardId(String uuid);
}
