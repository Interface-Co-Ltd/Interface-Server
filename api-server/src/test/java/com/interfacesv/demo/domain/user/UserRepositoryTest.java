package com.interfacesv.demo.domain.user;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Before
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void 회원가입() {
        String studentId = "20003201";
        String name = "김성민";
        String password = "q1w2e3r4!";
        String email = "20003201@sju.ac.kr";
        String auth = "admin";
        String phone = "010-4528-6427";
        String birthday = "99-04-26";

        userRepository.save(User.builder()
                .studentId(studentId)
                .name(name)
                .password(password)
                .email(email)
                .auth(auth)
                .phone(phone)
                .birthday(birthday)
                .build());

        List<User> userList = userRepository.findAll();
        User user = userList.get(0);

        assertThat(user.getUsername()).isEqualTo(studentId);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getAuth()).isEqualTo(auth);
        assertThat(user.getPhone()).isEqualTo(phone);
        assertThat(user.getPassword()).isEqualTo(password);
    }

}
