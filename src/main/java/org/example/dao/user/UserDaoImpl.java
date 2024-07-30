package org.example.dao.user;


import org.example.dto.register.RegisterDto;
import org.example.dto.user.UserDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    private String namespace = "UserMapper.";

    @Override
    public UserDto select(String userName) {
        return sqlSession.selectOne(namespace + "selectUser", userName);
    }

    @Override
    public boolean insert(RegisterDto registerDto) {
        int insert = sqlSession.insert(namespace + "insertUser", registerDto);
        return insert == 1;
    }

    @Override
    public boolean exist(String userEmail) {
        sqlSession.selectOne(namespace + "existInUser", userEmail);
        return true;
    }


}
