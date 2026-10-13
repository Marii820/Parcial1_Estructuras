package co.edu.uniquindio.poo.Comparator;

import java.util.Comparator;

import co.edu.uniquindio.poo.model.Paquete;

public class ComparadorPeso implements Comparator<Paquete> {

    @Override
    public int compare(Paquete p1, Paquete p2) {
        return Double.compare(p2.getPeso(), p1.getPeso());
    }
}