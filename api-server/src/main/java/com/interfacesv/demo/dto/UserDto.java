package com.interfacesv.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String studentId;
//    @NotBlank(message = "이름은 필수 입력 값입니다.")
//    @NotNull
    private String name;
//    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
    private String email;
//    @NotNull
    private String auth;
//    @Nullable
    private String phone;
//    @Nullable
    private String birthday;
}
