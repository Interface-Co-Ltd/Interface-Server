package com.interfacesv.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginUserDto {
    private String studentId;
    private String password;
    private String token;

    public LoginUserDto(String studentId, String password, String token) {
        this.studentId = studentId;
        this.password = password;
        this.token = token;
    }
}