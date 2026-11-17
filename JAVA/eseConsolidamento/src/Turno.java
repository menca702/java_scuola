import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class Turno {

    Operatore operatore;
    LocalDateTime inizio, fine;
    List<Scontrino> scontrini = new ArrayList<>();

    public Turno(Operatore operatore, LocalDateTime inizio, LocalDateTime fine) {
        this.operatore = operatore; 
        this.inizio = inizio; 
        this.fine = fine;
    }

    public Operatore getOperatore() { return operatore; }
    public void setOperatore(Operatore o) { this.operatore = o; }
    public LocalDateTime getInizio() { return inizio; }
    public LocalDateTime getFine() { return fine; }
    public List<Scontrino> getScontrini() { return scontrini; }
    public void emettiScontrino(Scontrino s) { scontrini.add(s); }

    //Utile per verificare che uno scontrino sia emesso dentro il turno
    public boolean contiene(LocalDateTime momento) {
        return !momento.isBefore(inizio) && !momento.isAfter(fine);
    }
}