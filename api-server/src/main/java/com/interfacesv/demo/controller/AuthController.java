package com.interfacesv.demo.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.interfacesv.demo.component.JwtTokenProvider;
import com.interfacesv.demo.domain.user.User;
import com.interfacesv.demo.dto.LoginUserDto;
import com.interfacesv.demo.dto.UserDto;
import com.interfacesv.demo.service.UserDetailsServiceImpl;
import com.interfacesv.demo.service.UserService;
import com.interfacesv.demo.service.messageService.FCMService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final FCMService fcmService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody Map<String,String> param, HttpServletResponse response) {
        String studentId = param.get("studentId");
        String password = param.get("password");
        String fcmToken = param.get("fcmToken");

        LoginUserDto loginUserDto = new LoginUserDto(studentId, password, fcmToken);

        //User Initial TEST CODE
        if(userService.findByStudentId(studentId).getStudentId().equals("")) {
            UserDto userDto = new UserDto(studentId, "INTERFACE_ADMIN", password, studentId + "@sju.ac.kr", "ROLE_ADMIN", "010-1111-1111", "11-11-11");
            userService.save(userDto);
        }

        User user = userService.login(loginUserDto);
        String checkStudentId = user.getStudentId();
        String role = user.getAuth();

        String token = jwtTokenProvider.createToken(checkStudentId, role);
        response.setHeader("JWT", token);

        String token_json = "{ \"token\": \""+token+"\"}";

        fcmService.saveToken(loginUserDto);

        //login alarm
        //fcmService.sendNewNoticePosted("Login Alarm", "로그인에 성공하였습니다!");

        return ResponseEntity.ok(token_json);
    }
}
