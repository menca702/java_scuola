import java.util.Random;

public class Operatore {
    
    private String[] nomi = { "Luca", "Giulia", "Marco" };
    private String[] cognomi = { "Bianchi", "Verdi", "Neri" };
    private String[] codici = { "D001", "D002", "D003" };
    
    private String nome;
    private String cognome;
    private String codice;

    public Operatore() {
    }

    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getCodice() { return codice; }

    public Operatore scegliOperatore() {
        int i = new Random().nextInt(codici.length);
        nome = nomi[i];
        cognome = cognomi[i];
        codice = codici[i];
        return this;
    }

    public void eseguiPagamento(double importo) {
        System.out.printf("L'operatore %s %s (%s) ha incassato %.2f €.%n", nome, cognome, codice, importo);
    }

    @Override 
    public String toString() {
        return nome + " " + cognome + " (" + codice + ")"; 
    }

}
