package dao.impl;

import dao.UserDao;
import model.User;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private Connection connection;
    private static final String ADD_USER = "INSERT INTO Users (username, first_name, last_name, password, email, address) VALUES (?, ?, ?, ?, ?, ?)",
            GET_USER_BY_ID = "SELECT * FROM Users WHERE user_id = ?",
            GET_USER_BY_USERNAME_PASSWORD = "SELECT * FROM Users WHERE username = ? AND password = ?";

    public UserDaoImpl() {
        try {
            this.connection = DatabaseUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int addUser(User user) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(ADD_USER)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getAddress());
            return statement.executeUpdate();
        }
    }

    @Override
    public User getUserById(Long userId) throws SQLException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getLong("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
            }
        }
        return user;
    }

    @Override
    public User getUserByUsernamePassword(String username, String password) throws SQLException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_USERNAME_PASSWORD)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getLong("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
            }
        }
        return user;
    }
}
