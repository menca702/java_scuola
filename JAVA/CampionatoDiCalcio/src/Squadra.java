import java.util.Arrays;

public class Squadra {

    String nome;
    String[] giocatori;
    int[] golFatti;
    int count;

    public Squadra(String nome, String[] giocatori, int[] golFatti) {
        this.nome = nome;
        this.giocatori = giocatori;
        this.golFatti = golFatti;
    }

    public void add(String nome, int n) {
        giocatori = Arrays.copyOf(giocatori, count + 1);
        golFatti = Arrays.copyOf(golFatti, count + 1);

        giocatori[count] = nome;
        golFatti[count] = n;

        count++;
    }

    public String getGiocatori(int i) {
        return giocatori[i];
    }

}
