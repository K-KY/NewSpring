package org.example.controller;

import org.example.dto.ResultResponseDto;
import org.example.dto.register.RegisterDto;
import org.example.model.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final UserService userService = new UserService();

    @GetMapping(value = "/", produces = "application/json; charset=UTF-8")
    public String hello() {
        return "오꼐";
    }

    @PostMapping(value ="/register", produces = "application/json; charset=UTF-8")
    public ResultResponseDto<RegisterDto> register(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }
}
