package com.interfacesv.demo.service;

import com.interfacesv.demo.domain.board.Board;
import com.interfacesv.demo.domain.board.BoardRepository;
import com.interfacesv.demo.domain.user.UserRepository;
import com.interfacesv.demo.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    final private BoardRepository boardRepository;
    final private UserRepository userRepository;

    @Transactional
    public BoardDto findById(Long id) {
        return new BoardDto(boardRepository.findById(id).get());
    }

    @Transactional
    public List<BoardDto> findAll() {
        List<BoardDto> boardDtoList = new ArrayList<>();
        List<Board> boardList = boardRepository.findAll();

        for(int i=0;i<boardList.size();i++) {
            boardDtoList.add(new BoardDto(boardList.get(i)));
        }

        return boardDtoList;
    }

    @Transactional
    public List<BoardDto> findByStudentId(String studentId) {
        List<BoardDto> boardDtoList = new ArrayList<>();
        List<Board> boardList = boardRepository.findAll();

        for(int i=0;i<boardList.size();i++) {
            if(boardList.get(i).getUser().getId().equals(studentId))
                boardDtoList.add(new BoardDto(boardList.get(i)));
        }

        return boardDtoList;
    }

    @Transactional
    public BoardDto save(BoardDto boardDto) {
        boardRepository.save(Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .type(boardDto.getType())
                .user(userRepository.findByStudentId(boardDto.getUser())
                        .orElseThrow(() -> new UsernameNotFoundException((boardDto.getUser())))).build());

        return boardDto;
    }

    @Transactional
    public BoardDto update(BoardDto boardDto) {
        Board board = boardRepository.findById(boardDto.getId()).get();
        board.update(boardDto.getTitle(), boardDto.getContent(), boardDto.getType(), userRepository.findByStudentId(boardDto.getUser())
                .orElseThrow(() -> new UsernameNotFoundException((boardDto.getUser()))));

        return new BoardDto(board);
    }

    @Transactional
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}
