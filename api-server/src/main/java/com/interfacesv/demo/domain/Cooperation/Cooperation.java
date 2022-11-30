package com.interfacesv.demo.domain.Cooperation;

import com.interfacesv.demo.domain.BaseTimeEntity.BaseTimeEntity;
import com.interfacesv.demo.dto.CooperationDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Cooperation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="cooperation_name")
    private String name;

    @Column
    private String address;  //erdcloud 제거

    @Column
    private String link;

    @Builder
    public Cooperation(String name, String address, String link) {
        this.name = name;
        this.address = address;
        this.link = link;
    }

    public void update(CooperationDto cooperationDto) {
        this.name = cooperationDto.getName();
        this.address = cooperationDto.getAddress();
        this.link = cooperationDto.getLink();
    }
}
