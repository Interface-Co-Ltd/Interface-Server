package com.interfacesv.demo.service;

import com.interfacesv.demo.component.FileHandler;
import com.interfacesv.demo.domain.board.Board;
import com.interfacesv.demo.domain.board.BoardRepository;
import com.interfacesv.demo.domain.image.Image;
import com.interfacesv.demo.domain.image.ImageRepository;
import com.interfacesv.demo.domain.user.UserRepository;
import com.interfacesv.demo.dto.BoardDto;
import com.interfacesv.demo.dto.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    final private BoardRepository boardRepository;
    final private UserRepository userRepository;
    final private ImageRepository imageRepository;
    final private FileHandler fileHandler = new FileHandler();

    final private Image image = new Image();

    @Transactional
    public BoardDto findById(Long id) throws EntityNotFoundException {
        Board board = boardRepository.findById(id).get();
        List<Image> imageList = board.getImages();

        return new BoardDto(board, board.toDtoList(imageList));
    }

    @Transactional
    public List<BoardDto> findAll() throws EntityNotFoundException {
        List<BoardDto> boardDtoList = new ArrayList<>();
        List<Board> boardList = boardRepository.findAll();

        for(Board boards: boardList) {
            boardDtoList.add(new BoardDto(boards, image.toDtoList(boards.getImages())));
        }

        return boardDtoList;
    }

    @Transactional
    public List<BoardDto> findByStudentId(String studentId) {
        List<BoardDto> boardDtoList = new ArrayList<>();
        List<Board> boardList = boardRepository.findAll();

        for(int i=0;i<boardList.size();i++) {
            if(boardList.get(i).getUser().getStudentId().equals(studentId)) {
                boardDtoList.add(new BoardDto(boardList.get(i), image.toDtoList(boardList.get(i).getImages())));
            }
        }

        return boardDtoList;
    }

    // Exception handling 해줘야 함!
    @Transactional
    public BoardDto save(BoardDto boardDto) {
        Board board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .type(boardDto.getType())
                .user(userRepository.findByStudentId(boardDto.getUser())
                        .orElseThrow(() -> new UsernameNotFoundException((boardDto.getUser())))).build();

        boardRepository.save(board);

        BoardDto boardDtoResult = new BoardDto(board, new ArrayList<>());

        return boardDtoResult;
    }

    @Transactional
    public BoardDto update(BoardDto boardDto) {
        Board board = boardRepository.findById(boardDto.getId()).get();
        board.update(boardDto.getTitle(), boardDto.getContent(), boardDto.getType(), userRepository.findByStudentId(boardDto.getUser())
                .orElseThrow(() -> new UsernameNotFoundException((boardDto.getUser()))));

        List<Image> images = imageRepository.findAllByBoard(board);

        return new BoardDto(board, image.toDtoList(images));
    }

    @Transactional
    public List<ImageDto> saveImages(Long boardId, List<MultipartFile> multipartFileList) {
        Board board = boardRepository.findById(boardId).get();

        if(board == null) {
            List<ImageDto> emptyList = new ArrayList<>();
            System.out.println("test Board Entity Not found!\n");
            return emptyList;
        }

        List<Image> imageList;
        try {
            imageList = fileHandler.parseFileInfo(board, multipartFileList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(imageList.isEmpty()) {
            // TODO : 이미지가 하나도 없다면?.. http 요청때 BAD ACCESS가 뜨는가?
        }
        else {
            for(int i=0;i<imageList.size();i++) {
                imageRepository.save(imageList.get(i));
            }
        }

        return image.toDtoList(imageList);
    }

    @Transactional
    public void deleteBoardImageByBoardId(Long boardId) {
        Board board = boardRepository.getById(boardId);
        List<Image> imageList = imageRepository.findAllByBoard(board);

        if(imageList!=null) return;

        for(Image boardImage: imageList){
            String uploadPath = boardImage.getUploadPath();

            if(uploadPath == null || uploadPath == "") continue;

            File file = new File(uploadPath);
            file.delete();
            imageRepository.delete(boardImage);
        }

        return;
    }

    @Transactional
    public void deleteById(Long id) {
        deleteBoardImageByBoardId(id);
        boardRepository.deleteById(id);
    }
}
