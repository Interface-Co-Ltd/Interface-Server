package com.interfacesv.demo.service;

import com.interfacesv.demo.domain.user.User;
import com.interfacesv.demo.domain.user.UserRepository;
import com.interfacesv.demo.dto.UserDto;
import com.interfacesv.demo.dto.UserSimple;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    /*
    Spring Security 필수 메소드 구현
    @param email 이메일
    @return UserDetails
    @throws UsernameNotfoundException 유저가 없을 때 예외 발생
     */

    @Transactional
    @Override //기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 UserInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
    public User loadUserByUsername(String studentId) throws UsernameNotFoundException { //시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
        return userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new UsernameNotFoundException((studentId))); //람다 표현 공부필요
    }

    /*
    회원정보 저장
    @param userDto 회원정보가 들어있는 DTO
    @return 저장되는 회원의 PK
     */
    @Transactional
    public Long save(UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(userDto.getPassword())); //입력받은 비밀번호를 BCrypt로 암호화한 후에 회원 저장

        return userRepository.save(User.builder()
                .studentId(userDto.getStudentId())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .auth(userDto.getAuth())
                .phone(userDto.getPhone())
                .birthday(userDto.getBirthday())
                .build()).getId();
    }


    @Transactional
    public void update(UserDto userDto) {
        User user = userRepository.findByStudentId(userDto.getStudentId()).orElseThrow(()->{
            return new IllegalArgumentException("회원 조회 실패");
        });
        user.update(userDto.getStudentId(),
                userDto.getName(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getAuth(),
                userDto.getPhone(),
                userDto.getBirthday());

        return;
    }

    @Transactional
    public void delete(String studentId) {
        User user = userRepository.findByStudentId(studentId).orElseThrow(()->{
            return new IllegalArgumentException("회원 조회 실패");
        });

        userRepository.delete(user);

        return;
    }


    // 보안 문제 때문에 비밀번호는 반환하지 않음.
    @Transactional
    public List<UserSimple> findAll() {
        List<UserSimple> userSimpleList = new ArrayList<>();
        List<User> userList = userRepository.findAll();

        for(int i=0;i<userList.size();i++) {
            userSimpleList.add(new UserSimple(userList.get(i)));
        }

        return userSimpleList;
    }

    @Transactional
    public UserSimple findByStudentId(String studentId) {
        return new UserSimple(userRepository.findByStudentId(studentId).get());
    }

}