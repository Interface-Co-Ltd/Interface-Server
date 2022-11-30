package com.interfacesv.demo.service;

import com.interfacesv.demo.domain.Cooperation.Cooperation;
import com.interfacesv.demo.domain.Cooperation.CooperationRepository;
import com.interfacesv.demo.dto.CooperationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CooperationService {

    private final CooperationRepository cooperationRepository;

    @Transactional
    public List<CooperationDto> findAll() {
        List<Cooperation> cooperationList = cooperationRepository.findAll();
        List<CooperationDto> cooperationDtoList = new ArrayList<>();

        for(int i=0;i<cooperationList.size();i++) {
            cooperationDtoList.add(new CooperationDto(cooperationList.get(i)));
        }

        return cooperationDtoList;
    }

    @Transactional
    public CooperationDto save(CooperationDto cooperationDto) {
        Cooperation cooperation = Cooperation.builder()
                .name(cooperationDto.getName())
                .address(cooperationDto.getAddress())
                .link(cooperationDto.getLink())
                .build();

        try {
            cooperationRepository.save(cooperation);
        } catch (NullPointerException e) {
            return cooperationDto;
        }

        return new CooperationDto(cooperation);
    }

    @Transactional
    public CooperationDto update(CooperationDto cooperationDto) {
        Cooperation cooperation = cooperationRepository.getById(cooperationDto.getId());
        cooperation.update(cooperationDto);

        return new CooperationDto(cooperation);
    }

    @Transactional
    public void delete(Long id) {
        cooperationRepository.deleteById(id);
    }
}
