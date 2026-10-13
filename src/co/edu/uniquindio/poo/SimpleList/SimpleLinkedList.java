package co.edu.uniquindio.poo.SimpleList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<T> implements ICollection<T> {
    private  int size;
    private NodeSimple<T> firstNode;

    public SimpleLinkedList(){
        this.size=   0;
        firstNode = null;
    }

    public int getSize(){
        return size;
    }
    public void setSize(int size){
        this.size=size;
    }

    public void addFirst(T data){
        NodeSimple<T> newNode = new  NodeSimple<>(data);
        newNode.setNext(firstNode);
        size++;
    }

    public void addLast(T data){
        NodeSimple<T> newNode= new NodeSimple<>(data);

        if(isEmpty()){
            firstNode= newNode;
        } else {
            NodeSimple<T> aux = firstNode;

            while (aux.getNext() != null){
                aux= aux.getNext();
        }

        aux.setNext(newNode);
    }

    size ++;
}
// Operación definida por ICollection
    @Override
    public void add(T element){
        addLast(element);
    }

// Obtener elemento por su posición
    @Override
    public T get(int index){
        validarIndice(index);

        NodeSimple<T> aux= firstNode;

        for(int i= 0; i < index; i ++){
            aux= aux.getNext();
        }

        return aux.getData();
    }

    //Eliminar la primera aparición de un elemento
    @Override
    public boolean remove (T element){
        if (isEmpty()) {
            return false;
        }

        //Elemento al inicio
        if(Objects.equals(firstNode.getData(), element)){
            firstNode= firstNode.getNext();
            size--;
            return true;
        }

        //Elemento en la mitad o al final
        NodeSimple<T> aux= firstNode;

        while(aux.getNext() != null){
            if (Objects.equals(aux.getNext().getData(), element)){
                aux.setNext(aux.getNext().getNext());
                size--;
                return true;
            }

            aux = aux.getNext();
        }

        return false;
    }

    // Cantidad de elementos
    @Override
    public int size(){
        return size;
    }

    //Consultar si está vacía
    @Override
    public boolean isEmpty(){
        return firstNode == null;
    }

    //Eliminar todos los elementos
    @Override
    public void clear(){
        firstNode = null;
        size = 0;
    }

    // Insertar en una posición
    public void agregarPosicion(T data, int posicion) {
        if (posicion < 0 || posicion > size) {
            throw new IndexOutOfBoundsException(
                    "Posición inválida: " + posicion);
        }

        if (posicion == 0) {
            addFirst(data);
            return;
        }

        if (posicion == size) {
            addLast(data);
            return;
        }

        NodeSimple<T> nuevo = new NodeSimple<>(data);
        NodeSimple<T> aux = firstNode;

        for (int i = 0; i < posicion - 1; i++) {
            aux = aux.getNext();
        }

        nuevo.setNext(aux.getNext());
        aux.setNext(nuevo);
        size++;
    }

    // Buscar la posición de un elemento
    public int obtenerPosicionNodo(T data) {
        NodeSimple<T> aux = firstNode;
        int posicion = 0;

        while (aux != null) {
            if (Objects.equals(aux.getData(), data)) {
                return posicion;
            }

            aux = aux.getNext();
            posicion++;
        }

        return -1;
    }

    // Validar una posición existente
    private void validarIndice(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Índice inválido: " + index);
        }
    }

    // Recorrer la lista sin exponer sus nodos
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private NodeSimple<T> actual = firstNode;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                T data = actual.getData();
                actual = actual.getNext();

                return data;
            }
        };
    }

    // Imprimir todos los elementos
    public void print() {
        for (T element : this) {
            System.out.println(element);
        }
    }



}