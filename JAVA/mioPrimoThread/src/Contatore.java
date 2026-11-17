public class Contatore extends Thread {

    String nome;
    int da, a;

    public Contatore(String n, int da, int a) {
        nome = n;
        this.da = da;
        this.a = a;
    }

    public void run() {

        // codice thread = output numero

        for (int i = da; i <= a; i++) {

            System.out.println(nome + ": " + i);

        }

    }
}