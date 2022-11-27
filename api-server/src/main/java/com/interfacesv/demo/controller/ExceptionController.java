package com.interfacesv.demo.controller;

import com.interfacesv.demo.exception.CustomException;
import com.interfacesv.demo.exception.ErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {
    @GetMapping("/exception/entrypoint")
    public void entryPoint() {
        throw new CustomException(ErrorCode.NO_LOGIN);
    }

    @GetMapping("/exception/access")
    public void denied() {
        throw new CustomException(ErrorCode.NO_ADMIN);
    }

    @GetMapping("/exception/notfound")
    public void notFound() {
        throw new CustomException(ErrorCode.NO_FOUND);
    }
}