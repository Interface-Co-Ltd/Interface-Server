package com.interfacesv.demo.controller;

import com.interfacesv.demo.domain.board.BoardRepository;
import com.interfacesv.demo.domain.image.ImageRepository;
import com.interfacesv.demo.dto.BoardDto;
import com.interfacesv.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardController {

    private final BoardService boardService;

    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;

    @GetMapping(value = "")
    public ResponseEntity<List<BoardDto>> findAllBoard() {
        return ResponseEntity.ok(boardService.findAll());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<BoardDto> create(@RequestBody Map<String,String> param) {
        String title = param.get("title");
        String content = param.get("content");
        String type = param.get("type");
        String studentId = param.get("studentId");

        BoardDto boardDto = new BoardDto(0L, title, content, type, studentId, "", ""); // id가 0L인 이유 : 어차피 사용안하고 자동지정이므로 쓰레기값을 아무것이나 넣어줌

        //boardDto = boardService.save(boardDto);  //이걸 우째야 될까여

        return ResponseEntity.ok(boardDto);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<BoardDto> update(@RequestBody Map<String,String> param) {
        String idString = param.get("id"); // Map 자료형으로 리퀘스트 바디를 받으려면 모든 값을 String으로 받아야하므로 일단 String으로 받고 Long으로 변환
        Long id = Long.parseLong(idString); // String to Long
        String title = param.get("title");
        String content = param.get("content");
        String type = param.get("type");
        String studentId = param.get("studentId");

        BoardDto boardDto = new BoardDto(id, title, content, type, studentId, "", "");

        boardDto = boardService.update(boardDto);

        return ResponseEntity.ok(boardDto);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> delete(@RequestParam("id")Long id) {
        boardService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/findByStudent")
    public ResponseEntity<List<BoardDto>> findByStudent(@RequestParam("studentId")String studentId) {
        return ResponseEntity.ok(boardService.findByStudentId(studentId));
    }

    @GetMapping(value = "/findById")
    public ResponseEntity<BoardDto> findById(@RequestParam("id")Long id) {
        return ResponseEntity.ok(boardService.findById(id));
    }
}
