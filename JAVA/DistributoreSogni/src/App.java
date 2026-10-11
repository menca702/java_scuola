import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner in = new Scanner(System.in);
        SportelloAutomatico sportello = new SportelloAutomatico();
        String altro;

        do {

            //fase 1
            Utente utente = sportello.accedi(in);
            Veicolo veicolo = utente.aggiungiVeicolo(in);

            //fase 2
            sportello.scegliServizio(utente, in);
            sportello.elaboraPrezzoQuantita(veicolo, in);

            //fase 3
            veicolo.faiBenzina();
            sportello.registraFine();

            //fase 4
            if (sportello.isServizioOperatore()) {
                sportello.getOperatore().eseguiPagamento(sportello.getCosto());
            } else {
                utente.eseguiPagamento(sportello.getCosto());
            }

            //fase 5
            Ricevuta ricevuta = sportello.inviaDatiRicevuta(utente, veicolo);
            System.out.println();
            ricevuta.mostraRicevuta(sportello.getUltimoIndice());

            System.out.print("\nNuovo cliente? (s/n): ");
            altro = in.nextLine().trim();
            System.out.println();

        } while (altro.equalsIgnoreCase("s"));

    }
}
