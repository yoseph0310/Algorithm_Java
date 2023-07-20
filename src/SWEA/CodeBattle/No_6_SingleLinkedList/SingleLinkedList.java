package SWEA.CodeBattle.No_6_SingleLinkedList;

class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class SingleLinkedList {

    private final static int MAX_NODE = 10000;

    private Node[] node = new Node[MAX_NODE];
    private int nodeCnt = 0;
    private Node head;

    public Node getNode(int data) {
        node[nodeCnt] = new Node(data);
        return node[nodeCnt++];
    }

    public void init() {
        head = new Node(0);
        head.next = null;
        nodeCnt = 0;
    }

    // addFirst
    public void addNode2Head(int data) {
        Node newNode = getNode(data);

        newNode.next = head.next;
        head.next = newNode;
    }

    // addLast
    public void addNode2Tail(int data) {
        addNode2Num(data, nodeCnt + 1);
    }

    // add - num 의 위치에 data 가 들어가야함
    public void addNode2Num(int data, int num) {
        Node newNode = getNode(data);
        Node prevNode = getPrevNode(num - 1);

        newNode.next = prevNode.next;
        prevNode.next = newNode;
    }

    private Node getPrevNode(int num) {
        Node node = head;

        for (int i = 0; i < num; i++) {
            node = node.next;
        }

        return node;
    }


    public void removeNode(int data) {
        Node prevNode = head;
        Node removeNode = head;

        for (; removeNode != null; removeNode = removeNode.next) {
            if (data == removeNode.data) {
                break;
            }
            prevNode = removeNode;
        }

        if (removeNode == null) return;

        prevNode.next = removeNode.next;

        removeNode = null;

        nodeCnt--;

    }

    public int getList(int[] output) {
        int idx = 0;
        for (Node x = head.next; x != null; x = x.next) {
            output[idx++] = x.data;
        }

        return nodeCnt;
    }
}