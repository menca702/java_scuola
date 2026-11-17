class Operatore {

    String codice, nome, cognome;

    public Operatore(String codice, String nome, String cognome) {
        this.codice = codice; 
        this.nome = nome; 
        this.cognome = cognome;
    }

    public String getCodice() { return codice; }
    public String toString() { return nome + " " + cognome; }
}
