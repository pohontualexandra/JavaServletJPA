package controller.insegnante;

import dao.InsegnanteDAO;
import dao.InsegnanteDaoInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet ("/registrazioneInsegnante")
public class RegistrazioneInsegnante extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(RegistrazioneInsegnante.class);
    private InsegnanteDaoInterface insegnanteDAO;

    public void init() throws ServletException {
        System.out.println("Servlet Registrazione Insegnante inizializzata");
        insegnanteDAO = new InsegnanteDAO();
        super.init();
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //get PrintWriter
        //set content type
        resp.setContentType("text/html");

        String codice_insegnante=req.getParameter("codiceInsegnante");
        String nome=req.getParameter("nome");
        String cognome=req.getParameter("cognome");
        String codice_fiscale=req.getParameter("codiceFiscale");
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        try {
            if(insegnanteDAO.registraInsegnante(codice_insegnante, nome, cognome, codice_fiscale, email, password)==1) {
                HttpSession session = req.getSession(true);
                session.setAttribute("codice_insegnante", codice_insegnante);
                req.setAttribute("success", "Registrazione avvenuta con successo");
                RequestDispatcher dispatcher = req.getRequestDispatcher("inserisciVoto.jsp");
                dispatcher.forward(req, resp);
            }

        } catch (SQLException e) {
            req.setAttribute("error", "Si è verificato un errore nella registrazione");
            RequestDispatcher dispatcher = req.getRequestDispatcher("registrazioneInsegnante.jsp");
            dispatcher.forward(req, resp);
            logger.error("Registrazione non avvenuta: {}", e.getMessage());
        }
    }
}


