package org.example.model.user;

import org.example.model.user.dao.UserDao;
import org.example.model.user.dto.LoginRequestDto;
import org.example.dto.ResultResponseDto;
import org.example.model.user.dto.RegisterDto;
import org.example.model.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserService() {
    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    private static final Map<String, UserDto> temp = new HashMap<>();

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

    public ResultResponseDto<Object> login(LoginRequestDto loginRequestDto) {
        UserDto userDto = getUserDto(loginRequestDto);
        if (userDto != null && userDto.getUserSecret().equals(loginRequestDto.getUserSecret())) {
            temp.clear();
            return new ResultResponseDto<>(userDto, true, "로그인 성공");
        }
        return new ResultResponseDto<>(loginRequestDto,false, "로그인 실패");
    }

    private UserDto getUserDto(LoginRequestDto loginRequestDto) {
        UserDto userDto;
        if (temp.containsKey(loginRequestDto.getUserEmail())) {
            userDto = temp.get(loginRequestDto.getUserEmail());
            return userDto;
        }

        userDto = userDao.select(loginRequestDto.getUserEmail());

        if (userDto != null) {
            temp.put(userDto.getUserEmail(), userDto);
        }
        return userDto;
    }

    public List<UserDto> getAllUsers() {
        return userDao.selectAllUsers();
    }

    public String test() {
        if (userDao == null) {
            System.out.println("null = " + null);
        }
        return "Test";
    }

    public int count() {
        return userDao.count();
    }
}
