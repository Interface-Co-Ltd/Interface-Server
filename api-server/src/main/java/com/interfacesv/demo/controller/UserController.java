package com.interfacesv.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(originPatterns = "http://localhost:8080")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {


}
