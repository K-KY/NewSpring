package org.example.model;

import org.example.dao.user.UserDao;
import org.example.dto.ResultResponseDto;
import org.example.dto.register.RegisterDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class RegisterTest {

    @Autowired
    private Register register;

    @Test
    @DisplayName("중복되는 Email은 저장하지 않는다.")
    void register_fail() {
        ResultResponseDto responseDto = register.register(new RegisterDto("kkk", "m", "1234.com", "kor", "0000"));
        assertThat(responseDto.getMessage()).isEqualTo("중복된 이메일이 존재합니다.");
        assertThat(responseDto.isSuccess()).isFalse();
        System.out.println("responseDto = " + responseDto);
    }

    @Test
    @Transactional
    @DisplayName("중복되지 않는 이메일은 저장한다.")
    void register_Success() {
        String randomValue = UUID.randomUUID().toString().substring(0, 10);
        ResultResponseDto responseDto = register.register(new RegisterDto("kkk", "m", randomValue, "kor", "0000"));
        assertThat(responseDto.getMessage()).isEqualTo("회원가입 성공.");
        assertThat(responseDto.isSuccess()).isTrue();
        System.out.println("responseDto = " + responseDto);
    }
}