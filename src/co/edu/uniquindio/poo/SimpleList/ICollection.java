package co.edu.uniquindio.poo.SimpleList;

//Establece qué operaciones debe ofrecer la colección, pero no explica cómo se realizan internamente.

public interface ICollection<T> extends Iterable<T> {

    void add(T element);

    boolean remove(T element);

    T get(int index);

    int size();

    boolean isEmpty();

    void clear();
}
