package org.example.model.user.dao;

import org.example.model.user.dto.RegisterDto;
import org.example.model.user.dto.UserDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    int count();
    UserDto select(String userEmail);
    boolean insert(RegisterDto registerDto);
    boolean exist(String userEmail);
    List<UserDto> selectAllUsers();
}
