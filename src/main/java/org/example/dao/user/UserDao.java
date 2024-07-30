package org.example.dao.user;

import org.example.dto.register.RegisterDto;
import org.example.dto.user.UserDto;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    UserDto select(String userEmail);

    boolean insert(RegisterDto registerDto);

    boolean exist(String userEmail);
}
