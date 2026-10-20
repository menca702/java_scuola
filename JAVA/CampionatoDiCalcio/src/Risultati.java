public class Risultati {

    int totSquadra; // numero totale di squadre
    int[][] risultati;
    int count;

    public Risultati(int totSquadra) {
        this.risultati = new int[totSquadra][2];
        this.totSquadra = totSquadra;
    }

    public void add(int p1, int p2) {

        risultati[count][0] = p1;
        risultati[count][1] = p2;
        count++;
    }

    public String toString(String s1, String s2) {
        if (count == 0) {
            return "Nessun risultato disponibile";
        }

        int i = count - 1;
        return s1 + ": " + risultati[i][0] + " punti - " + s2 + ": " + risultati[i][1] + " punti";
    }

}
