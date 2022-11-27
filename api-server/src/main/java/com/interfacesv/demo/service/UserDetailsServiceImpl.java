package com.interfacesv.demo.service;

import com.interfacesv.demo.domain.user.User;
import com.interfacesv.demo.domain.user.UserRepository;
import com.interfacesv.demo.dto.LoginUserDto;
import com.interfacesv.demo.dto.UserDto;
import com.interfacesv.demo.dto.UserSimple;
import com.interfacesv.demo.exception.CustomException;
import com.interfacesv.demo.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

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
                .orElseThrow(() -> new UsernameNotFoundException(studentId)); //람다 표현 공부필요
    }
}