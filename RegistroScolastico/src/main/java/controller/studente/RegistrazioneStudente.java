package controller.studente;

import dao.StudenteDAO;
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

@WebServlet ("/registrazioneStudente")
public class RegistrazioneStudente extends HttpServlet {

    private StudenteDAO studenteDAO;
    private static final Logger logger = LogManager.getLogger(RegistrazioneStudente.class);

    public void init() throws ServletException {
        System.out.println("Servlet Registrazione Studente inizializzata");
        super.init();
        try {
            studenteDAO = new StudenteDAO();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Errore durante la inizializzazione della Servlet Registrazione Studente", e.getMessage());
            throw new ServletException("Error initializing connection", e);
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");

        String matricola=req.getParameter("matricola");
        String nome=req.getParameter("nome");
        String cognome=req.getParameter("cognome");
        String codice_fiscale=req.getParameter("codiceFiscale");
        String email=req.getParameter("email");
        String corso=req.getParameter("corso");
        String password=req.getParameter("password");

        try {
            if(studenteDAO.registraStudente(matricola, nome, cognome, codice_fiscale, email, corso, password) == 1){
                HttpSession session = req.getSession(true);
                session.setAttribute("matricola", matricola);
                req.setAttribute("success", "Registrazione avvenuta con successo");
                RequestDispatcher dispatcher = req.getRequestDispatcher("visualizzaVoti.jsp");
                dispatcher.forward(req, resp);
            };
        } catch (SQLException e) {
            req.setAttribute("error", "Errore durante la registrazione");
            RequestDispatcher dispatcher = req.getRequestDispatcher("registrazioneStudente.jsp");
            dispatcher.forward(req, resp);
            logger.error("Errore durante la registrazione: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

