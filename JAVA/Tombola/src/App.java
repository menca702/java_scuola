public class App {
    public static void main(String[] args) throws Exception {
        
        Tombola tombola = new Tombola();
        
        //creazione cartelle * giocatori
        String[] nomiGiocatori = {"Anna", "Marco", "Giulia"};
        for (int i = 0; i < nomiGiocatori.length; i++) {
            Giocatore g = new Giocatore(nomiGiocatori[i], "giocatore" + i + ".png");
            g.add(new Cartella());
            g.add(new Cartella());
            tombola.add(g);
        }
 
        int[] combinazioni = {2, 3, 4, 5};
        String[] nomiCombinazioni = {"Ambo", "Terno", "Quaterna", "Cinquina"};
        boolean[] assegnata = new boolean[combinazioni.length]; //tiene traccia dei premi
 
        //inizio del gioco (fase principale)
        int estrazioni = 0; //numeri usciti fino ad ora
        while (tombola.vincitore(15) == null) {
            tombola.gioca();
            estrazioni++;
            
            //scorre i premi e assegna quelli non ancora vinti
            for (int i = 0; i < combinazioni.length; i++) {
                if (!assegnata[i]) {
                    Giocatore giocatore = tombola.vincitore(combinazioni[i]);
                    if (giocatore != null) {
                        assegnata[i] = true;
                        System.out.println(nomiCombinazioni[i] + " a " + giocatore.getName()
                                + " all'estrazione n. " + estrazioni);
                    }
                }
            }
        }
 
        //controllo tombola
        Giocatore vincitore = tombola.vincitore(15);
        System.out.println("TOMBOLA! Vince " + vincitore.getName()
                + " all'estrazione n. " + estrazioni);
    }

}
