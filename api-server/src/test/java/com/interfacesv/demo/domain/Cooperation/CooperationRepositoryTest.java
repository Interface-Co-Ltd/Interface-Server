package com.interfacesv.demo.domain.Cooperation;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperationRepositoryTest {
    @Autowired
    CooperationRepository cooperationRepository;

    @After
    public void cleanUp(){
        cooperationRepository.deleteAll();
    }

    @Test
    public void 제휴업체조회(){
        //given
        String name = "mart1";
        String address = "서울특별시 광진구";
        String link = "www.interface.com";

        Cooperation save = cooperationRepository.save(Cooperation.builder()
                        .name(name)
                        .address(address)
                        .link(link)
                        .build());

        //when
        List<Cooperation> cooperationList = cooperationRepository.findAll();

        //then
        Cooperation cooperation = cooperationList.get(0);

        assertThat(cooperation.getName()).isEqualTo("mart1");
        assertThat(cooperation.getAddress()).isEqualTo(address);
        assertThat(cooperation.getLink()).isEqualTo(link);
    }
}