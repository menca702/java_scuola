public class Ricevuta {
    
    private String[] ricevute = new String[100];
    private int numRicevute = 0;

    public int aggiuntaRicevuta(String testo) {
        if (numRicevute == ricevute.length) {
            System.out.println("Archivio ricevute pieno!");
            return -1;
        }
        ricevute[numRicevute] = testo;
        numRicevute++;
        return numRicevute - 1;
    }

    public void mostraRicevuta(int indice) {
        if (indice < 0 || indice >= numRicevute) {
            System.out.println("Ricevuta non trovata!");
            return;
        }
        System.out.println(ricevute[indice]);
    }

    public int getNumRicevute() {
        return numRicevute;
    }

}
