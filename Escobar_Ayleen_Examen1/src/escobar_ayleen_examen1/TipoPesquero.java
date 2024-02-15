package escobar_ayleen_examen1;

public enum TipoPesquero {
    PEZ(5), CAMARON(10), LANGOSTA(20);
    
    public final double price;

    private TipoPesquero(double price) {
        this.price = price;
    }
    
}
