public class App {
    public static void main(String[] args) throws Exception {
        
        Giocatore[] g = new Giocatore[4];
    
        g[0] = new Giocatore("Marco", "img/Marco.jpg");
        g[0].add(new Cartella());
        g[0].add(new Cartella());
        g[0].add(new Cartella());
    
        g[1] = new Giocatore("Elisa", "img/Elisa.jpg");
        g[1].add(new Cartella());
        g[1].add(new Cartella());
    
        g[2] = new Giocatore("Marianna", "img/Marianna.jpg");
        g[2].add(new Cartella());
    
        g[3] = new Giocatore("Federico", "img/Federico.jpg");
        g[3].add(new Cartella());
    
        Tombola t = new Tombola();
        for (int i = 0; i < g.length; i++) {
            t.add(g[i]);
        }
    
        System.out.println("Situazione iniziale");
        for (int i = 0; i < g.length; i++) {
            System.out.println(g[i].getName() + ": " + g[i].getNumeroCartelle() + " cartelle");
        }
    
        // gioca finché qualcuno non fa tombola
        while (t.vincitore(15) == null) {
            t.gioca();
        }
    
        System.out.println("Situazione finale");
        System.out.println("Tombola! Ha vinto " + t.vincitore(15).getName());
    }

}
