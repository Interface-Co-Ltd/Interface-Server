package com.interfacesv.demo.dto;

import com.interfacesv.demo.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSimple {
    private String studentId;
    private String name;
    private String email;
    private String auth;
    private String phone;
    private String birthday;

    public UserSimple(User user) {
        this.studentId = user.getStudentId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.auth = user.getAuth();
        this.phone = user.getPhone();
        this.birthday = user.getBirthday();
    }
}
