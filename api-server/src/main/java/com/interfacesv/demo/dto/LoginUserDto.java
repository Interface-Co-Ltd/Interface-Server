package com.interfacesv.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginUserDto {
    private String studentId;
    private String password;

    public LoginUserDto(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }
}