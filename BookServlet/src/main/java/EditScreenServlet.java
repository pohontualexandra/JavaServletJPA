
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
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.Connessione;

@WebServlet("/edit")
public class EditScreenServlet extends HttpServlet {
    Connection connection;
    public void init(){
        System.out.println("Edit Servlet initialized");
        try{
            connection = Connessione.getConnection();
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sql = "SELECT * FROM book_data WHERE id=?";
        PrintWriter pw = resp.getWriter();

        try{
            PreparedStatement ps = connection.prepareStatement(sql);

            int id = Integer.parseInt(req.getParameter("id"));
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String title1 = rs.getString(2);
            String author1 = rs.getString(3);
            Float price1 = rs.getFloat(4);

            Cookie cookie = new Cookie("id1", String.valueOf(id));
            resp.addCookie(cookie);
            req.setAttribute("title1", title1);
            req.setAttribute("author1", author1);
            req.setAttribute("price1", price1);


            RequestDispatcher dispatcher = req.getRequestDispatcher("edit.jsp");
            dispatcher.forward(req, resp);

            pw.println("<br>");
            pw.println("<a href='index.jsp' style='text-decoration: none; color: red; font-weight: bold;'>Home</a>");
            pw.println("<br>");
            pw.println("<a href='bookList.jsp' style='text-decoration: none; color: red; font-weight: bold;'>Book List</a>");

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
