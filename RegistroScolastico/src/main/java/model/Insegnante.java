package model;

public class Insegnante {

    private String codiceInsegnante;
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String email;

    public Insegnante(String codiceInsegnante, String nome, String cognome, String codiceFiscale, String email) {
        this.codiceInsegnante = codiceInsegnante;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
    }

    public String getCodiceInsegnante() {
        return codiceInsegnante;
    }

    public void setCodiceInsegnante(String codiceInsegnante) {
        this.codiceInsegnante = codiceInsegnante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
