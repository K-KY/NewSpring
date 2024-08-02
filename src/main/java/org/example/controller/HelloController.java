package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.dto.LoginRequestDto;
import org.example.dto.ResultResponseDto;
import org.example.dto.register.RegisterDto;
import org.example.dto.user.UserDto;
import org.example.model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private UserService userService;

    @PostMapping(value ="/register", produces = "application/json; charset=UTF-8")
    public ResultResponseDto<RegisterDto> register(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    @PostMapping(value = "/login", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResultResponseDto<?>> login(
            @RequestBody LoginRequestDto loginRequestDto, HttpServletRequest httpServletRequest) {
        System.out.println("HelloController.login");
        ResultResponseDto<Object> login = userService.login(loginRequestDto);
        if (login.isSuccess()) {
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("user", login.getDto());
            session.setMaxInactiveInterval(600 * 6);
        }
        System.out.println(httpServletRequest.getSession().getAttribute("user"));
        return ResponseEntity.ok().body(login);
    }

    //그냥 데이터 확인용
    @GetMapping(value = "/fetch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> fetch() {
            System.out.println("HelloController.fetch");
            return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping(value = "ok", produces = MediaType.APPLICATION_JSON_VALUE)
    public String ok() {
        System.out.println("HelloController.ok");
        System.out.println("userService.count() = " + userService.count());
        return userService.test();
    }

    @GetMapping(value = "/session", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultResponseDto<Object>> getSession(HttpServletRequest httpServletRequest) {
        System.out.println("HelloController.getSession");
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("user") != null) {
            Object userSession = session.getAttribute("user");
            System.out.println("session = " + userSession);
            return ResponseEntity.ok().body(new ResultResponseDto<>(userSession, true, "유효"));
        }
        return ResponseEntity.ok().body(new ResultResponseDto<>("", false, "만료"));
    }
}
