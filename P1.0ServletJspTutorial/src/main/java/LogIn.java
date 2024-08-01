
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
@WebServlet("/login")
public class LogIn extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        System.out.println("Inserted credentials: "+name+" " +surname);

        HttpSession session = req.getSession();
        session.setAttribute("name", name);
        session.setAttribute("surname", surname);
        resp.sendRedirect("credentials");

        /*
        int num1 = Integer.parseInt(num1Str);
        int num2 = Integer.parseInt(num2Str);

        int k = num1 + num2;
        */

        /*
        //cookies
        Cookie cookie = new Cookie("k", k+"");
        resp.addCookie(cookie);

        resp.sendRedirect("sq");
        */
        /*
        //send redirect
        HttpSession session = req.getSession();
        session.setAttribute("k",k);

        resp.sendRedirect("sq?k="+k);
        */

        /*
        //dispacher
        req.setAttribute("k", k);
        System.out.println("Result from addition: " + k);

        RequestDispatcher rd = req.getRequestDispatcher("sq");
        rd.forward(req, resp);
         */
    }
}

