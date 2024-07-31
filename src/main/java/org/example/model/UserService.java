package org.example.model;

import org.example.dao.user.UserDao;
import org.example.dto.ResultResponseDto;
import org.example.dto.register.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    private UserDao userDao;


    public ResultResponseDto<RegisterDto> register(RegisterDto registerDto) {
        if (validateDto(registerDto)) {
            return new ResultResponseDto<>(registerDto, false, "모든 값을 입력 해 주세요.");
        }
        if (userDao.exist(registerDto.getUserEmail())) {
            return new ResultResponseDto<>(registerDto, false, "중복된 이메일이 존재합니다.");
        }
        userDao.insert(registerDto);

        return new ResultResponseDto<>(registerDto, true, "회원가입 성공");
    }

    private boolean validateDto(RegisterDto registerDto) {
        return registerDto.getUserEmail() == null || registerDto.getUserEmail().equals("")
                || registerDto.getUserGender() == null  || registerDto.getUserGender().equals("")
                || registerDto.getUserName() == null || registerDto.getUserName().equals("")
                || registerDto.getUserRegion() == null || registerDto.getUserRegion().equals("")
                || registerDto.getUserSecret() == null || registerDto.getUserSecret().equals("");
    }
}
