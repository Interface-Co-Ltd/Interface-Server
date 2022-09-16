package com.interfacesv.demo.domain.board;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @After
    public void cleanUp() {
        boardRepository.deleteAll();
    }

    @Test
    public void 저장조회() {
        String title = "제목";
        String content = "본문";
        String type = "공지사항";

        boardRepository.save(Board.builder()
                .title(title)
                .content(content)
                .type(type)
                .build());

        List<Board> boardList = boardRepository.findAll();
        Board board = boardList.get(0);

        assertThat(board.getTitle()).isEqualTo(title);
        assertThat(board.getContent()).isEqualTo(content);
        assertThat(board.getType()).isEqualTo(type);
    }
}
