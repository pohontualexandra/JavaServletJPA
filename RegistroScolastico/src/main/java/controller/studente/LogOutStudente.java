package controller.studente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/logOutStudente")
public class LogOutStudente extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session != null) {
            session.removeAttribute("matricola");
            session.invalidate();
        }

        PrintWriter pw = resp.getWriter();

        pw.println("<h2>You have successfully logged out</h2>");
        pw.println("<br>");
        pw.println("<a href='index.jsp' style='text-decoration: none; color: white; background-color: green; padding: 10px 20px; border: none; border-radius: 5px; font-weight: bold;'>Home</a>");

        resp.sendRedirect("index.jsp");
    }
}
