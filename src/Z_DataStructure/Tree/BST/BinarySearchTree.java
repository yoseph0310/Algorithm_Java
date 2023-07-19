package Z_DataStructure.Tree.BST;

import java.util.Comparator;

public class BinarySearchTree<E> {

    private Node<E> root;       // 루트 (최상단) 노드
    private int size;           // 요소(노드)의 개수

    private final Comparator<? super E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
        this.root = null;
        this.size = 0;
    }
}
