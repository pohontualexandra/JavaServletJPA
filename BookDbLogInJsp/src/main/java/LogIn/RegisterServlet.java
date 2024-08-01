package LogIn;

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
import java.sql.SQLException;
import Connection.ConnessioneUser;

@WebServlet ("/registerUser")
public class RegisterServlet extends HttpServlet {
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
        //get PrintWriter
        PrintWriter pw = resp.getWriter();
        //set content type
        resp.setContentType("text/html");
        String username1=req.getParameter("username");
        String email1=req.getParameter("email");
        String password1=req.getParameter("password");
        String country1=req.getParameter("country");

        String sql="INSERT INTO users(username,email,password,country) VALUES(?,?,?,?)";
        if(username1.isEmpty()||email1.isEmpty()||password1.isEmpty()){
            req.setAttribute("message", "All fields must be compiled");
            RequestDispatcher dispacher = req.getRequestDispatcher("registration.jsp");
            dispacher.forward(req, resp);
        }else{
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1,username1);
                ps.setString(2,email1);
                ps.setString(3,password1);
                ps.setString(4,country1);
                int count=ps.executeUpdate();
                if(count==1){
                    pw.println("<h2>Successfully Registrated</h2>");
                    pw.println("<br>");
                    pw.println("<p>You can now manage the Book Store Database</p>");
                    pw.println("<a href='index.jsp' style='text-decoration: none; color: white; background-color: green; padding: 10px 20px; border: none; border-radius: 5px; font-weight: bold;'>Go to Book Management</a>");
                    HttpSession session = req.getSession(true);
                    session.setAttribute("username", username1);

                }else{
                    pw.println("<h2>Error while Registrating</h2>");
                }
            }catch (SQLException se){
                se.printStackTrace();
                pw.println("<h1>"+se.getMessage()+"/h1");
            }
            catch(Exception e){
                e.printStackTrace();
                pw.println("<h1>"+e.getMessage()+"/h1");
            }
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<a href='bookList.jsp' style='text-decoration: none; color: red; font-weight: bold;'>Book List</a>");
        }
    }

}
