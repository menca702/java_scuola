import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        LocalDate giorno = LocalDate.of(2026, 11, 17);

        Supermercato super1 = new Supermercato();
        Cassa cassa1 = new Cassa(1);
        Cassa cassa2 = new Cassa(2);
        super1.aggiungiCassa(cassa1);
        super1.aggiungiCassa(cassa2);

        Operatore mario = new Operatore("OP1", "Mario", "Rossi");
        Operatore lucia = new Operatore("OP2", "Lucia", "Bianchi");

        //Turni
        Turno turno1 = new Turno(mario, giorno.atTime(8, 0), giorno.atTime(14, 0));
        Turno turno2 = new Turno(lucia, giorno.atTime(8, 0), giorno.atTime(14, 0));
        cassa1.aggiungiTurno(turno1);
        cassa2.aggiungiTurno(turno2);

        //Scontrini
        Scontrino s1 = new Scontrino(giorno.atTime(9, 15), TipoPagamento.CONTANTI);
        s1.aggiungiRiga(new RigaScontrino("Pane", 2, 1.50));
        s1.aggiungiRiga(new RigaScontrino("Latte", 1, 1.20));
        turno1.emettiScontrino(s1);

        Scontrino s2 = new Scontrino(giorno.atTime(10, 30), TipoPagamento.ELETTRONICO);
        s2.aggiungiRiga(new RigaScontrino("Pasta", 3, 0.90));
        turno1.emettiScontrino(s2);

        Scontrino s3 = new Scontrino(giorno.atTime(9, 45), TipoPagamento.ELETTRONICO);
        s3.aggiungiRiga(new RigaScontrino("Caffè", 1, 4.50));
        s3.aggiungiRiga(new RigaScontrino("Biscotti", 2, 2.00));
        turno2.emettiScontrino(s3);

        //2: incasso di una cassa (contanti ed elettronico)
        for (Cassa c : new Cassa[] { cassa1, cassa2 }) {
            System.out.printf("%s -> contanti: %.2f | elettronico: %.2f | totale: %.2f%n",
                    c,
                    c.incasso(giorno, TipoPagamento.CONTANTI),
                    c.incasso(giorno, TipoPagamento.ELETTRONICO),
                    super1.incassoCassa(c, giorno));
        }

        //3: incasso totale della giornata
        System.out.printf("Incasso totale giornata: %.2f%n", super1.incassoGiornata(giorno));

        //4: incasso per operatore
        System.out.printf("Incasso %s: %.2f%n", mario, super1.incassoOperatore(mario, giorno));
        System.out.printf("Incasso %s: %.2f%n", lucia, super1.incassoOperatore(lucia, giorno));

        //1: scambio turno (il turno di Mario passa a Lucia)
        super1.scambiaTurno(turno1, lucia);
        System.out.println("--- dopo lo scambio del turno ---");
        System.out.printf("Incasso %s: %.2f%n", mario, super1.incassoOperatore(mario, giorno));
        System.out.printf("Incasso %s: %.2f%n", lucia, super1.incassoOperatore(lucia, giorno));
    }
}