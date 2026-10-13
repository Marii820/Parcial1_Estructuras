package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.Comparator.ComparadorTiempo;
import co.edu.uniquindio.poo.Comparator.ComparadorPeso;
import co.edu.uniquindio.poo.Comparator.ComparadorPrioridad;
import co.edu.uniquindio.poo.Comparator.ComparadorDespacho;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Collections;

public class CentroDistribución {

    private Queue<Paquete> paquetesPendientes;
    private Map<String, Paquete> paquetesPorCodigo;
    private Set<String> municipios;
    private PriorityQueue<Paquete> colaDespacho;
    private Map<String, Repartidor> repartidores;
    private HistorialEntregas historialEntregas;
    private Map<String, List<Paquete>> paquetesPorDestino;
    private Map<String, Repartidor> repartidoresAsignados;

    public CentroDistribución() {
        paquetesPendientes = new LinkedList<>();
        paquetesPorCodigo = new HashMap<>();
        municipios = new TreeSet<>();

        //Condiciones prioridad de salida del paquete
        colaDespacho = new PriorityQueue<>(
                Comparator.comparingInt(Paquete::getPrioridad)
                        .reversed()
                        .thenComparingInt(Paquete::getTiempoEstimado)
                        .thenComparing(Paquete::getCodigo));

        repartidores = new HashMap<>();
        historialEntregas = new HistorialEntregas();
        paquetesPorDestino = new HashMap<>();
        repartidoresAsignados= new HashMap<>();
    }


    public void registrarPaquete(Paquete paquete) {
        if (paquete == null) {
            throw new IllegalArgumentException("El paquete no puede ser nulo");
        }

        String codigo = paquete.getCodigo();

        if (paquetesPorCodigo.containsKey(codigo)) {
            throw new IllegalArgumentException(
                    "Ya existe un paquete con ese código");
        }

        paquetesPendientes.add(paquete);
        paquetesPorCodigo.put(codigo, paquete);
        paquetesPorDestino
                .computeIfAbsent(paquete.getDestino(), destino -> new ArrayList<>())
                .add(paquete);
        municipios.add(paquete.getDestino());
        colaDespacho.add(paquete);
    }

