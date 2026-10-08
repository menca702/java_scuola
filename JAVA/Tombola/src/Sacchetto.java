import java.util.Random;

public class Sacchetto {
  
    private int[] dischetti;
    private int cont; // quanti numeri sono ancora nel sacchetto
    private Random random;

    public Sacchetto() {

        dischetti = new int[90];

        for (int i = 0; i < dischetti.length; i++) {
            dischetti[i] = i + 1;
        }
        
        cont = dischetti.length;
        random = new Random();
    }

    //se il sacchetto è vuoto restituisce null, altrimenti estrae un numero casuale e lo restituisce
    //mette il numero estratto in fondo all'array e decrementa cont + (il numero che era in fondo viene messo al posto dell'indice)

    public Dischetto estrai() {
        if (cont == 0) {
            return null;
        }
        int indice = random.nextInt(cont);   // numero casuale tra 0 e cont-1 (89)
        int valore = dischetti[indice];
 
        // scambio: porto il numero estratto in fondo alla zona "ancora dentro"
        dischetti[indice] = dischetti[cont - 1];
        dischetti[cont - 1] = valore;
        cont--;
 
        return new Dischetto(valore, "dischetto" + valore + ".png");
    }

    public boolean isEstratto(int n) {
        for (int i = cont; i < dischetti.length; i++) {
            if (dischetti[i] == n) {
                return true;
            }
        }
        return false;
    }
 
    public int quantiEstratti() {
        return dischetti.length - cont;
    }
}