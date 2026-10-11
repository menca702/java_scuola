public class Veicolo {
    
    private String marca;
    private String modello;
    private TipoCarburante carburante;

    public Veicolo(String marca, String modello, TipoCarburante carburante) {
        this.marca = marca;
        this.modello = modello;
        this.carburante = carburante;
    }

    public String getMarca() { return marca; }
    public String getModello() { return modello; }
    public TipoCarburante getCarburante() { return carburante; }

    public void faiBenzina() {
        System.out.println("Rifornimento di " + carburante + " in corso... rifornimento completato!");
    }

    @Override 
    public String toString() {
        return marca + " " + modello + " (" + carburante + ")";
    }

}
