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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

@WebServlet("/gestioneVoti")
public class GestioneVotiServlet extends HttpServlet {

    private static InsegnanteDAO insegnanteDAO = new InsegnanteDAO();
    private static final Logger logger = LogManager.getLogger(GestioneVotiServlet.class);


//    public void init() throws ServletException {
//        System.out.println("Gestione Voti Servlet is being initialized");
//        super.init();
//        try {
//            insegnanteDAO = new InsegnanteDAO();
//        } catch (SQLException | ClassNotFoundException e) {
//            logger.error("Errore while initializing Gestione Voti Servlet: {}", e.getMessage());
//        }
//        super.init();
//    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/inserisciVoto.jsp");
        dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String materia = req.getParameter("materia");

        Date dataEsame = null;
        String dataEsameString = req.getParameter("dataEsame");
        if (dataEsameString != null && !dataEsameString.isEmpty()) {
            try {
                dataEsame = insegnanteDAO.parseDate(dataEsameString); // Use DAO method for parsing
            } catch (ParseException e) {
                logger.error("Parse Date error: {}", e.getMessage());
                return;
            }
        } else {
            req.setAttribute("errorMessage", "Data esame obbligatoria");
        }

        int voto = Integer.parseInt(req.getParameter("voto"));
        String matricolaStudente = req.getParameter("matricola");
        String codiceInsegnante = req.getParameter("codiceInsegnante");

        PrintWriter pw = resp.getWriter();

        int count = 0;
        try {
            count = insegnanteDAO.insertVoto(materia, dataEsame, voto, matricolaStudente, codiceInsegnante);
        } catch (SQLException e) {
            logger.error("Error while inserting vote: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        if (count == 1) {
            pw.println("<h2>Vote inserted into the store database</h2>");
        } else {
            pw.println("<h2>Error while inserting record</h2>");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("inserisciVoto.jsp");
        dispatcher.forward(req, resp);
    }
}


