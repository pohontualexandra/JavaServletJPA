package BookDb;

import Connection.ConnessioneBookDb;

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

@WebServlet("/deleteUrl")
public class DeleteServlet extends HttpServlet {

    Connection connection;
    public void init() {
        System.out.println("Delete Servlet is being initialized");
        try {
            connection = ConnessioneBookDb.getConnection();
        } catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        PrintWriter pw = resp.getWriter();
        //set content type
        resp.setContentType("text/html");
        String sql = "DELETE FROM book_data WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, id);
            int count = ps.executeUpdate();
            if(count == 1) {
                req.setAttribute("message", "Book deleted from database");
                RequestDispatcher dispatcher = req.getRequestDispatcher("bookList");
                dispatcher.forward(req, resp);
            }else{
                req.setAttribute("message", "Error while deleting book");
                RequestDispatcher dispatcher = req.getRequestDispatcher("bookList");
                dispatcher.forward(req, resp);
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
