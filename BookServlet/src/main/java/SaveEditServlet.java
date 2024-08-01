import Connection.Connessione;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet("/saveEdit")
public class SaveEditServlet extends HttpServlet {

    //jdbc connection
    Connection connection;
    public void init() {
        System.out.println("Save Edit Servlet is being initialized");
        try {
            connection = Connessione.getConnection();
        } catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        String idString = "";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id1")) {
                    idString = cookie.getValue();
                }
            }
        }
        System.out.println(idString);

        PrintWriter pw = resp.getWriter();
        //set content type
        resp.setContentType("text/html");
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        float price = Float.parseFloat(req.getParameter("price"));
        String sql = "UPDATE book_data SET title=?, author=?, price=? WHERE ID = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setFloat(3, price);
            ps.setInt(4, Integer.parseInt(idString));
            int count = ps.executeUpdate();
            if(count == 1) {
                pw.println("<h2>Book updated into the store database</h2>");
            }else{
                pw.println("<h2>Error while updating record</h2>");
            }

        }catch(SQLException se){
            se.printStackTrace();
            pw.println("<h1>"+se.getMessage()+"/h1");
        }
        catch(Exception e){
            e.printStackTrace();
            pw.println("<h1>"+e.getMessage()+"/h1");
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
