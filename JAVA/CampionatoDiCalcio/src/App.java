import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            // Campionato con 2 squadre
            GestioneCampionato gestione = new GestioneCampionato(2);

            gestione.inserimentoSquadra();
            gestione.inserimentoSquadra();
            gestione.inserimentoSquadra();

            gestione.inserimentoIncontro();
            gestione.inserimentoIncontro();
            gestione.inserimentoIncontro();

            gestione.mostraPodio();

            System.out.println("\nEsecuzione completata con successo!");

        } catch (IOException e) {
            System.out.println("Errore di I/O durante la lettura da tastiera: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Errore nel formato numerico inserito: " + e.getMessage());
        }
    }
}
