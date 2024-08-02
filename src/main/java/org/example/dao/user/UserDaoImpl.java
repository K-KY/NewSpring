package org.example.dao.user;


import org.example.dto.register.RegisterDto;
import org.example.dto.user.UserDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SqlSessionTemplate sqlSession;

    public UserDaoImpl() {
    }

    public UserDaoImpl(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    private final String namespace = "UserMapper.";

    @Override
    public int count() {
        return sqlSession.selectOne(namespace+ "countUsers");
    }

    @Override
    public UserDto select(String userName) {
        return sqlSession.selectOne(namespace + "selectUser", userName);
    }

    @Override
    public boolean insert(RegisterDto registerDto) {
        int insert = sqlSession.insert(namespace + "insertUser", registerDto);
        int insertUserInfo = sqlSession.insert(namespace + "insertUserInfo", registerDto);
        return insert == 1 && insertUserInfo == 1;
    }

    @Override
    public boolean exist(String userEmail) {
        return sqlSession.selectOne(namespace + "existInUser", userEmail);
    }

    @Override
    public List<UserDto> selectAllUsers() {
        return sqlSession.selectList(namespace + "selectAllUsers");
    }


}
