import java.util.Arrays;

public class Classifiche {

    String[] podio;
    int count;

    public Classifiche() {
        podio = new String[3];
    }

    public void getPodio(int[] puntiSquadre, String[] nomiSquadre) {
        Arrays.sort(puntiSquadre);

        int n = puntiSquadre.length;

        // Primo posto
        if (n >= 1) {
        podio[0] = nomiSquadre[n - 1] + " - " + puntiSquadre[n - 1] + " punti";
        }
        // Secondo posto (il penultimo)
        if (n >= 2) {
            podio[1] = nomiSquadre[n - 2] + " - " + puntiSquadre[n - 2] + " punti";
        }
        // Terzo posto (il terzultimo)
        if (n >= 3) {
            podio[2] = nomiSquadre[n - 3] + " - " + puntiSquadre[n - 3] + " punti";
        }

    }

    public String toString(String s1, String s2) {
        return "Classifiche [primo posto (" + s1 + " - " + podio[0] + ")\nsecondo posto (" + s2 + " - " + podio[1] + ")\nterzo posto (" + podio[2] + ")]";
    }

}
