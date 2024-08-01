package LogIn;

import Connection.ConnessioneUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
    Connection conn;
    public void init(){
        System.out.println("Register servlet is being initialized");
        try{
            conn= ConnessioneUser.getConnection();
        }catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");
        String username2=req.getParameter("username");
        String password2=req.getParameter("password");

        if(username2.isEmpty() || password2.isEmpty()) {
            req.setAttribute("message", "All fields must be compiled");
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        }

        String sql = "SELECT from users WHERE users.username=? AND users.password=?";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, username2);
            ps.setString(2, password2);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                pw.println("<h2>Login successful</h2>");
                pw.println("<br>");
                pw.println("<p>You can now manage the Book Store Database</p>");
                pw.println("<a href='index.jsp' style='text-decoration: none; color: white; background-color: green; padding: 10px 20px; border: none; border-radius: 5px; font-weight: bold;'>Go to Book Management</a>");
                HttpSession session = req.getSession(true);
                session.setAttribute("username", username2);
            }else{
                req.setAttribute("message", "Invalid username or password. Please try again. ");
                RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                dispatcher.forward(req, resp);
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new ServletException("Database error during login",e);
        }
        pw.println("<br>");
        pw.println("<br>");
        pw.println("<a href='bookList.jsp' style='text-decoration: none; color: red; font-weight: bold;'>Book List</a>");
    }
}
