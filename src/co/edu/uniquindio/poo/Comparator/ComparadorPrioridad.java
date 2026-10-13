package co.edu.uniquindio.poo.Comparator;

import java.util.Comparator;

import co.edu.uniquindio.poo.model.Paquete;

public class ComparadorPrioridad implements Comparator<Paquete> {

    @Override
    public int compare(Paquete p1, Paquete p2) {
        return Integer.compare(p2.getPrioridad(), p1.getPrioridad());
    }
}
