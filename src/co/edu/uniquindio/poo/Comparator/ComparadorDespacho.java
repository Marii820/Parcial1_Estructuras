package co.edu.uniquindio.poo.Comparator;
import java.util.Comparator;

import co.edu.uniquindio.poo.model.Paquete;

public class ComparadorDespacho implements Comparator<Paquete> {

    @Override 
    public int compare(Paquete p1, Paquete p2){

        int resultado = Integer.compare(p2.getPrioridad(), p1.getPrioridad()
        );
        if (resultado != 0 ){
            return resultado;
        }
        resultado= Integer.compare(p1.getTiempoEstimado(), p2.getTiempoEstimado()
        );
        if (resultado != 0){
            return  resultado;
        }

        return p1.getCodigo().compareTo(p2.getCodigo());
    }

    
}
