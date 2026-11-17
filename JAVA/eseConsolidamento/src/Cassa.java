import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Cassa {
    
    int numero;
    List<Turno> turni = new ArrayList<>();

    public Cassa(int numero) { 
        this.numero = numero; 
    }

    public int getNumero() { return numero; }
    public void aggiungiTurno(Turno t) { turni.add(t); }
    public List<Turno> getTurni() { return turni; }

    public double incasso(LocalDate giorno, TipoPagamento tipo) {
        double tot = 0;
        for (Turno t : turni)
            for (Scontrino s : t.getScontrini())
                if (s.getGiorno().equals(giorno) && s.getPagamento() == tipo)
                    tot += s.getTotale();
        return tot;
    }

    @Override
    public String toString() { return "Cassa " + numero; }
}