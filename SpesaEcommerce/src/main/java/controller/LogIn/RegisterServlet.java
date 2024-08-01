package controller.LogIn;

import dao.impl.UserDaoImpl;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

    private static final Logger logger = LogManager.getLogger(RegisterServlet.class);
    private UserDaoImpl userDao = new UserDaoImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String password = req.getParameter("password");
        if (username.isEmpty() || firstName.isEmpty() ||
                 lastName.isEmpty() || email.isEmpty() || address.isEmpty() || password.isEmpty()) {
            req.setAttribute("message", "All fields must be compiled");
            RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
            dispatcher.forward(req, resp);
            return;
        }


        try {
            User user = new User(username, firstName, lastName, password, email,  address);
            int userId = userDao.addUser(user);
            if (userId > 0) {
                User user_creato =  userDao.getUserByUsernamePassword(username, password);
                HttpSession session = req.getSession(true);
                session.setAttribute("userId", user_creato.getUserId());
                session.setAttribute("username", user_creato.getUsername());
                resp.sendRedirect("products");
            } else {
                req.setAttribute("message", "Error while signing in. Please try again.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (SQLException e) {
            logger.error("Error while signing in: ", e);
            throw new ServletException("Database error during register: ", e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("register.jsp");
    }
}



