package com.interfacesv.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String studentId;
    private String name;
    private String password;
    private String email;
    private String auth;
    private String phone;
    private String birthday;

    public UserDto() {}

    public UserDto(String studentId, String name, String password, String email, String auth, String phone, String birthday) {
        this.setStudentId(studentId);
        this.setName(name);
        this.setPassword(password);
        this.setEmail(email);
        this.setAuth(auth);
        this.setPhone(phone);
        this.setBirthday(birthday);
    }
}
