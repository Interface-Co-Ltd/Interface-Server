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

    @Test
    public void 회원가입() {
        String studentId = "20003201";
        String name = "빵은정";
        String password = "q1w2e3r4!";
        String email = "20003201@sju.ac.kr";
        String auth = "admin";
        String phone = "010-4528-6427";
        String birthday = "99-04-26";

        UserDto userDto = new UserDto();
        userDto.setStudentId(studentId);
        userDto.setName(name);
        userDto.setPassword(password);
        userDto.setEmail(email);
        userDto.setAuth(auth);
        userDto.setPhone(phone);
        userDto.setBirthday(birthday);

        userDetailsServiceImpl.save(userDto);

        List<User> userList = userRepository.findAll();
        User user = userList.get(0);

        assertThat(user.getUsername()).isEqualTo(studentId);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(encoder.matches(password, user.getPassword())).isEqualTo(true);
        assertThat(encoder.matches(password + "notMatched", user.getPassword())).isEqualTo(false);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getAuth()).isEqualTo(auth);
        assertThat(user.getPhone()).isEqualTo(phone);
    }

}
