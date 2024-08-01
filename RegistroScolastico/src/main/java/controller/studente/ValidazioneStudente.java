package controller.studente;

import util.DatabaseUtil;

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

@WebServlet("/loginStudente")
public class ValidazioneStudente extends HttpServlet {
    Connection conn;
    public void init(){
        System.out.println("Login Studente is being initialized");
        try{
            conn= DatabaseUtil.getConnection();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");
        String matricola=req.getParameter("matricola");
        String password=req.getParameter("password");

        if(matricola.isEmpty()) {
            req.setAttribute("message", "Field 'matricola' must be compiled");
            RequestDispatcher dispatcher = req.getRequestDispatcher("loginStudente.jsp");
            dispatcher.forward(req, resp);
        }

        String sql = "SELECT * from studente WHERE studente.matricola=? AND studente.password=?";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, matricola);
            ps.setString(2, password);


            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                pw.println("<h2>Login studente avvenuto con successo</h2>");
                HttpSession session = req.getSession(true);
                session.setAttribute("matricola", matricola);
                pw.println("<br>");
                pw.println("<p>Puoi visualizzare la lista dei tuoi voti</p>");
                pw.println("<a href='visualizzaVoti' style='text-decoration: none; color: white; background-color: green; padding: 10px 20px; border: none; border-radius: 5px; font-weight: bold;'>Visualizza Voti</a>");
            }else{
                req.setAttribute("message", "Dati non validi. Riprova. ");
                RequestDispatcher dispatcher = req.getRequestDispatcher("loginStudente.jsp");
                dispatcher.forward(req, resp);
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new ServletException("Database error during login",e);
        }
        pw.println("<a href='index.jsp' style='text-decoration: none; color: red; font-weight: bold;'>Home</a>");
    }
}

