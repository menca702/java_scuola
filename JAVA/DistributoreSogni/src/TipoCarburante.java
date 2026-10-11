public enum TipoCarburante {

    BENZINA(false),
    DIESEL(false),
    GPL(true),
    METANO(true);


    private final boolean misuratoInKg;

    TipoCarburante(boolean misuratoInKg) {
        this.misuratoInKg = misuratoInKg;
    }

    public boolean isMisuratoInKg() {
        return misuratoInKg;
    }

    public String unitaDiMisura() {
        return misuratoInKg ? "kg" : "litri";
    }

}
