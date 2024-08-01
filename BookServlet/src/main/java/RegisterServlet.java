import Connection.Connessione;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    //jdbc connection
    Connection connection;
    public void init() {
        System.out.println("Register Servlet is being initialized");
        try {
            connection = Connessione.getConnection();
        } catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get PrintWriter
        PrintWriter pw = resp.getWriter();
        //set content type
        resp.setContentType("text/html");
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        float price = Float.parseFloat(req.getParameter("price"));
        String sql = "INSERT into book_data(title, author, price) VALUES(?,?,?)";
        if(title.isEmpty() || author.isEmpty()){
            req.setAttribute("message", "All fields must be compiled");
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }else{
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1, title);
                ps.setString(2, author);
                ps.setFloat(3, price);
                int count = ps.executeUpdate();
                if(count == 1) {
                    pw.println("<h2>Book inserted into the store database</h2>");
                }else{
                    pw.println("<h2>Error while inserting record</h2>");
                }
            }catch(SQLException se){
                se.printStackTrace();
                pw.println("<h1>"+se.getMessage()+"/h1");
            }
            catch(Exception e){
                e.printStackTrace();
                pw.println("<h1>"+e.getMessage()+"/h1");
            }
        }
        pw.println("<br>");
        pw.println("<a href='index.jsp' style='text-decoration: none; color: red; font-weight: bold;'>Home</a>");
        pw.println("<br>");
        pw.println("<a href='bookList' style='text-decoration: none; color: red; font-weight: bold;'>Book List Preview</a>");
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
