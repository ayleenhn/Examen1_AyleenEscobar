
package escobar_ayleen_examen1;

import java.util.Date;

public abstract class Barco {
    private final String nombre;
    private final Date fecha;

    public Barco(String nombre) {
        this.nombre = nombre;
        this.fecha = new Date();
    }

    public final String getNombre() {
        return nombre;
    }

    public final Date getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    public abstract void agregarElemento();
    public abstract double vaciarCobrar();
    public abstract double precioElemento();
}
