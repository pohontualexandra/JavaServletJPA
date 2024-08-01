package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneBookDb{
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String URL = "jdbc:postgresql://localhost:5432/bookStore";
        String USERNAME = "postgres";
        String PASSWORD = "postgres";
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}