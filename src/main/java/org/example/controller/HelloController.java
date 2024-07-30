package org.example.controller;

import org.example.dao.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private UserDao userDao;

    @GetMapping(value = "/", produces = "application/json; charset=UTF-8")
    public String hello() {
        return userDao.select("name").toString();
    }
}
