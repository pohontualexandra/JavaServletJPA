import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    Connection connection;
    public void init(ServletConfig config){
        System.out.println("Server is being initialized");
        try{
            connection = Connessione.getConnection();
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uname = request.getParameter("uname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        User user = new User(uname, password, email, phone);
        Connessione insertU = new Connessione();
        try {
            String result = insertU.insertUser(user);
            response.getWriter().println(result);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
