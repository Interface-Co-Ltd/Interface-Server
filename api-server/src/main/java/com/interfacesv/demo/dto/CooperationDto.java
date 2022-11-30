package com.interfacesv.demo.dto;

import com.interfacesv.demo.domain.Cooperation.Cooperation;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Data
public class CooperationDto {
    private final Long id;
    private final String name;
    private final String address;
    private final String link;

    @Builder
    public CooperationDto(Long id, String name, String address, String link) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.link = link;
    }

    public CooperationDto(Cooperation cooperation) {
        this.id = cooperation.getId();
        this.name = cooperation.getName();;
        this.address = cooperation.getAddress();
        this.link = cooperation.getLink();
    }
}
