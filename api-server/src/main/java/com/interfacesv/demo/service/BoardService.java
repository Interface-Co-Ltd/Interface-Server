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
    public BoardDto findById(Long id) {
        return new BoardDto(boardRepository.findById(id).get(), image.toDtoList(boardRepository.findById(id).get().getImages()));
    }

    @Transactional
    public List<BoardDto> findAll() {
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

    @Transactional
    public BoardDto save(BoardDto boardDto, List<MultipartFile> files) throws Exception {
        Board board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .type(boardDto.getType())
                .user(userRepository.findByStudentId(boardDto.getUser())
                        .orElseThrow(() -> new UsernameNotFoundException((boardDto.getUser())))).build();

        boardRepository.save(board);

        List<Image> list = fileHandler.parseFileInfo(board, files);

        List<ImageDto> pictureBeans;

        if(list.isEmpty()){
            return new BoardDto(board, null);
        }
        else{
            pictureBeans = new ArrayList<>();
            for(Image images: list){
                pictureBeans.add(new ImageDto(imageRepository.save(images)));
            }
        }

        BoardDto boardDtoResult = new BoardDto(board, pictureBeans);

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

    public void deleteBoardImageByBoardId(Long boardId){
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
