package co.edu.uniquindio.poo.Comparator;

import java.util.Comparator;

import co.edu.uniquindio.poo.model.Paquete;

public class ComparadorTiempo implements Comparator<Paquete>{

    @Override 
    public int compare(Paquete p1, Paquete p2){
        return Integer.compare(p1.getTiempoEstimado(), p2.getTiempoEstimado()
        );
    }



}
