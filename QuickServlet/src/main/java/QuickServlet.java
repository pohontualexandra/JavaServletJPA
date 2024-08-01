import DB.Connessione;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuickServlet extends HttpServlet {

    Connection connection;
    public void init(ServletConfig config) {
        System.out.println("Servlet is being initialized");
        try {
            connection = Connessione.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String sql = "SELECT * FROM user";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                PrintWriter writer = resp.getWriter();
                writer.println("<html>");
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("username");
                    String surname = resultSet.getString("password");
                    writer.println(" " +id + ": " + name + " - " + surname);
                    writer.println("<br>");
                }
                writer.println("<a href=\"http://localhost:8080/QuickServlet/\">Torna indietro</a></html>");
                writer.flush();
            } catch (Exception e) {
                System.out.println("failed to get results");
            }
        }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String sql = "Insert into user (username, password) values (?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.execute();

            PrintWriter writer = resp.getWriter();
            writer.println("<html>inserimento avvenuto con successo" +
                    "<br>" +
                    "<a href=/QuickServlet>Torna indietro</a>"+
                    "</html>");
            writer.flush();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void destroy(){
        System.out.println("servlet destroyed");
    }
}
