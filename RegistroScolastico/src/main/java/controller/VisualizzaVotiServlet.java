package controller;

import dao.StudenteDaoInterface;
import model.Voto;
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
import java.sql.SQLException;
import java.util.List;

@WebServlet("/visualizzaVoti")
public class VisualizzaVotiServlet extends HttpServlet {

    private StudenteDaoInterface studenteDAO;
    private static final Logger logger = LogManager.getLogger(VisualizzaVotiServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            studenteDAO = new StudenteDAO();
            System.out.println("Servlet Visualizza Voti inizializzata");
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error while initialising Visualizza Voti Servlet: {}", e.getMessage());
            throw new ServletException("Errore durante l'inizializzazione del DAO", e);
        }

        List<Voto> voti;
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("matricola") != null) {
            try {
                voti = studenteDAO.getVotiStudente(req);
                System.out.println(voti);
                req.setAttribute("voti", voti);
            } catch (SQLException e) {
                req.setAttribute("errorMessage", "Errore durante il recupero dei voti: " + e.getMessage());
            }
        } else {
            resp.sendRedirect("loginStudente.jsp");
            return;
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/visualizzaVoti.jsp");
        dispatcher.forward(req, resp);
    }
}
