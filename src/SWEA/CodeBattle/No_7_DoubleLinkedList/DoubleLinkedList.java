package SWEA.CodeBattle.No_7_DoubleLinkedList;

class Node {
    public int data;
    public Node prev;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

public class DoubleLinkedList {

    private final static int MAX_NODE = 10000;

    private Node[] node = new Node[MAX_NODE];
    private int nodeCnt = 0;
    private Node head;

    public Node getNode(int data) {
        node[nodeCnt] = new Node(data);
        return node[nodeCnt++];
    }

    // 초기화
    public void init() {
        head = new Node(0);

        head.next = null;
        head.prev = null;

        nodeCnt = 0;
    }

    // addFirst
    public void addNode2Head(int data) {
        Node newNode = getNode(data);

        newNode.next = head.next;
        newNode.prev = head;
        head.next = newNode;
    }

    // addLast
    public void addNode2Tail(int data) {
        addNode2Num(data, nodeCnt + 1);
    }

    // add
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

    // search
    public int findNode(int data) {
        Node node = head;
        int idx = 0;

        for (; node != null; node = node.next) {
            if (data == node.data) {
                break;
            }
            idx++;
        }

        return idx;
    }

    // remove
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

    public int getReversedList(int[] output) {
        int idx = nodeCnt - 1;
        for (Node x = head.next; x != null; x = x.next) {
            output[idx--] = x.data;
        }

        return nodeCnt;
    }
}
