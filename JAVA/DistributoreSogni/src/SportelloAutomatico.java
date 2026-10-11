import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SportelloAutomatico {

    private static final DateTimeFormatter FORMATO_DATA_ORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private String tipologiaServizio;
    private float quantita;
    private float[] tariffeCarburante = { 1.85f, 1.75f, 0.75f, 1.20f };
    private LocalDateTime dataOraInizio;
    private LocalDateTime dataOraFine;
    private int ultimoIndice = -1;

    private double costo;
    private int numPompa;
    private boolean servizioOperatore;
    private Operatore operatore;
    private Ricevuta ricevuta = new Ricevuta();

    SportelloAutomatico() {
    }

    public double getCosto() {
        return costo;
    } // fase 4

    public boolean isServizioOperatore() {
        return servizioOperatore;
    }

    public Operatore getOperatore() {
        return operatore;
    } // fase 4

    public int getUltimoIndice() {
        return ultimoIndice;
    }

    // fase 1
    public Utente accedi(Scanner in) {

        System.out.println("===== SPORTELLO AUTOMATICO: ACCESSO =====");
        System.out.print("Nome: ");
        String nome = in.nextLine().trim();
        System.out.print("Cognome: ");
        String cognome = in.nextLine().trim();
        LocalDate dataNascita = null;

        while (dataNascita == null) {
            System.out.print("Giorno di nascita: ");
            int giorno = in.nextInt();
            System.out.print("Mese di nascita: ");
            int mese = in.nextInt();
            System.out.print("Anno di nascita: ");
            int anno = in.nextInt();
            in.nextLine();

            try {
                dataNascita = LocalDate.of(anno, mese, giorno);
            } catch (DateTimeException e) {
                System.out.println("Data non valida, riprova.");
            }
        }

        dataOraInizio = LocalDateTime.now();
        return new Utente(nome, cognome, dataNascita);
    }

    // fase 2
    public void scegliServizio(Utente utente, Scanner in) {
        System.out.println("Tipo di servizio:");
        System.out.println("  1) Self service");
        System.out.println("  2) Svolto da un operatore");
        int scelta = 0;
        do {
            System.out.print("Scelta (1 o 2): ");
            scelta = in.nextInt();
        } while (scelta != 1 && scelta != 2);
        in.nextLine();

        servizioOperatore = (scelta == 2);
        if (servizioOperatore) {
            ottieniOperatore();
            tipologiaServizio = "Operatore " + operatore.getNome() + " " + operatore.getCognome()
                    + " - servizio svolto da operatore";
        } else {
            operatore = null;
            tipologiaServizio = utente.getNome() + " " + utente.getCognome() + " - self service";
        }
        System.out.println(tipologiaServizio);
    }

    // richiamato da scegliServizio(
    public Operatore ottieniOperatore() {
        operatore = new Operatore();
        operatore.scegliOperatore();
        System.out.println("Operatore assegnato: " + operatore);
        return operatore;
    }

    public void elaboraPrezzoQuantita(Veicolo veicolo, Scanner in) {
        TipoCarburante carburante = veicolo.getCarburante();
        int posizione = carburante.ordinal();
        numPompa = posizione + 1;

        quantita = 0;
        System.out.printf("Quanti " + carburante.unitaDiMisura() + " di " + carburante + " immettere? ");
        do {
            quantita = in.nextInt();
        } while (quantita < 1 || quantita > 200);
        in.nextLine();
        costo = tariffeCarburante[posizione] * quantita;

        System.out.printf("%s in attesa nella pompa %d, costo: %.2f EUR%n", carburante, numPompa, costo);
    }

    // fase 3
    public void registraFine() {
        dataOraFine = LocalDateTime.now();
    }

    // fase 5
    public Ricevuta inviaDatiRicevuta(Utente utente, Veicolo veicolo) {
        String unita = veicolo.getCarburante().unitaDiMisura();
        String testo = "========= RICEVUTA =========\n"
                + "Cliente:      " + utente + "\n"
                + "Veicolo:      " + veicolo + "\n"
                + "Servizio:     " + tipologiaServizio + "\n"
                + "Arrivo:       " + dataOraInizio.format(FORMATO_DATA_ORA) + "\n"
                + "Partenza:     " + dataOraFine.format(FORMATO_DATA_ORA) + "\n"
                + "Pompa n.:     " + numPompa + "\n"
                + String.format("Quantita:     %.0f %s%n", quantita, unita)
                + String.format("Tariffa:      %.3f EUR/%s%n", tariffeCarburante[veicolo.getCarburante().ordinal()],
                        unita)
                + String.format("TOTALE:       %.2f EUR%n", costo)
                + "============================";
        ultimoIndice = ricevuta.aggiuntaRicevuta(testo);
        return ricevuta;
    }

}