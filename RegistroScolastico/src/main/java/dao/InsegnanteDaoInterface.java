package dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public interface InsegnanteDaoInterface {
    Date parseDate(String dataEsameString) throws ParseException;
    public int registraInsegnante(String codiceInsegnante, String nome, String cognome, String codiceFiscale, String email, String password) throws SQLException;
    public int insertVoto(String materia, Date dataEsame, int voto, String matricolaStudente, String codiceInsegnante) throws SQLException;
}
