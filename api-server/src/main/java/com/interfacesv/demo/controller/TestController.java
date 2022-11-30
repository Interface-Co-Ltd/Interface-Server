package com.interfacesv.demo.controller;

import com.interfacesv.demo.component.TestDataInit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestController {

    private final TestDataInit testDataInit;

    @GetMapping("")
    public ResponseEntity<?> initialData() {
        testDataInit.deleteAll();

        testDataInit.boardInitial();
        testDataInit.cooperationInitial();
        testDataInit.scheduleInitial();

        return ResponseEntity.ok().build();
    }
}
