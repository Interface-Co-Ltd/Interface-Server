package com.interfacesv.demo.service;

import com.interfacesv.demo.domain.board.Board;
import com.interfacesv.demo.domain.board.BoardRepository;
import com.interfacesv.demo.domain.user.User;
import com.interfacesv.demo.domain.user.UserRepository;
import com.interfacesv.demo.dto.LoginUserDto;
import com.interfacesv.demo.dto.UserDto;
import com.interfacesv.demo.dto.UserSimple;
import com.interfacesv.demo.exception.CustomException;
import com.interfacesv.demo.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final PasswordEncoder passwordEncoder;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, BoardRepository boardRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.boardRepository = boardRepository;
    }

    /*
    회원정보 저장
    @param userDto 회원정보가 들어있는 DTO
    @return 저장되는 회원의 PK
     */
    @Transactional
    public Long save(UserDto userDto) {
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

        List<Board> boardList = boardRepository.findAll();

        for(int i=0;i<boardList.size();i++) {
            if(boardList.get(i).getUser().getStudentId().equals(studentId)) {
                boardList.get(i).update(boardList.get(i).getTitle(),
                        boardList.get(i).getContent(),
                        boardList.get(i).getType(),
                        null);
            }
        }

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

    //로그인
    public User login(LoginUserDto loginUserDto) {
        User user = userRepository.findByStudentId(loginUserDto.getStudentId()).orElseThrow(
                () -> new CustomException(ErrorCode.NO_USER)
        );
        if (!encoder.matches(loginUserDto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.NO_PASSWORD);
        }
        return user;
    }
}
