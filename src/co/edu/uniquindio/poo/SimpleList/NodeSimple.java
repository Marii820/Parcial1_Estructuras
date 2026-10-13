package co.edu.uniquindio.poo.SimpleList;
// Clase genérica

public class NodeSimple<T> {

    private T data;
    private NodeSimple<T> next; //nodos compatibles (mismo tipo de dato)

    public NodeSimple(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeSimple getNext() {
        return next;
    }

    public void setNext(NodeSimple<T> next) {
        this.next = next;
    }


}
