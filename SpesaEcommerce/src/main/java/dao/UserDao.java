package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    int addUser(User user) throws SQLException;
    User getUserById(Long userId) throws SQLException;

    User getUserByUsernamePassword(String username, String password) throws SQLException;
}

