import java.io.BufferedReader; //lettura da tastiera
import java.io.IOException; //gestione eccezioni
import java.time.DateTimeException; //gestione eccezioni
import java.time.LocalDate; //gestione date (data)
import java.time.LocalDateTime; //gestione date (data e ora)
import java.time.format.DateTimeFormatter; //gestione date (formattazione)
 
public class SportelloAutomatico {
 
    // costante per formattare la data e l'ora in modo leggibile
    private static final DateTimeFormatter FORMATO_DATA_ORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
 
    private String tipologiaServizio;
    private float quantita;
    private float[] tariffeCarburante = { 1.85f, 1.75f, 0.75f, 1.20f };
    private LocalDateTime dataOraInizio;
    private LocalDateTime dataOraFine;
    private int ultimoIndice = -1; // indice dell'ultima ricevuta generata
 
    private double costo;
    private int numPompa;
    private boolean servizioOperatore; 
    private Operatore operatore;
    private Ricevuta ricevuta = new Ricevuta();
 
    //costruttore vuoto
    SportelloAutomatico() {
    }
 
    public double getCosto() { return costo; } // fase 4
    public boolean isServizioOperatore() { return servizioOperatore; }
    public Operatore getOperatore() { return operatore; } // fase 4
    public int getUltimoIndice() { return ultimoIndice; }
 
    // fase 1
    public Utente accedi(BufferedReader in) throws IOException {
 
        System.out.println("===== SPORTELLO AUTOMATICO: ACCESSO =====");
        System.out.print("Nome: ");
        String nome = in.readLine().trim();
        System.out.print("Cognome: ");
        String cognome = in.readLine().trim();
        LocalDate dataNascita = null;
 
        while (dataNascita == null) {
            System.out.print("Giorno di nascita: ");
            int giorno = Integer.parseInt(in.readLine().trim());
            System.out.print("Mese di nascita: ");
            int mese = Integer.parseInt(in.readLine().trim());
            System.out.print("Anno di nascita: ");
            int anno = Integer.parseInt(in.readLine().trim());
            
            // tenta di creare la data di nascita; se non valida, stampa un messaggio e ripete il ciclo
            try {
                dataNascita = LocalDate.of(anno, mese, giorno);
            } catch (DateTimeException e) {
                System.out.println("Data non valida, riprova.");
            }
        }
 
        // registra l'orario di inizio della transazione
        dataOraInizio = LocalDateTime.now();
        return new Utente(nome, cognome, dataNascita);
    }
 
    // fase 2
    public void scegliServizio(Utente utente, BufferedReader in) throws IOException {
        System.out.println("Tipo di servizio:");
        System.out.println("  1) Self service");
        System.out.println("  2) Svolto da un operatore");
        int scelta = 0;
        do {
            System.out.print("Scelta (1 o 2): ");
            scelta = Integer.parseInt(in.readLine().trim());
        } while (scelta != 1 && scelta != 2);
 
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
 
    // richiamato da scegliServizio()
    public Operatore ottieniOperatore() {
        operatore = new Operatore();
        operatore.scegliOperatore();
        System.out.println("Operatore assegnato: " + operatore);
        return operatore;
    }
 
    public void elaboraPrezzoQuantita(Veicolo veicolo, BufferedReader in) throws IOException {
        TipoCarburante carburante = veicolo.getCarburante();
        int posizione = carburante.ordinal();
        numPompa = posizione + 1;
 
        do {
            System.out.print("Quanti " + carburante.unitaDiMisura() + " di " + carburante + " immettere? ");
            quantita = Integer.parseInt(in.readLine().trim()); // converte la stringa in intero + acquisce la quantità di carburante da immettere
        } while (quantita < 1 || quantita > 200);
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
                + String.format("Tariffa:      %.3f EUR/%s%n", tariffeCarburante[veicolo.getCarburante().ordinal()], unita)
                + String.format("TOTALE:       %.2f EUR%n", costo)
                + "============================";
        ultimoIndice = ricevuta.aggiuntaRicevuta(testo);
        return ricevuta;
    }
 
}