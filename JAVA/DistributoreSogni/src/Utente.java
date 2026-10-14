import java.time.LocalDate;
 
public class Utente {
 
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private Veicolo[] veicoli = new Veicolo[5]; // massimo 5 veicoli
    private int numVeicoli = 0;
 
    public Utente(String nome, String cognome, LocalDate dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
    }
 
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public LocalDate getDataNascita() { return dataNascita; }
    public int getNumVeicoli() { return numVeicoli; }
    public int getMaxVeicoli() { return veicoli.length; }
    public Veicolo getVeicolo(int indice) { return veicoli[indice]; }
 
    // salva il veicolo (già creato altrove) nell'array; false se l'array è pieno
    public boolean aggiungiVeicolo(Veicolo veicolo) {
        if (numVeicoli == veicoli.length) {
            return false;
        }
        veicoli[numVeicoli] = veicolo;
        numVeicoli++;
        return true;
    }
 
    public void eseguiPagamento(double importo) {
        System.out.printf("%s %s ha pagato %.2f EUR alla cassa automatica.%n", nome, cognome, importo);
    }
 
    @Override
    public String toString() {
        return nome + " " + cognome;
    }
}