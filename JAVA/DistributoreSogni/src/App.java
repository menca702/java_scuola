import java.io.BufferedReader; //lettura da tastiera
import java.io.IOException; //gestione eccezioni
import java.io.InputStreamReader; //lettura da tastiera
 
public class App {
 
    // FRONT-END: chiede i dati alla tastiera e crea il Veicolo
    //throws IOException: per gestire eventuali errori di input/output
    static Veicolo leggiVeicolo(BufferedReader in) throws IOException {
        System.out.println("Inserimento veicolo: ");
        System.out.print("Marca del veicolo: ");
        String marca = in.readLine().trim();
        System.out.print("Modello del veicolo: ");
        String modello = in.readLine().trim();
 
        TipoCarburante[] tipi = TipoCarburante.values();
        System.out.println("Seleziona il tipo di carburante: ");
        for (int i = 0; i < tipi.length; i++) {
            System.out.println((i + 1) + ". " + tipi[i]);
        }
        int scelta = 0;
        do {
            System.out.print("Scelta: ");
            scelta = Integer.parseInt(in.readLine().trim());
        } while (scelta < 1 || scelta > tipi.length);
 
        return new Veicolo(marca, modello, tipi[scelta - 1]);
    }
 
    // FRONT-END: fa scegliere all'utente quale veicolo rifornire
    static Veicolo scegliVeicolo(Utente utente, BufferedReader in) throws IOException {
        if (utente.getNumVeicoli() == 1) {
            return utente.getVeicolo(0);
        }
        System.out.println("Quale veicolo vuoi rifornire?");
        for (int i = 0; i < utente.getNumVeicoli(); i++) {
            System.out.println((i + 1) + ". " + utente.getVeicolo(i));
        }
        int scelta = 0;
        do {
            System.out.print("Scelta: ");
            scelta = Integer.parseInt(in.readLine().trim());
        } while (scelta < 1 || scelta > utente.getNumVeicoli());
        return utente.getVeicolo(scelta - 1);
    }
 
    public static void main(String[] args) throws Exception {
 
        //instanzia un oggetto BufferedReader per leggere l'input da tastiera
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        SportelloAutomatico sportello = new SportelloAutomatico();
        String altro; // variabile per controllare se ci sono altri clienti
 
        do {
 
            // fase 1
            Utente utente = sportello.accedi(in);
 
            String piu; // variabile per controllare se l'utente vuole aggiungere un altro veicolo
            do {
                utente.aggiungiVeicolo(leggiVeicolo(in));
                piu = "n";
                if (utente.getNumVeicoli() < utente.getMaxVeicoli()) {
                    System.out.print("Aggiungere un altro veicolo? (s/n): ");
                    piu = in.readLine().trim(); // legge la risposta dell'utente
                } else {
                    System.out.println("Limite di veicoli raggiunto.");
                }
            } while (piu.equalsIgnoreCase("s")); // finché l'utente vuole aggiungere veicoli
 
            Veicolo veicolo = scegliVeicolo(utente, in);
 
            // fase 2
            sportello.scegliServizio(utente, in);
            sportello.elaboraPrezzoQuantita(veicolo, in);
 
            // fase 3
            veicolo.faiBenzina();
            sportello.registraFine();
 
            // fase 4
            if (sportello.isServizioOperatore()) {
                sportello.getOperatore().eseguiPagamento(sportello.getCosto());
            } else {
                utente.eseguiPagamento(sportello.getCosto());
            }
 
            // fase 5
            Ricevuta ricevuta = sportello.inviaDatiRicevuta(utente, veicolo);
            System.out.println();
            ricevuta.mostraRicevuta(sportello.getUltimoIndice());
 
            System.out.print("\nNuovo cliente? (s/n): ");
            altro = in.readLine().trim();
            System.out.println();
 
        } while (altro.equalsIgnoreCase("s"));
 
        in.close();
    }
}