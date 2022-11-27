package com.interfacesv.demo.controller;

import com.interfacesv.demo.dto.UserDto;
import com.interfacesv.demo.dto.UserSimple;
import com.interfacesv.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserSimple>> findAllUser() throws IllegalArgumentException{
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<UserSimple> create(@RequestBody Map<String,String> param) {
        UserDto userDto = new UserDto(param.get("studentId"),
                param.get("name"),
                param.get("password"),
                param.get("email"),
                param.get("auth"),
                param.get("phone"),
                param.get("birthday"));
        userService.save(userDto);

        UserSimple userSimple = userService.findByStudentId(userDto.getStudentId()); //여기서 학번으로 찾을 수 있게 해줘야함!

        return ResponseEntity.ok(userSimple);// userSimple반환
    }

    // 여기서부터 구현!
    @PutMapping("/update")
    public ResponseEntity<UserSimple> update(@RequestBody Map<String,String> param) {
        UserDto userDto = new UserDto(param.get("studentId"),
                param.get("name"),
                param.get("password"),
                param.get("email"),
                param.get("auth"),
                param.get("phone"),
                param.get("birthday"));

        userService.update(userDto);
        UserSimple userSimple = userService.findByStudentId(userDto.getStudentId());

        return ResponseEntity.ok(userSimple);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("studentId")String studentId) {
        userService.delete(studentId);

        return ResponseEntity.ok().build();
    }
}
