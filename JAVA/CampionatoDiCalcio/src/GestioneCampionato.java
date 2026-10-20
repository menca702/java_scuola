import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class GestioneCampionato {

    Squadra[] squadre;
    Calendario calendario;
    Risultati risultati;
    Classifiche classifiche;
    int countSquadre;

    BufferedReader reader;
    OutputStreamWriter writer;
    
    public GestioneCampionato(int totSquadre) {
        this.squadre = new Squadra[totSquadre];
        this.calendario = new Calendario();
        this.risultati = new Risultati(totSquadre);
        this.classifiche = new Classifiche();
        this.countSquadre = 0;

        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.writer = new OutputStreamWriter(System.out);
    }

    public void inserimentoSquadra() throws IOException {
        writer.write("Inserisci il nome della squadra: ");
        writer.flush();
        String nomeSquadra = reader.readLine();

        writer.write("Quanti giocatori vuoi inserire? ");
        writer.flush();
        int nGiocatori = Integer.parseInt(reader.readLine());

        Squadra squadra = new Squadra(nomeSquadra, new String[0], new int[0]);

        for (int i = 0; i < nGiocatori; i++) {
            writer.write("Nome giocatore " + (i + 1) + ": ");
            writer.flush();
            String nomeGiocatore = reader.readLine();

            writer.write("Gol fatti da " + nomeGiocatore + ": ");
            writer.flush();
            int gol = Integer.parseInt(reader.readLine());

            squadra.add(nomeGiocatore, gol);
        }

        if (countSquadre < squadre.length) {
            squadre[countSquadre] = squadra;
            countSquadre++;
            writer.write("Squadra aggiunta\n");
            writer.flush();
        } else {
            writer.write("Campionato pieno! Impossibile aggiungere altre squadre.\n");
            writer.flush();
        }
    }

    public void inserimentoIncontro() throws IOException {
        writer.write("Inserisci la squadra 1: ");
        writer.flush();
        String s1 = reader.readLine();

        writer.write("Inserisci la squadra 2: ");
        writer.flush();
        String s2 = reader.readLine();

        writer.write("Inserisci anno (es. 2026): ");
        writer.flush();
        int aa = Integer.parseInt(reader.readLine());

        writer.write("Inserisci mese (1-12): ");
        writer.flush();
        int mm = Integer.parseInt(reader.readLine());

        writer.write("Inserisci giorno (1-31): ");
        writer.flush();
        int gg = Integer.parseInt(reader.readLine());

        calendario.add(s1, s2, aa, mm, gg);
        writer.write("Incontro pianificato con successo!\n");
        writer.flush();
    }

    public void mostraPodio(String[] nomiSquadre, int[] puntiSquadre) throws IOException {
        classifiche.getPodio(puntiSquadre, nomiSquadre);
        
        // Stampa tramite OutputStreamWriter
        writer.write(classifiche.toString(nomiSquadre.length > 0 ? nomiSquadre[0] : "", nomiSquadre.length > 1 ? nomiSquadre[1] : "") + "\n");
        writer.flush();
    }
}
