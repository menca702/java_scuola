import java.util.Arrays;

public class Giocatore {
    
    Cartella[] cartelle;
 	String nome;
 	String immagine;

    public Giocatore(String nome, String immagine) {
        cartelle = new Cartella[0];
        this.nome = nome;
        this.immagine = immagine;
    }

    //crea una copia dell'array aumentando la dimensione di 1
    public void add(Cartella c) {
        cartelle = Arrays.copyOf(cartelle, cartelle.length + 1);
        cartelle[cartelle.length - 1] = c;
    }

    //viene chiamato dalla classe Tombola per estrarre un numero
    public void cancella(int numeroEstratto) {
        for (int i = 0; i < cartelle.length; i++) {
            cartelle[i].cancella(numeroEstratto);
        }
    }


    //entrambi i metodi scorrono tutte le cartelle richiamando il metodo specifico nella classe cartella
    public boolean haCombinazione(int quanti) {
        for (int i = 0; i < cartelle.length; i++) {
            if (cartelle[i].haCombinazione(quanti)) {
                return true;
            }
        }
        return false;
    }
 
    public boolean haTombola() {
        for (int i = 0; i < cartelle.length; i++) {
            if (cartelle[i].haTombola()) {
                return true;
            }
        }
        return false;
    }

    //GETTER+ALTRI METODI
    
    public String getName() {
        return nome;
    }
 
    public String getImmagine() {
        return immagine;
    }
 
    public int getNumeroCartelle() {
        return cartelle.length;
    }
 
    public Cartella getCartella(int n) {
        return cartelle[n];
    }
    
}
