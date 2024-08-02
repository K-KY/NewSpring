package org.example.dao.user;

import org.example.dto.register.RegisterDto;
import org.example.dto.user.UserDto;
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
