package dao;

import model.Voto;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public interface StudenteDaoInterface {
    public int registraStudente(String matricola, String nome, String cognome, String codiceFiscale, String email, String corso, String password) throws SQLException;
    public List<Voto> getVotiStudente(HttpServletRequest req) throws SQLException;


}
