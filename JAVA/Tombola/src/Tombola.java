import java.util.Arrays;

public class Tombola {
  
    private Tabellone tabellone;
    private Sacchetto sacchetto;
    private Giocatore[] giocatori;

    public Tombola() {
        tabellone = new Tabellone();
        sacchetto = new Sacchetto();
        giocatori = new Giocatore[0];
    }

    //viene creata una copia dell'array in memoria  aumentando la sua dimensione di 1 e aggiungendo il nuovo giocatore alla fine dell'array
    public void add(Giocatore g) {
        giocatori = Arrays.copyOf(giocatori, giocatori.length + 1);
        giocatori[giocatori.length - 1] = g;
    }
    

    //estrazione del dischetto dal sacchetto
    // rimozione del numero estratto dal tabellone e dalle cartelle dei giocatori
    public void gioca() {
        Dischetto d = sacchetto.estrai();
        if (d == null) {
            return; // sacchetto vuoto
        }
        tabellone.togli(d);
        for (int i = 0; i < giocatori.length; i++) {
            giocatori[i].cancella(d.getNumero());
        }
    }

    //viene passato la quantità di numeri uscita
    //controllo se: tombola - combinazioni - null
    public Giocatore vincitore(int quanti) {
        for (int i = 0; i < giocatori.length; i++) {
            boolean ha;
            if (quanti == 15) {
                ha = giocatori[i].haTombola();
            } else {
                ha = giocatori[i].haCombinazione(quanti);
            }
            if (ha) {
                return giocatori[i];
            }
        }
        return null;
    }
}