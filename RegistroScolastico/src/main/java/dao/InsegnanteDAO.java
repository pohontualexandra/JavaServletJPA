package dao;

import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class InsegnanteDAO implements InsegnanteDaoInterface {

    private Connection connection;

    public InsegnanteDAO() {
        try {
            connection = DatabaseUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Date parseDate(String dataEsameString) throws ParseException {
        SimpleDateFormat serverDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return serverDateFormat.parse(dataEsameString);
    }
    public int registraInsegnante(String codiceInsegnante, String nome, String cognome, String codiceFiscale, String email, String password) throws SQLException{
        String sql = "INSERT INTO insegnante (codice_insegnante, nome, cognome, codice_fiscale, email, password) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, codiceInsegnante);
            ps.setString(2, nome);
            ps.setString(3, cognome);
            ps.setString(4, codiceFiscale);
            ps.setString(5, email);
            ps.setString(6, password);
            return ps.executeUpdate();
        }
    }

    public int insertVoto(String materia, Date dataEsame, int voto, String matricolaStudente, String codiceInsegnante) throws SQLException {
        String sql = "INSERT INTO voti (materia, data_esame, voto, matricola_studente, codice_insegnante) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, materia);
            ps.setDate(2, new java.sql.Date(dataEsame.getTime()));
            ps.setInt(3, voto);
            ps.setString(4, matricolaStudente);
            ps.setString(5, codiceInsegnante);
            int count = ps.executeUpdate();
            return count;
        }
    }
}


