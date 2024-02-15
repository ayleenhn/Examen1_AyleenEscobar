
package escobar_ayleen_examen1;

import java.util.Date;

public final class BarcoPesquero extends Barco{
    private int pecesCapturados;
    private TipoPesquero tipo;

    public BarcoPesquero(String nombre,int pecesCapturados, TipoPesquero tipo) {
        super("Barco Pesquero");
        this.pecesCapturados = 0;
        this.tipo = tipo;
    }
    
    @Override
    public void agregarElemento() {
        pecesCapturados++;
    }

    @Override
    public double vaciarCobrar() {
        double total = pecesCapturados* tipo.price;
        pecesCapturados=0;
        return total;
    }

    @Override
    public double precioElemento() {
        double precioE= tipo.price;
        return precioE;
    }

    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipo + ", Cantidad de Peces Capturados: " + pecesCapturados;
    }
    
    
}
