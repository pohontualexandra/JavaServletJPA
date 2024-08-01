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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.Connessione;


@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
    Connection connection;
    public void init() {
        System.out.println("Book List Servlet is being initialized");
        try {
            connection = Connessione.getConnection();
        } catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");
        String sql ="SELECT * FROM book_data";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Book> bookList = new ArrayList<>();
            while (rs.next()) {
                int id1 = rs.getInt(1);
                String title1 = rs.getString(2);
                String author1 = rs.getString(3);
                float price1 = rs.getFloat(4);

                // Create a new Book object
                Book book = new Book(id1, title1, author1, price1);

                // Add the book object to the list
                bookList.add(book);
            }
            req.setAttribute("bookList", bookList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("bookList.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        pw.println("<br>");
        pw.println("<a href='index.jsp' style='text-decoration: none; color: red; font-weight: bold;'>Home</a>");
        pw.println("<br>");
        pw.println("<a href='bookList' style='text-decoration: none; color: red; font-weight: bold;'>Book List Preview</a>");
    }
}
