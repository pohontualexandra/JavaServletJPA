package model;

import java.util.Date;

public class Voto {

    private String materia;
    private Date dataEsame;
    private int voto;
    private String matricolaStudente;
    private String codiceInsegnante;

    public Voto(String materia, Date dataEsame, int voto, String matricolaStudente, String codiceInsegnante) {
        this.materia = materia;
        this.dataEsame = dataEsame;
        this.voto = voto;
        this.matricolaStudente = matricolaStudente;
        this.codiceInsegnante = codiceInsegnante;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Date getDataEsame() {
        return dataEsame;
    }

    public void setDataEsame(Date dataEsame) {
        this.dataEsame = dataEsame;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public String getMatricolaStudente() {
        return matricolaStudente;
    }

    public void setMatricolaStudente(String matricolaStudente) {
        this.matricolaStudente = matricolaStudente;
    }

    public String getCodiceInsegnante() {
        return codiceInsegnante;
    }

    public void setCodiceInsegnante(String codiceInsegnante) {
        this.codiceInsegnante = codiceInsegnante;
    }
}