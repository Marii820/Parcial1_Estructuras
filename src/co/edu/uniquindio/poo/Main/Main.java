package co.edu.uniquindio.poo.Main;

import co.edu.uniquindio.poo.model.CentroDistribución;
import co.edu.uniquindio.poo.model.Paquete;
import co.edu.uniquindio.poo.model.HistorialEntregas;
import co.edu.uniquindio.poo.model.Repartidor;

public class Main {
    public static void main(String[] args) {

        CentroDistribución centro= new CentroDistribución();

        centro.registrarRepartidor(new Repartidor("R001", "Carlos Pérez", "Armenia", true));
        centro.registrarRepartidor(new Repartidor("R002", "Laura Gómez", "Armenia", true));
        centro.registrarRepartidor(new Repartidor("R003", "Andrés López", "Calarcá", true));
        centro.registrarRepartidor(new Repartidor("R004", "María Torres", "Salento", true));
        centro.registrarRepartidor(new Repartidor("R005", "Juan Ramírez", "Montenegro", true));
        centro.registrarRepartidor(new Repartidor("R006", "Sofía Martínez", "Quimbaya", true));

        // Paquetes con destino a Armenia
        centro.registrarPaquete(new Paquete("P001", "Armenia", 5.5, 5, 20));
        centro.registrarPaquete(new Paquete("P002", "Armenia", 2.0, 3, 15));
        centro.registrarPaquete(new Paquete("P003", "Armenia", 8.2, 4, 30));
        centro.registrarPaquete(new Paquete("P004", "Armenia", 1.5, 2, 10));
        centro.registrarPaquete(new Paquete("P005", "Armenia", 6.0, 5, 25));
        centro.registrarPaquete(new Paquete("P006", "Armenia", 3.4, 1, 18));

        // Paquetes con destino a Calarcá
        centro.registrarPaquete(new Paquete("P007", "Calarcá", 4.0, 4, 22));
        centro.registrarPaquete(new Paquete("P008", "Calarcá", 7.5, 5, 35));
        centro.registrarPaquete(new Paquete("P009", "Calarcá", 2.3, 2, 12));
        centro.registrarPaquete(new Paquete("P010", "Calarcá", 9.0, 3, 28));
        centro.registrarPaquete(new Paquete("P011", "Calarcá", 1.8, 1, 16));
        centro.registrarPaquete(new Paquete("P012", "Calarcá", 5.2, 4, 24));

        // Paquetes con destino a Salento
        centro.registrarPaquete(new Paquete("P013", "Salento", 3.0, 5, 14));
        centro.registrarPaquete(new Paquete("P014", "Salento", 6.7, 3, 26));
        centro.registrarPaquete(new Paquete("P015", "Salento", 2.5, 4, 19));
        centro.registrarPaquete(new Paquete("P016", "Salento", 10.0, 5, 40));
        centro.registrarPaquete(new Paquete("P017", "Salento", 4.4, 2, 21));
        centro.registrarPaquete(new Paquete("P018", "Salento", 1.2, 1, 11));

        // Paquetes con destino a Montenegro
        centro.registrarPaquete(new Paquete("P019", "Montenegro", 7.0, 4, 27));
        centro.registrarPaquete(new Paquete("P020", "Montenegro", 3.6, 5, 17));
        centro.registrarPaquete(new Paquete("P021", "Montenegro", 8.5, 3, 32));
        centro.registrarPaquete(new Paquete("P022", "Montenegro", 2.1, 2, 13));
        centro.registrarPaquete(new Paquete("P023", "Montenegro", 5.8, 4, 23));
        centro.registrarPaquete(new Paquete("P024", "Montenegro", 1.9, 1, 15));

        // Paquetes con destino a Quimbaya
        centro.registrarPaquete(new Paquete("P025", "Quimbaya", 4.8, 5, 20));
        centro.registrarPaquete(new Paquete("P026", "Quimbaya", 9.5, 4, 38));
        centro.registrarPaquete(new Paquete("P027", "Quimbaya", 2.7, 3, 16));
        centro.registrarPaquete(new Paquete("P028", "Quimbaya", 6.3, 5, 29));
        centro.registrarPaquete(new Paquete("P029", "Quimbaya", 3.2, 2, 18));
        centro.registrarPaquete(new Paquete("P030", "Quimbaya", 1.6, 1, 12));

        System.out.println("Sistema QuindíoExpress iniciado correctamente.");

        System.out.println("Sistema QuindíoExpress iniciado correctamente.");
        System.out.println("Municipios registrados: " + centro.obtenerMunicipios());
        System.out.println("Paquete P015: " + centro.buscarPaquete("P015"));
        System.out.println("Siguiente paquete para despacho: "
                + centro.consultarSiguienteDespacho());

        // 1. Buscar un paquete por código
        System.out.println("\n--- BÚSQUEDA POR CÓDIGO ---");
        System.out.println(centro.buscarPaquete("P015"));

        // 2. Calcular el peso total de un municipio
        System.out.println("\n--- PESO POR DESTINO ---");
        System.out.println("Peso total en Armenia: "
                + centro.calcularPesoPorDestino("Armenia"));

        // 3. Contar paquetes con prioridad mínima de 4
        System.out.println("\n--- PAQUETES POR PRIORIDAD ---");
        System.out.println("Paquetes con prioridad >= 4: "
                + centro.contarPaquetesPorPrioridad(4));

        // 4. Buscar el paquete de mayor peso
        System.out.println("\n--- PAQUETE MÁS PESADO ---");
        System.out.println(centro.buscarPaqueteMayorPeso());

//// 5. Realizar una búsqueda binaria
//        System.out.println("\n--- BÚSQUEDA BINARIA ---");
//        System.out.println(centro.buscarPaqueteBinario("P020"));

        // 6. Consultar los paquetes de un destino
        System.out.println("\n--- PAQUETES DE SALENTO ---");
        for (Paquete paquete : centro.consultarPaquetesPorDestino("Salento")) {
            System.out.println(paquete);
        }

        // 7. Mostrar los paquetes ordenados por prioridad
        System.out.println("\n--- ORDENADOS POR PRIORIDAD ---");
        for (Paquete paquete : centro.ordenarPorPrioridad()) {
            System.out.println(paquete);
        }


        System.out.println("\n--- DESPACHO DE PAQUETES ---");

        Paquete paqueteDespachado = centro.despacharPaquete();

        if (paqueteDespachado != null) {
            System.out.println("Paquete despachado: " + paqueteDespachado);
            centro.registrarEntrega(paqueteDespachado.getCodigo());
            System.out.println("Entrega registrada correctamente.");
        }

        System.out.println("\n--- HISTORIAL DE ENTREGAS ---");
        centro.mostrarHistorialEntregas();
        System.out.println("Cantidad de entregas: "
                + centro.obtenerCantidadEntregas());


        System.out.println("\n--- PAQUETES PENDIENTES ---");

        for (Paquete paquete : centro.consultarPaquetesPendientes()) {
            System.out.println(paquete.getCodigo());
        }

        Paquete paquete = centro.despacharPaquete();


    }





}
