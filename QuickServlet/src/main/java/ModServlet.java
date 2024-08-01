import DB.Connessione;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModServlet extends HttpServlet {

    Connection connection;
    public void init() throws ServletException {
        System.out.println("Modify Servlet is being initialized");
        try {
            connection = Connessione.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String modusername = req.getParameter("modusername");
        String modpassword = req.getParameter("modpassword");
        int id = Integer.parseInt(req.getParameter("id"));
        String sql = "UPDATE user SET username = ?, password = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, modusername);
            statement.setString(2, modpassword);
            statement.setInt(3, id);
            statement.executeUpdate();
            PrintWriter writer = resp.getWriter();
            writer.println("<html>Update avvenuto con successo" +
                    "<br>" +
                    "<a href=/QuickServlet>Torna indietro</a>"+
                    "</html>");
            writer.flush();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
