package org.example.model;

import org.example.dto.LoginRequestDto;
import org.example.dto.ResultResponseDto;
import org.example.dto.register.RegisterDto;
import org.example.dto.user.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @Commit
    @Transactional
    @DisplayName("중복되는 Email은 저장하지 않는다.")
    void register_fail() {
        ResultResponseDto<RegisterDto> responseDto = userService.register(new RegisterDto("kkk", "m", "1234.com", "kor", "0000"));
        assertThat(responseDto.getMessage()).isEqualTo("중복된 이메일이 존재합니다.");
        assertThat(responseDto.isSuccess()).isFalse();
        System.out.println("responseDto = " + responseDto);
    }

    @Test
    @Transactional
    @DisplayName("중복되지 않는 이메일은 저장한다.")
    void register_Success() {
        String randomValue = UUID.randomUUID().toString().substring(0, 10);
        ResultResponseDto<RegisterDto> responseDto = userService.register(new RegisterDto("kkk", "m", randomValue, "kor", "0000"));
        assertThat(responseDto.getMessage()).isEqualTo("회원가입 성공");
        assertThat(responseDto.isSuccess()).isTrue();
        System.out.println("responseDto = " + responseDto);
    }

    @Test
    @Transactional
    @DisplayName("비어있는 값이 있으면 저장하지 않는다.")
    void register_empty_fail() {
        String randomValue = UUID.randomUUID().toString().substring(0, 10);
        ResultResponseDto<RegisterDto> responseDto = userService.register(new RegisterDto("", "", randomValue, "", ""));
        assertThat(responseDto.getMessage()).isEqualTo("모든 값을 입력 해 주세요.");
        assertThat(responseDto.isSuccess()).isFalse();
        System.out.println("responseDto = " + responseDto);
    }

    @Test
    @DisplayName("이메일과 비밀번호가 같으면 로그인 성공")
    void login_success() {
        ResultResponseDto<Object> login = userService.login(new LoginRequestDto("1234.com", "0000"));
        assertThat(login.getMessage()).isEqualTo("로그인 성공");
        assertThat(login.isSuccess()).isTrue();
        System.out.println("login = " + login);
    }

    @Test
    @DisplayName("이메일과 비밀번호가 다르거나 없으면 로그인 실패")
    void login_fail() {
        ResultResponseDto<Object> login = userService.login(new LoginRequestDto("1234.m", "0000"));
        assertThat(login.getMessage()).isEqualTo("로그인 실패");
        assertThat(login.isSuccess()).isFalse();
        System.out.println("login = " + login);
    }

    @Test
    void getAllUsers() {
        List<UserDto> login = userService.getAllUsers();
        System.out.println("login = " + login);
    }

    @Test
    void countUser() {
        System.out.println("userService.count() = " + userService.count());
    }

}