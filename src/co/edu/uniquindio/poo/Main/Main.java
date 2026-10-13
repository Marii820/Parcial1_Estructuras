package co.edu.uniquindio.poo.Main;

import co.edu.uniquindio.poo.model.Paquete;

public class Main {
    public static void main(String[] args){
        Paquete p1 = new Paquete("PQ731", "Armenia", 4.5, 5, 45);
        Paquete p2 = new Paquete("PQ105", "Salento", 3.0, 3, 25);

        System.out.println(p1);
        System.out.println(p2);

        System.out.println(p1.compareTo(p2));
    }
}
