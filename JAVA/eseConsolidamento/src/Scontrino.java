import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class Scontrino {

    LocalDateTime dataOra;
    TipoPagamento pagamento;
    List<RigaScontrino> righe = new ArrayList<>();

    public Scontrino(LocalDateTime dataOra, TipoPagamento pagamento) {
        this.dataOra = dataOra; 
        this.pagamento = pagamento;
    }

    public LocalDate getGiorno() { return dataOra.toLocalDate(); }
    public TipoPagamento getPagamento() { return pagamento; }
    public void aggiungiRiga(RigaScontrino r) { righe.add(r); }

    public double getTotale() {
        double tot = 0;
        for (RigaScontrino r : righe) tot += r.getPrezzoParziale();
        return tot;
    }
}