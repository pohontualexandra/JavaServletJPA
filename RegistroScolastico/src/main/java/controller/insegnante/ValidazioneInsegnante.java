package controller.insegnante;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/loginInsegnante")
public class ValidazioneInsegnante extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(ValidazioneInsegnante.class);
    Connection conn;
    public void init(){
        System.out.println("Login Insegnante is being initialized");
        try{
            conn= DatabaseUtil.getConnection();
        }catch(SQLException e){
            logger.error("Error while initialising Log In Insegnante: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");
        String codice_insegnante=req.getParameter("codice_insegnante");
        String password=req.getParameter("password");

        if(codice_insegnante.isEmpty()) {
            req.setAttribute("message", "Field 'Codice Insegnante' must be compiled");
            RequestDispatcher dispatcher = req.getRequestDispatcher("loginInsegnante.jsp");
            dispatcher.forward(req, resp);
        }

        String sql = "SELECT from insegnante I WHERE I.codice_insegnante=? AND I.password=?";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, codice_insegnante);
            ps.setString(2, password);


            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                HttpSession session = req.getSession(true);
                session.setAttribute("codice_insegnante", codice_insegnante);
                RequestDispatcher dispatcher = req.getRequestDispatcher("inserisciVoto.jsp");
                dispatcher.forward(req, resp);
            }else{
                req.setAttribute("message", "Dati non validi. Riprova. ");
                RequestDispatcher dispatcher = req.getRequestDispatcher("loginInsegnante.jsp");
                dispatcher.forward(req, resp);
            }

        }catch(SQLException e){
            logger.error("Error while logging In: ");
            throw new ServletException("Database error during login: {}",e);
        }
    }
}


