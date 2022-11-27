package com.interfacesv.demo.domain.image;

import com.interfacesv.demo.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, String> {
    List<Image> findAll();

    List<Image> findAllByBoard(Board board);
    //Image findAllByBoardId(String uuid);
}
