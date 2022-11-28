package com.interfacesv.demo.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.interfacesv.demo.component.JwtTokenProvider;
import com.interfacesv.demo.domain.user.User;
import com.interfacesv.demo.dto.LoginUserDto;
import com.interfacesv.demo.service.UserDetailsServiceImpl;
import com.interfacesv.demo.service.UserService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody Map<String,String> param, HttpServletResponse response) {
        String studentId = param.get("studentId");
        String password = param.get("password");

        LoginUserDto loginUserDto = new LoginUserDto(studentId, password);

        User user = userService.login(loginUserDto);
        String checkStudentId = user.getStudentId();
        String role = user.getAuth();

        String token = jwtTokenProvider.createToken(checkStudentId, role);
        response.setHeader("JWT", token);

        return ResponseEntity.ok(token);
    }
}
