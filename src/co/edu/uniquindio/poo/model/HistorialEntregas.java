package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.SimpleList.SimpleLinkedList;

public class HistorialEntregas {
    private SimpleLinkedList<Paquete> paquetesEntregados;

    public HistorialEntregas() {
        paquetesEntregados = new SimpleLinkedList<>();
    }

    public void registrarEntrega(Paquete paquete) {
        if (paquete == null) {
            throw new IllegalArgumentException(
                    "El paquete no puede ser nulo"
            );
        }

        paquetesEntregados.add(paquete);
    }

    // Consulta cuántos paquetes se han entregado
    public int obtenerCantidadEntregas() {
        return paquetesEntregados.size();
    }

    // Comprueba si el historial está vacío
    public boolean estaVacio() {
        return paquetesEntregados.isEmpty();
    }

    // Consulta una entrega por su posición
    public Paquete obtenerEntrega(int posicion) {
        return paquetesEntregados.get(posicion);
    }

    // Muestra las entregas en el orden en que se registraron
    public void mostrarHistorial() {
        if (estaVacio()) {
            System.out.println("No hay entregas registradas.");
            return;
        }

        for (Paquete paquete : paquetesEntregados) {
            System.out.println(paquete);
        }
    }
}
