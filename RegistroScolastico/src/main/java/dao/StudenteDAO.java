package dao;

import model.Voto;
import util.DatabaseUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudenteDAO implements StudenteDaoInterface{

    private Connection conn;

    public StudenteDAO() throws SQLException, ClassNotFoundException {
        conn = DatabaseUtil.getConnection();
    }
    public int registraStudente(String matricola, String nome, String cognome, String codiceFiscale, String email, String corso, String password) throws SQLException{
        String sql = "INSERT INTO studente (matricola, nome, cognome, codice_fiscale, email, corso, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, matricola);
            ps.setString(2, nome);
            ps.setString(3, cognome);
            ps.setString(4, codiceFiscale);
            ps.setString(5, email);
            ps.setString(6, corso);
            ps.setString(7, password);
            int count = ps.executeUpdate();
            return count;
        }
    }

    public List<Voto> getVotiStudente(HttpServletRequest req) throws SQLException {
        List<Voto> voti = new ArrayList<>();
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("matricola") == null){
            throw new RuntimeException("Studente non autenticato");
        }
        String matricolaStudente = (String)session.getAttribute("matricola");
        System.out.println(matricolaStudente);
        String sql = "SELECT * FROM voti WHERE matricola_studente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, matricolaStudente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String materia = rs.getString("materia");
                java.sql.Date dataEsame = rs.getDate("data_esame");
                int voto = rs.getInt("voto");
                String matricola = rs.getString("matricola_studente");
                String codiceInsegnante = rs.getString("codice_insegnante");

                voti.add(new Voto(materia, dataEsame, voto, matricola, codiceInsegnante));
                System.out.println(voti);
            }
            rs.close();
        }
        return voti;
    }
}
