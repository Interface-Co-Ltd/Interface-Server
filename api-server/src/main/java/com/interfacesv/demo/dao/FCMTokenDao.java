package com.interfacesv.demo.dao;

import com.interfacesv.demo.dto.LoginUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FCMTokenDao {

    private final StringRedisTemplate tokenRedisTemplate;

    public void saveToken(LoginUserDto loginUserDto) {
        tokenRedisTemplate.opsForValue()
                .set(loginUserDto.getStudentId(), loginUserDto.getToken());
        tokenRedisTemplate.opsForValue().set("test", "isdata");
    }

    public String getToken(String studentId) {
        return tokenRedisTemplate.opsForValue().get(studentId);
    }

    public void deleteToken(String studentId) {
        tokenRedisTemplate.delete(studentId);
    }

    public boolean hasKey(String studentId) {
        return tokenRedisTemplate.hasKey(studentId);
    }
}