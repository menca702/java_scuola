public class App {
    public static void main(String[] args) throws Exception {

        Giocatore[] g = new Giocatore[4];

        g[0] = new Giocatore("Marco", "img/Marco.jpg");
        g[0].add(new Cartella());
        g[0].add(new Cartella());
        g[0].add(new Cartella());

        g[1] = new Giocatore("Elisa", "img/Elisa.jpg");
        g[1].add(new Cartella());
        g[1].add(new Cartella());

        g[2] = new Giocatore("Marianna", "img/Marianna.jpg");
        g[2].add(new Cartella());

        g[3] = new Giocatore("Federico", "img/Federico.jpg");
        g[3].add(new Cartella());

        Tombola t = new Tombola();
        for (int i = 0; i < g.length; i++) {
            t.add(g[i]);
        }

        // stampa dati del giocatori e di ogni cartella posseduta
        System.out.println("Situazione iniziale\n");
        System.out.println("Al gioco della tombola partecipano: " + g.length + " giocatori");
        for (int i = 0; i < g.length; i++) {
            System.out.println("Giocatore " + (i + 1) + ": " + g[i].getName() + " possiede " + g[i].getNumeroCartelle()
                    + " cartelle");
            g[i].stampaCartelle();
        }

        boolean sacchettoDisponibile = true;

        while (t.vincitore(15) == null && sacchettoDisponibile) {
            sacchettoDisponibile = t.gioca();

            Giocatore vincitore = t.vincitore(15);
            if (vincitore != null) {
                System.out.println("Gioco terminato! Arrivederci");
            } else if (!sacchettoDisponibile) {
                System.out.println("Sacchetto vuoto: nessuno ha fatto tombola.");
            }
        }
    }

}
