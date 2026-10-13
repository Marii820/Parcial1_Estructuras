package co.edu.uniquindio.poo.model;

public class Paquete implements Comparable<Paquete>{

    private String codigo;
    private String destino;
    private double peso;
    private int prioridad;
    private int tiempoEstimado;

    //Constructor válida los datos
    public Paquete (String codigo, String destino, double peso, int prioridad, int tiempoEstimado){

        if(codigo== null || codigo.isBlank()){  // OR ||
            throw new IllegalArgumentException("El código es obligatorio");
        }

        if(destino== null || destino.isBlank()){
            throw new IllegalArgumentException("El destino es obligatorio");
        }

        if(peso <=0){
            throw new IllegalArgumentException("El peso debe ser mayor a 0");
        }

        if(prioridad <1 || prioridad > 5){
            throw new IllegalArgumentException("La prioridad debe ser entre 1 y 5");
        }

        if(tiempoEstimado <=0){
            throw new IllegalArgumentException("El tiempo debe ser mayor a 0");
        }

        this.codigo= codigo;
        this.destino= destino;
        this.peso= peso;
        this.prioridad= prioridad;
        this.tiempoEstimado= tiempoEstimado;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    @Override
    // Orden natural: Por código de manera ascendente
    public int compareTo(Paquete otro) {
        return this.codigo.compareTo(otro.codigo);
    }

    @Override
    public String toString() {
        return "Paquete{" +
                "codigo='" + codigo + '\'' +
                ", destino='" + destino + '\'' +
                ", peso=" + peso +
                ", prioridad=" + prioridad +
                ", tiempoEstimado=" + tiempoEstimado +
                '}';
    }
}
