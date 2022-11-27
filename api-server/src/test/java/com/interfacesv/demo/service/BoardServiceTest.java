package com.interfacesv.demo.service;

import com.interfacesv.demo.component.FileHandler;
import com.interfacesv.demo.domain.board.Board;
import com.interfacesv.demo.domain.board.BoardRepository;
import com.interfacesv.demo.domain.image.ImageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardServiceTest {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ImageRepository imageRepository;

    private final FileHandler fileHandler = new FileHandler();

    @Autowired
    BoardService boardService;

    @Before
    public void cleanUpBefore() {
        List<Board> boardList = boardRepository.findAll();

        for(Board boards: boardList){
            boardService.deleteBoardImageByBoardId(boards.getId());
        }

        boardRepository.deleteAll();
        imageRepository.deleteAll();
    }

    @Test
    public void 상품요약조회() {
        //given


        //when

        //then

    }
}