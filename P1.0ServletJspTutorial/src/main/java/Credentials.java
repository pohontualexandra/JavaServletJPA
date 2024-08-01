import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/credentials")
public class Credentials extends LogIn {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        HttpSession session = req.getSession();
        String name = (String)session.getAttribute("name");
        String surname = (String)session.getAttribute("surname");
        resp.setContentType("text/plain");
        resp.getWriter().println("Your credentials: "+name+" "+surname);

        PrintWriter out = resp.getWriter();
        out.print("Hi, ");
        ServletConfig cts = getServletConfig();
        String str = cts.getInitParameter("name");
        out.println(str);
        /*
        ServletContext cts = getServletContext();
        String str = cts.getInitParameter("name");
        out.println(str);
         */

        //cookies
        /*
        int k = 0;
        Cookie cookies[] = req.getCookies();
        for(Cookie c : cookies){
            if(c.getName().equals("k")){
                k = Integer.parseInt(c.getValue());
            }
        }
        resp.setContentType("text/plain");
        resp.getWriter().println("Result from addition: " + k);

        k = k*k;

        System.out.println("Squared result: " + k);

        resp.getWriter().println("Squared Result: " + k);
         */
        /*
        PrintWriter out = resp.getWriter();
        int k = (int) req.getAttribute("k");
         */

        /*
        //sendredirect
        HttpSession session = req.getSession();
        int k = (int)session.getAttribute("k");
       // int k = Integer.parseInt(req.getParameter("k"));
        resp.setContentType("text/plain");
        resp.getWriter().println("Result from addition: " + k);

        k = k*k;

        System.out.println("Squared result: " + k);

        resp.getWriter().println("Squared Result: " + k);
        */

        // session.removeAttribute("k");
    }
}
