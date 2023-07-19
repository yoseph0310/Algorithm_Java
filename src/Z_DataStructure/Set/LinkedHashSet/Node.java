package Z_DataStructure.Set.LinkedHashSet;

public class Node<E> {

    final int hash;
    final E key;

    Node<E> next;       // for Separate Chaining

    Node<E> prevLink;   // for linked list(set)
    Node<E> nextLink;   // for linked list(set)

    public Node(int hash, E key, Node<E> next) {
        this.hash = hash;
        this.key = key;
        this.next = next;

        this.prevLink = null;
        this.nextLink = null;
    }
}
