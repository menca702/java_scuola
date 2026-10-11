import java.time.LocalDate;
import java.util.Scanner;

public class Utente {

    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private Veicolo veicolo;

    public Utente(String nome, String cognome, LocalDate dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
    }

    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public LocalDate getDataNascita() { return dataNascita; }
    public Veicolo getVeicolo() { return veicolo; }

    public Veicolo aggiungiVeicolo(Scanner in) {

        System.out.println("Inserimento veicolo: ");
        System.out.println("Marca del veicolo: ");
        String marca = in.nextLine().trim();
        System.out.println("Modello del veicolo: ");
        String modello = in.nextLine().trim();

        TipoCarburante[] tipi = TipoCarburante.values();
        System.out.println("Seleziona il tipo di carburante: ");
        for (int i = 0; i < tipi.length; i++) {
            System.out.println((i + 1) + ". " + tipi[i]);
        }
        int scelta = 0;
        do {
            scelta = in.nextInt();
        } while (scelta < 1 || scelta > tipi.length);
        in.nextLine();

        veicolo = new Veicolo(marca, modello, tipi[scelta - 1]);

        return veicolo;
    }

    public void eseguiPagamento(double importo) {
        System.out.printf("%s %s ha pagato %.2f € alla cassa automatica.%n", nome, cognome, importo);
    }

    @Override
    public String toString() {
        return nome + " " + cognome;
    }
}