    public Paquete buscarPaquete(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código es obligatorio");

        }
        return paquetesPorCodigo.get(codigo);
    }

    public Paquete consultarSiguienteDespacho() {

        return colaDespacho.peek();
    }



    public boolean puedeEntregar(Paquete paquete, Repartidor repartidor) {
        if (paquete == null || repartidor == null) {
            return false;
        }

        return repartidor.isDisponible()
                && repartidor.getZona().equalsIgnoreCase(paquete.getDestino());
    }

    public List<Paquete> consultarPaquetesPorDestino(String destino) {
        if (destino == null || destino.isBlank()) {
            throw new IllegalArgumentException("El destino es obligatorio");
        }

        List<Paquete> paquetes = paquetesPorDestino.get(destino);

        if (paquetes == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(paquetes);
    }


    public void registrarRepartidor(Repartidor repartidor) {
        if (repartidor == null) {
            throw new IllegalArgumentException(
                    "El repartidor no puede ser nulo"
            );
        }

        String identificacion = repartidor.getIdentificacion();

        if (repartidores.containsKey(identificacion)) {
            throw new IllegalArgumentException(
                    "Ya existe un repartidor con esa identificación"
            );
        }

        repartidores.put(identificacion, repartidor);
    }

    public void mostrarRepartidores() {
        if (repartidores.isEmpty()) {
            System.out.println("No hay repartidores registrados");
            return;
        }

        for (Repartidor repartidor : repartidores.values()) {
            System.out.println(repartidor);
        }
    }


    public Repartidor buscarRepartidorDisponible(Paquete paquete) {
        if (paquete == null) {
            return null;
        }

        for (Repartidor repartidor : repartidores.values()) {
            if (puedeEntregar(paquete, repartidor)) {
                return repartidor;
            }
        }

        return null;
    }


    public Paquete despacharPaquete() {
        List<Paquete> paquetesRevisados = new ArrayList<>(); //Lista temporal que guarda los paquetes que no tengan
                                                             //quién los entregue en ese momento. (Mantiene los paquetes para que no se pierdan al sacarlos de la cola)
        Paquete paqueteSeleccionado = null;                  //Variables que empiezan vacías,
        Repartidor repartidorSeleccionado = null;            //que guardarán el paquete y el repartidor

        while (!colaDespacho.isEmpty()) {
            Paquete paquete = colaDespacho.poll();

            Repartidor repartidor = buscarRepartidorDisponible(paquete);

            if (repartidor != null) {
                paqueteSeleccionado = paquete;
                repartidorSeleccionado = repartidor;
                break;
            }

            paquetesRevisados.add(paquete);
        }

        colaDespacho.addAll(paquetesRevisados);

        if (paqueteSeleccionado == null) {
            System.out.println("No hay paquetes que puedan despacharse actualmente.");
            return null;
        }

        paquetesPendientes.remove(paqueteSeleccionado);

        repartidoresAsignados.put(
                paqueteSeleccionado.getCodigo(),
                repartidorSeleccionado
        );

        repartidorSeleccionado.setDisponible(false);

        return paqueteSeleccionado;
    }


    public void registrarEntrega(String codigoPaquete) {
        if (codigoPaquete == null || codigoPaquete.isBlank()) {
            throw new IllegalArgumentException("El código es obligatorio");
        }

        Paquete paquete = paquetesPorCodigo.get(codigoPaquete);
        Repartidor repartidor = repartidoresAsignados.get(codigoPaquete);

        if (paquete == null || repartidor == null) {
            throw new IllegalArgumentException(
                    "El paquete no tiene una asignación activa"
            );
        }

        historialEntregas.registrarEntrega(paquete);

        repartidoresAsignados.remove(codigoPaquete);
        repartidor.setDisponible(true);
    }




    public Set<String> obtenerMunicipios() {
        return new TreeSet<>(municipios);
    }


    public void mostrarHistorialEntregas() {
        historialEntregas.mostrarHistorial();
    }

    public int obtenerCantidadEntregas() {
        return historialEntregas.obtenerCantidadEntregas();
    }


    public double calcularPesoPorDestino(String destino) {
        if (destino == null || destino.isBlank()) {
            throw new IllegalArgumentException("El destino es obligatorio");
        }

        List<Paquete> paquetes = new ArrayList<>(paquetesPorCodigo.values());

        return calcularPesoRecursivo(paquetes, destino, 0);
    }

    private double calcularPesoRecursivo(List<Paquete> paquetes, String destino, int indice) {

        // Caso base: Ya se recorrió toda la lista
        if (indice >= paquetes.size()) {
            return 0.0;
        }

        Paquete paquete = paquetes.get(indice);

        // Caso recursivo: calcular el peso del resto
        double pesoRestante = calcularPesoRecursivo(
                paquetes, destino, indice + 1
        );

        if (paquete.getDestino().equalsIgnoreCase(destino)) {
            return paquete.getPeso() + pesoRestante;
        }

        return pesoRestante;
    }

    public int contarPaquetesPorPrioridad(int prioridadMinima) {
        List<Paquete> paquetes = new ArrayList<>(paquetesPorCodigo.values());

        return contarPaquetesRecursivo(paquetes, prioridadMinima, 0);
    }

    private int contarPaquetesRecursivo(List<Paquete> paquetes, int prioridadMinima, int indice) {

        // Caso base: ya recorrimos todos los paquetes
        if (indice >= paquetes.size()) {
            return 0;
        }

        Paquete paquete = paquetes.get(indice);

        // Agregar paquete si cumple la condición
        int cantidadRestante = contarPaquetesRecursivo(
                paquetes, prioridadMinima, indice + 1);

        if (paquete.getPrioridad() >= prioridadMinima) {
            return 1 + cantidadRestante;
        }

        return cantidadRestante;
    }

    public Paquete buscarPaqueteBinario(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código es obligatorio");
        }

        List<Paquete> paquetes = new ArrayList<>(paquetesPorCodigo.values());

        Collections.sort(paquetes);

        int inicio = 0;
        int fin = paquetes.size() - 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;

            Paquete paquete = paquetes.get(medio);
            int comparacion = paquete.getCodigo().compareTo(codigo);

            if (comparacion == 0) {
                return paquete;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }

        return null;
    }


    //Obtiene los paquetes registrados y llama el método recursivo
    public Paquete buscarPaqueteMayorPeso() {
        List<Paquete> paquetes = new ArrayList<>(paquetesPorCodigo.values());

        if (paquetes.isEmpty()) {
            return null;
        }

        return buscarMayorPesoRecursivo(paquetes, 0, paquetes.size() - 1);
    }

    private Paquete buscarMayorPesoRecursivo(
            List<Paquete> paquetes, int inicio, int fin) {

        // Caso base: solo hay un paquete
        if (inicio == fin) {
            return paquetes.get(inicio);
        }

        // Dividir la lista en dos partes
        int medio = inicio + (fin - inicio) / 2;

        // Buscar el paquete más pesado de cada parte
        Paquete mayorIzquierda =
                buscarMayorPesoRecursivo(paquetes, inicio, medio);

        Paquete mayorDerecha =
                buscarMayorPesoRecursivo(paquetes, medio + 1, fin);

        // Comparar los resultados
        if (mayorIzquierda.getPeso() >= mayorDerecha.getPeso()) {
            return mayorIzquierda;
        }

        return mayorDerecha;
    }


    public List<Paquete> ordenarPorTiempo() {
        List<Paquete> paquetes =
                new ArrayList<>(paquetesPorCodigo.values());

        paquetes.sort(new ComparadorTiempo());

        return paquetes;
    }

    public List<Paquete> ordenarPorPeso() {
        List<Paquete> paquetes =
                new ArrayList<>(paquetesPorCodigo.values());

        paquetes.sort(new ComparadorPeso());

        return paquetes;
    }

    public List<Paquete> ordenarPorPrioridad() {
        List<Paquete> paquetes =
                new ArrayList<>(paquetesPorCodigo.values());

        paquetes.sort(new ComparadorPrioridad());

        return paquetes;
    }

    public List<Paquete> ordenarParaDespacho() {
        List<Paquete> paquetes =
                new ArrayList<>(paquetesPorCodigo.values());

        paquetes.sort(new ComparadorDespacho());

        return paquetes;
    }

    public List<Paquete> consultarPaquetesPendientes() {
        return new ArrayList<>(paquetesPendientes);
    }

}







