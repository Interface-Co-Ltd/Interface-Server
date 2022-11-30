package com.interfacesv.demo.controller;

import com.interfacesv.demo.dto.CooperationDto;
import com.interfacesv.demo.service.CooperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cooperation")
public class CooperationController {

    private final CooperationService cooperationService;

    @GetMapping("")
    public ResponseEntity<List<CooperationDto>> findAllCooperation() {
        List<CooperationDto> cooperationDtoList = cooperationService.findAll();

        return ResponseEntity.ok(cooperationDtoList);
    }

    @PostMapping("/create")
    public ResponseEntity<CooperationDto> create(@RequestBody Map<String,String> param) {
        String name = param.get("name");
        String address = param.get("address");
        String link = param.get("link");

        CooperationDto cooperationDto = cooperationService.save(CooperationDto.builder()
                .id(0L)
                .name(name)
                .address(address)
                .link(link)
                .build());

        return ResponseEntity.ok(cooperationDto);
    }

    @PutMapping("/update")
    public ResponseEntity<CooperationDto> update(@RequestBody Map<String,String> param) {
        Long id = Long.parseLong(param.get("id"));
        String name = param.get("name");
        String address = param.get("address");
        String link = param.get("link");

        CooperationDto cooperationDto = cooperationService.update(CooperationDto.builder()
                .id(id)
                .name(name)
                .address(address)
                .link(link)
                .build());

        return ResponseEntity.ok(cooperationDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id")Long id) {
        cooperationService.delete(id);

        return ResponseEntity.ok().build();
    }
}
