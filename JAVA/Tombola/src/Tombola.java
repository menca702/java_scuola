import java.util.Arrays;

public class Tombola {
  
    private Tabellone tabellone;
    private Sacchetto sacchetto;
    private Giocatore[] giocatori;
    private boolean[] combinazioniAnnunciate;
    private boolean tombolaAnnunciata;

    public Tombola() {
        tabellone = new Tabellone();
        sacchetto = new Sacchetto();
        giocatori = new Giocatore[0];
        combinazioniAnnunciate = new boolean[6];
    }

    //viene creata una copia dell'array in memoria  aumentando la sua dimensione di 1 e aggiungendo il nuovo giocatore alla fine dell'array
    public void add(Giocatore g) {
        giocatori = Arrays.copyOf(giocatori, giocatori.length + 1);
        giocatori[giocatori.length - 1] = g;
    }
    

    //estrazione del dischetto dal sacchetto
    // rimozione del numero estratto dal tabellone e dalle cartelle dei giocatori
    public boolean gioca() {
        Dischetto d = sacchetto.estrai();
        if (d == null) {
            return false; // sacchetto vuoto
        }
        tabellone.togli(d);

        for (int i = 0; i < giocatori.length; i++) {
            giocatori[i].cancella(d.getNumero());
        }

        String[] annunci = {
            "", "", "L'ambo è stato vinto da ", "La terna è stata vinta da ",
            "La quaterna è stata vinta da ", "La cinquina è stata vinta da "
        };

        //Per ogni premio annuncia il primo giocatore che lo raggiunge
        for (int quanti = 2; quanti <= 5; quanti++) {
            if (combinazioniAnnunciate[quanti]) {
                continue;
            }
            for (int i = 0; i < giocatori.length; i++) {
                Cartella cartellaVincente = giocatori[i].trovaCartellaCombinazione(quanti);
                if (cartellaVincente != null) {
                    System.out.println(annunci[quanti] + giocatori[i].getName()); //stampa il vincitore della combinazione
                    giocatori[i].stampaCartella(cartellaVincente); //stampa la cartella vincente
                    combinazioniAnnunciate[quanti] = true; //modifica array: combinazione "i" raggiunta 
                    break;
                }
            }
        }

        if (!tombolaAnnunciata) {
            Giocatore vincitoreTombola = vincitore(15);
            if (vincitoreTombola != null) {
                System.out.println("Il giocatore " + vincitoreTombola.getName() + " ha fatto tombola!");
                vincitoreTombola.stampaCartelle();
                tombolaAnnunciata = true;
            }
        }
        return true;
    }

    //viene passato la quantità di numeri uscita
    //controllo se: tombola - combinazioni - null
    //return: nome del giocatore che ha vinto o null se non c'è nessun vincitore
    public Giocatore vincitore(int quanti) {
        for (int i = 0; i < giocatori.length; i++) {
            boolean ha;
            if (quanti == 15) {
                ha = giocatori[i].haTombola();//controllo se il giocatore ha fatto tombola
            } else {
                ha = giocatori[i].haCombinazione(quanti);//controllo se il giocatore ha fatto combinazione
            }
            if (ha) {
                return giocatori[i];
            }
        }
        return null;
    }
}