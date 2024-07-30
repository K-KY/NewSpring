package org.example.model;

import org.example.dao.user.UserDao;
import org.example.dto.ResultResponseDto;
import org.example.dto.register.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;

public class Register {

    @Autowired
    private UserDao userDao;


    public ResultResponseDto register(RegisterDto registerDto) {
        if (userDao.exist(registerDto.getUserEmail())) {
            return new ResultResponseDto(registerDto, false, "중복된 이메일이 존재합니다.");
        }
        userDao.insert(registerDto);
        return new ResultResponseDto(registerDto, true, "회원가입 성공");
    }
}
