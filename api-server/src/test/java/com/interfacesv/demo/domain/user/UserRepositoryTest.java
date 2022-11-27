package com.interfacesv.demo.domain.user;

import com.interfacesv.demo.dto.UserDto;
import com.interfacesv.demo.service.UserDetailsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Before
    public void cleanUp() {
        userRepository.deleteAll();
    }

}
