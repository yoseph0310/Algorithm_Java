package Z_DataStructure.Queue.LinkedQueue;

public class Node<E> {

    E data;
    Node<E> next;

    Node(E data) {
        this.data = data;
        this.next = null;
    }
}
