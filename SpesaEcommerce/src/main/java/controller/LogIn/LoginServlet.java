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

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

    private static final Logger logger = LogManager.getLogger(LoginServlet.class);
    private UserDaoImpl userDao = new UserDaoImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.isEmpty()) {
            req.setAttribute("message", "Field 'Username' must be compiled");
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        if (password == null || password.isEmpty()) {
            req.setAttribute("message", "Field 'Password' must be compiled");
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        try {
            User user = userDao.getUserByUsernamePassword(username, password);
            if (user != null) {
                HttpSession session = req.getSession(true);
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("username", user.getUsername());
                resp.sendRedirect("products");
            } else {
                req.setAttribute("message", "Invalid credentials. Please try again.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (SQLException e) {
            logger.error("Error while logging in: ", e);
            throw new ServletException("Database error during login: ", e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }
}


