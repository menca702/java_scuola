import java.io.IOException;

public class PrincipaleThread {

    public static void main(String[] args) throws IOException {

        //nome significativo - in base allo scopo del thread
        Contatore c1 = new Contatore("Thread 1", 1, 10);
        Contatore c2 = new Contatore("Thread 2", 100, 800);

        //per farli partire
        c1.start();
        c2.start();

    }
}