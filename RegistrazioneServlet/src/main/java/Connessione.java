import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connessione {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String jdbcUrl = "jdbc:postgresql://localhost:5432/accounts";
        String username = "postgres";
        String password = "postgres";
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
    public String insertUser(User user) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        String result = "Data entered successfully";
        String sql = "insert into users values(?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUname());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            result = "We couldn't enter your data into our Database";
        }
        return result;
    }
}

