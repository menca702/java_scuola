import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Supermercato {
    
    List<Cassa> casse = new ArrayList<>();

    public void aggiungiCassa(Cassa c) { casse.add(c); }

    //1. Scambio di un turno: il turno passa a un altro operatore
    public void scambiaTurno(Turno turno, Operatore altro) {
        turno.setOperatore(altro);
    }

    //2. Incasso di una cassa nella giornata (contanti + elettronico)
    public double incassoCassa(Cassa c, LocalDate giorno) {
        return c.incasso(giorno, TipoPagamento.CONTANTI)
             + c.incasso(giorno, TipoPagamento.ELETTRONICO);
    }

    //3. Incasso totale della giornata.
    public double incassoGiornata(LocalDate giorno) {
        double tot = 0;
        for (Cassa c : casse) tot += incassoCassa(c, giorno);
        return tot;
    }

    //4. Importo incassato da un operatore in una giornata.
    public double incassoOperatore(Operatore op, LocalDate giorno) {
        double tot = 0;
        for (Cassa c : casse)
            for (Turno t : c.getTurni())
                if (t.getOperatore() == op)
                    for (Scontrino s : t.getScontrini())
                        if (s.getGiorno().equals(giorno)) tot += s.getTotale();
        return tot;
    }

}
