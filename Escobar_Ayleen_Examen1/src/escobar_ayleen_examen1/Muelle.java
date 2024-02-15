
package escobar_ayleen_examen1;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Muelle {
    public ArrayList<Barco> listaBarcos;
    
    public Muelle() {
        this.listaBarcos = new ArrayList<>();
    }
    
    public static void main(String[] args) {
            Muelle muelle = new Muelle();

            int opcion;
            do {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(
                        "Menú:\n" +
                                "1. Agregar Barco\n" +
                                "2. Agregar Elemento a Barco\n" +
                                "3. Vaciar Barco\n" +
                                "4. Listar Barcos desde un año\n" +
                                "5. Salir"));

                switch (opcion) {
                    case 1:
                        String tipoBarco = JOptionPane.showInputDialog("Ingrese el tipo de barco (PESQUERO/PASAJERO): ");
                        muelle.AgregarBarco(tipoBarco);
                        break;
                        
                    case 2:
                        String nombreBarcoAgregarElemento = JOptionPane.showInputDialog("Ingrese el nombre del barco: ");
                        muelle.AgregarElemento(nombreBarcoAgregarElemento);
                        break;
                        
                    case 3:
                        String nombreBarcoVaciar = JOptionPane.showInputDialog("Ingrese el nombre del barco: ");
                        double totalGenerado = muelle.VaciarBarco(nombreBarcoVaciar);
                        JOptionPane.showMessageDialog(null, "Total generado:" + totalGenerado, "Información", JOptionPane.INFORMATION_MESSAGE);
                        break;
                        
                    case 4:
                        int year = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año: "));
                        //muelle.barcosDesde(listaBarcos, year);
                        break;
                        
                    case 5:
                        break;
                        
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } while (opcion != 5);
        }

        public void AgregarBarco(String tipo) {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del barco: ");
            if (nombreRepetido(nombre)) {
                JOptionPane.showMessageDialog(null, "Ya existe un barco con ese nombre. Intente con otro nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if ("PESQUERO".equalsIgnoreCase(tipo)) {
                BarcoPesquero pesquero = new BarcoPesquero(nombre, 0, obtenerTipoPesquero());
                listaBarcos.add(pesquero);
            } else if ("PASAJERO".equalsIgnoreCase(tipo)) {
                BarcoPasajero pasajero = new BarcoPasajero(nombre,obtenerCapacidadMaxima(), obtenerPrecioBoleto());
                listaBarcos.add(pasajero);
            } else {
                JOptionPane.showMessageDialog(null, "Tipo de barco no válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        public void AgregarElemento(String nombre) {
            Barco barco = buscarBarco(nombre);
            if (barco != null) {
                barco.agregarElemento();
                JOptionPane.showMessageDialog(null, "Elemento agregado al barco " + nombre, "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el barco con nombre " + nombre, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        public double VaciarBarco(String nombre) {
            Barco barco = buscarBarco(nombre);
            if (barco != null) {
                double totalGenerado = barco.vaciarCobrar();
                JOptionPane.showMessageDialog(null, "Datos del Barco:\n" + barco, "Información", JOptionPane.INFORMATION_MESSAGE);
                if (barco instanceof BarcoPasajero) {
                    ((BarcoPasajero) barco).listarPasajeros();
                }
                return totalGenerado;
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el barco con nombre " + nombre, "Error", JOptionPane.ERROR_MESSAGE);
                return 0.0;
            }
        }


        public void barcosDesde(ArrayList<Barco> barcos, int year) {
            StringBuilder mensaje = new StringBuilder("Barcos desde el año " + year + ":\n");
            barcosDesde(listaBarcos, year);
            JOptionPane.showMessageDialog(null, mensaje.toString(), "Información", JOptionPane.INFORMATION_MESSAGE);
            for (Barco barco : barcos) {
                if (barco.getFecha().getYear() >= year) {
                    mensaje.append("Nombre: ").append(barco.getNombre()).append(" Fecha: ").append(barco.getFecha()).append("\n");
                }

                if (barco instanceof BarcoPasajero) {
                    BarcoPasajero pasajero = (BarcoPasajero) barco;
                    mensaje.append("Pasajeros que compraron boleto:\n");
                }
            }
        }

        public Barco buscarBarco(String nombre) {
            for (Barco barco : listaBarcos) {
                if (barco.getNombre().equalsIgnoreCase(nombre)) {
                    return barco;
                }
            }
            return null;
        }

        public boolean nombreRepetido(String nombre) {
            for (Barco barco : listaBarcos) {
                if (barco.getNombre().equalsIgnoreCase(nombre)) {
                    return true;
                }
            }
            return false;
        }

        public TipoPesquero obtenerTipoPesquero() {
            String tipoPesqueroStr = JOptionPane.showInputDialog("Ingrese el tipo de pesquero (PEZ/CAMARON/LANGOSTA): ").toUpperCase();

            try {
                return TipoPesquero.valueOf(tipoPesqueroStr);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Tipo de pesquero no válido. Se usará PEZ por defecto.", "Error", JOptionPane.ERROR_MESSAGE);
                return TipoPesquero.PEZ;
            }
        }

        public int obtenerCapacidadMaxima() {
            return Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad máxima del barco pasajero: "));
        }

        public double obtenerPrecioBoleto() {
            return Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del boleto del barco pasajero: "));
        }
}
