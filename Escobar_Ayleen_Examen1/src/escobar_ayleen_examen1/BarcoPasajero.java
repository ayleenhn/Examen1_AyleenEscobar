
package escobar_ayleen_examen1;

import java.util.Arrays;
import javax.swing.JOptionPane;

public final class BarcoPasajero extends Barco{
    private String [] nombrePasajeros;
    //private int maxBarco;
    private final double precioBoleto;
    private int cantPasajeros; 

    public BarcoPasajero(String nombre,int maxBarco, double precioBoleto) {
        super("Barco de Pasajeros");
        this.nombrePasajeros = new String[maxBarco];
        this.precioBoleto = precioBoleto;
        this.cantPasajeros = 0;
    }
    
    @Override
    public void agregarElemento() {
        if (cantPasajeros<nombrePasajeros.length ) {
            String nombreP=JOptionPane.showInputDialog("Ingrese el nombre del pasajero");
            nombrePasajeros[cantPasajeros++] = nombreP;
        }else{
           JOptionPane.showMessageDialog(null, "El barco ha llegado a su capacidad maxima.");
        }
    }

    @Override
    public double vaciarCobrar() {
        double totalCobrado = cantPasajeros * precioBoleto;
        Arrays.fill(nombrePasajeros, null);
        cantPasajeros = 0;
        return totalCobrado;
    }

    @Override
    public double precioElemento() {
        return precioBoleto;
    }
    
    @Override
    public String toString() {
        return super.toString() + "Precio Boleto: " + precioBoleto + ", Pasajeros que compraron boleto: " + cantPasajeros;
    }
    
    public void listarPasajeros() {
        StringBuilder pasajeros = new StringBuilder("Pasajeros que compraron boleto:\n");

        if (cantPasajeros == 0) {
            pasajeros.append("No hay pasajeros en el barco.");
        } else {
            for (int num = 0; num < cantPasajeros; num++) {
                pasajeros.append(nombrePasajeros[num]).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, pasajeros.toString(), "Lista de Pasajeros", JOptionPane.INFORMATION_MESSAGE);
    }
}
