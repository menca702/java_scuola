public class Tabellone {
  
    int[] numeri;

    public Tabellone() {
        numeri = new int[90];

        for(int i = 0; i < numeri.length; i++) {
            numeri[i] = i+1;
        }
    }

    //metodo che viene chiamato dalla classe Tombola per rimuovere il numero estratto dal tabellone
    public void togli(Dischetto discoEstratto) {
        int valore = discoEstratto.getNumero();
        numeri[valore - 1] = 0;
    }

    //metodo che restituisce true se il numero è già uscito, false altrimenti
    public boolean isUscito(int numero) {
        return numeri[numero - 1] == 0;
    }
    
}