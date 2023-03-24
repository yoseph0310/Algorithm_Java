package Z_DataStructure.LinkedList.Doubly;

public class Node<E> {

    E data;
    Node<E> prev;
    Node<E> next;

    Node(E data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

}
