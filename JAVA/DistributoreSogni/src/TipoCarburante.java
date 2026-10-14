public enum TipoCarburante {
 
    BENZINA(false),
    DIESEL(false),
    GPL(true),
    METANO(true);
 
    private final boolean misuratoInKg;
 
    // costruttore privato per l'enum
    TipoCarburante(boolean misuratoInKg) {
        this.misuratoInKg = misuratoInKg;
    }
 
    // metodo per verificare se il carburante è misurato in kg
    public boolean isMisuratoInKg() {
        return misuratoInKg;
    }
 
    // metodo per ottenere l'unità di misura del carburante
    public String unitaDiMisura() {
        return misuratoInKg ? "kg" : "litri";
    }
 
}