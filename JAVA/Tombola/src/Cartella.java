public class Cartella {
  
    private static final int RIGHE = 3;
    private static final int COLONNE = 5;
 
    private int[][] numeri;
    private boolean[][] estratti; // di default tutti false

    public Cartella() {
        numeri = new int[RIGHE][COLONNE];
        estratti = new boolean[RIGHE][COLONNE];
        
        //se i n è già uscito mette la posizione dell'array booleano a true
        boolean[] giaUsato = new boolean[91]; //ignora lo 0 e va da 1 a 90
        for (int r = 0; r < RIGHE; r++) {
            for (int c = 0; c < COLONNE; c++) {
                int n;
                do {
                    n = (int) (Math.random() * 90) + 1;
                } while (giaUsato[n]);
                giaUsato[n] = true;
                numeri[r][c] = n;
            }
        }
    }

    //mette l'array estratti a "true" in una posizione se il numero estratto è presente nella cartella (array numeri)
    public void cancella(int numeroEstratto) {
        for (int r = 0; r < RIGHE; r++) {
            for (int c = 0; c < COLONNE; c++) {
                if (numeri[r][c] == numeroEstratto) {
                    estratti[r][c] = true;
                }
            }
        }
    }

    //incrementa il contatore di quanti numeri sono stati estratti in una riga
    // se il contatore è >= a quanti allora ritorna true
    public boolean haCombinazione(int quanti) {
        for (int r = 0; r < RIGHE; r++) {
            int contati = 0;
            for (int c = 0; c < COLONNE; c++) {
                if (estratti[r][c]) {
                    contati++;
                }
            }
            if (contati >= quanti) {
                return true;
            }
        }
        return false;
    }

    //se il valore della posizione in "estratti" è false diventà torna false se no il contrario
    //il ciclo cerca il primo errore senza guardare tutto quanto
    public boolean haTombola() {
        for (int r = 0; r < RIGHE; r++) {
            for (int c = 0; c < COLONNE; c++) {
                if (!estratti[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    //GETTER+ALTRI METODI

    public boolean isEstratto(int r, int c) {
        return estratti[r][c];
    }
 
    //se il valore in numeri è uguale a n da true
    public boolean isPresente(int n) {
        for (int r = 0; r < RIGHE; r++) {
            for (int c = 0; c < COLONNE; c++) {
                if (numeri[r][c] == n) {
                    return true;
                }
            }
        }
        return false;
    }
 
    public int getNumero(int r, int c) {
        return numeri[r][c];
    }
}