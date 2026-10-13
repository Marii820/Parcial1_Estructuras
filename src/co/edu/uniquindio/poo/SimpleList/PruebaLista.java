package co.edu.uniquindio.poo.SimpleList;

public class PruebaLista {

    public static void main(String[] args) {

        SimpleLinkedList<String> lista = new SimpleLinkedList<>();

        lista.add("PQ105");
        lista.add("PQ942");
        lista.add("PQ731");

        System.out.println("Lista inicial:");
        lista.print();

        System.out.println("Tamaño: " + lista.size());

        lista.remove("PQ942");

        System.out.println("Después de eliminar PQ942:");
        lista.print();

        System.out.println("Elemento en posición 1: "
                + lista.get(1));

        System.out.println("¿Está vacía? " + lista.isEmpty());
    }

}
