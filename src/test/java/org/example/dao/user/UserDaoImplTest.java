package org.example.dao.user;

import org.example.model.user.dto.RegisterDto;
import org.example.model.user.dao.UserDao;
import org.example.model.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DataSource ds;


    @Test
    public void select() {
        if (userDao == null) {
            System.out.println("null = " + null);
        }
        UserDto name = userDao.select("9999");
        System.out.println("name = " + name);
    }

    @Test
    @Transactional
    void userRegisterTest() {
        boolean insert = userDao.insert(new RegisterDto("name99999", "male", "99999", "kor", "1234"));
        assertThat(insert).isTrue();
    }

    @Test
    public void jdbcConnectionTest() throws Exception {
        try (Connection conn = ds.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM user limit 1");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                System.out.println("이름 = " + resultSet.getString("userName"));
                System.out.println("국적 = " + resultSet.getString(2));
                System.out.println("성별 = " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}