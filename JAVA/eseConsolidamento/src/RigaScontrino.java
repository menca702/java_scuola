class RigaScontrino {
    
    String prodotto;
    int quantita;
    double prezzoUnitario;

    public RigaScontrino(String prodotto, int quantita, double prezzoUnitario) {
        this.prodotto = prodotto; 
        this.quantita = quantita; 
        this.prezzoUnitario = prezzoUnitario;
    }
    public String getProdotto() { return prodotto; }
    public int getQuantita() { return quantita; }
    public double getPrezzoUnitario() { return prezzoUnitario; }
    public double getPrezzoParziale() { return quantita * prezzoUnitario; }

    @Override
    public String toString() {
        return prodotto + " x" + quantita + " @ " + prezzoUnitario + " = " + getPrezzoParziale();
    }
}