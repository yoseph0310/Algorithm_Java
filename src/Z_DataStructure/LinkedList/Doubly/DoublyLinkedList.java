package Z_DataStructure.LinkedList.Doubly;

import Z_DataStructure.A_InterfaceForm.ListInterface;

import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements ListInterface<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private Node<E> search(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        // tail 에서부터 탐색
        if (index + 1 > size / 2) {
            Node<E> x = tail;

            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }

            return x;

        } else {    // head 에서부터 탐색
            Node<E> x = head;

            for (int i = 0; i < index; i++) {
                x = x.next;
            }

            return x;
        }
    }

    public void addFirst(E value) {
        Node<E> newNode = new Node<>(value);
        newNode.next = head;

        /**
         * head 가 null 이 아닐 경우에만 기존 head 노드의 prev 변수가 새 노드를 가리키도록 한다.
         * 이유는 기존 head 노드가 없는 경우 (null)는 데이터가
         * 아무 것도 없는 상태이므로 head.prev 를 하게 되면 잘못된 참조가 된다.
         */
        if (head != null) {
            head.prev = newNode;
        }

        head = newNode;
        size++;

        /**
         * 다음에 가리킬 노드가 없는 경우 ( 데이터가 새 노드 밖에 없는 경우)
         * 데이터가 한개라는 뜻이므로 새노드는 처음 시작노드이자 끝 노드이다.
         */
        if (head.next == null) {
            tail = head;
        }
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    public void addLast(E value) {
        Node<E> newNode = new Node<>(value);

        if (size == 0) {
            addFirst(value);
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }

    @Override
    public void add(int index, E value) {
        // 범위 체크
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        // index 가 0이면 가장 맨 앞이므로 addFirst 활용
        if (index == 0) {
            addFirst(value);
            return;
        }

        // index 가 size 와 같다면 마지막이므로 addLast 활용
        if (index == size) {
            addLast(value);
            return;
        }

        Node<E> prevNode = search(index - 1);       // 넣으려는 위치 이전 노드
        Node<E> nextNode = prevNode.next;                 // 넣으려는 위치의 노드
        Node<E> newNode = new Node<>(value);              // 새로운 노드

        // unlinking
        prevNode.next = null;
        newNode.prev = null;

        // linking
        prevNode.next = newNode;        // prev 노드의 next 에 새 노드에 연결

        newNode.prev = prevNode;        // 새 노드의 prev 는 이전 노드에
        newNode.next = nextNode;        // 새 노드의 next 는 다음 노드에 연결

        newNode.prev = newNode;         // next 노드의 prev 에 새노드에 연결

        // size 증가
        size++;
    }

    public E remove() {
        Node<E> headNode = head;

        if (headNode == null) {
            throw new NoSuchElementException();
        }

        E element = headNode.data;

        Node<E> nextNode = head.next;

        head.data = null;
        head.next = null;

        /**
         * head 의 다음 노드가 null 이 아닐 경우만 prev 변수를 업데이트 해야한다.
         * nextNode 가 없는 경우는 데이터가 아무것도 없는 상태이므로 잘못된 참조가 된다.
         */
        if (nextNode != null) {
            nextNode.prev = null;
        }

        head = nextNode;
        size--;

        /**
         * 삭제된 요소가 리스트의 유일한 요소였을 경우
         * 그 요소가 head 이자 tail 이었으므로
         * 삭제되면서 tail 도 가리킬 요소가 없기 때문에
         * size 가 0일 경우 tail 도 null 로 변환
         */
        if (size == 0) {
            tail = null;
        }

        return element;
    }

    @Override
    public E remove(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            E element = head.data;
            remove();
            return element;
        }

        Node<E> prevNode = search(index - 1);
        Node<E> removeNode = prevNode.next;
        Node<E> nextNode = removeNode.next;

        E element = removeNode.data;

        /**
         * index == 0 일 때 조건에서 이미 head 노드 삭제에 대한 분기가 있기 때문에
         * prevNode 는 항상 존재 한다.
         *
         * 그러나 nextNode 의 경우는 null 일 수 있기 때문에
         * 이전처럼 반드시 검사를 해준 뒤에 nextNode.prev 에 접근해야 한다.
         */

        prevNode.next = null;
        removeNode.data = null;
        removeNode.next = null;
        removeNode.prev = null;

        if (nextNode != null) {
            nextNode.prev = prevNode;
            prevNode.next = nextNode;
        }

        /**
         * nextNode 가 null 이라면 마지막 노드를 삭제했다는 의미임
         * 따라서 prevNode 가 tail 이 된다.
         */
        if (nextNode == null) {
            tail = prevNode;
        }

        size--;

        return element;
    }

    @Override
    public boolean remove(Object value) {

        Node<E> prevNode = head;
        Node<E> x = head;       // removeNode

        // value 와 일치하는 노드를 찾는다;
        for (; x != null; x = x.next) {
            if (value.equals(x.data)) {
                break;
            }
            prevNode = x;
        }

        // 찾으려는 요소가 없을 경우 false 반환
        if (x == null) {
            return false;
        }

        // 삭제하려는 노드가 head 일 경우 remove() 삭제
        if (x.equals(head)) {
            remove();
            return true;
        }

        // remove(int index) 와 같은 매커니즘으로 삭제
        else {
            Node<E> nextNode = x.next;

            prevNode.next = null;
            x.data = null;
            x.next = null;
            x.prev = null;

            if (nextNode != null) {
                nextNode.prev = prevNode;
                prevNode.next = nextNode;
            } else {
                tail = prevNode;
            }

            size--;

            return true;
        }
    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public void set(int index, E value) {
        Node<E> replaceNode = search(index);
        replaceNode.data = value;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(Object value) {
        int index = 0;

        for (Node<E> x = head; x != null; x = x.next) {
            if (value.equals(x.data)) {
                return index;
            }
            index++;
        }

        return -1;
    }

    public int lastIndexOf(Object value) {
        int index = size;

        for (Node<E> x = tail; x != null; x = x.prev) {
            if (value.equals(x.data)) {
                return index;
            }
            index--;
        }

        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (Node<E> x = head; x != null; x = x.next) {
            Node<E> nextNode = x.next;

            x.data = null;
            x.next = null;
            x.prev = null;

            x = nextNode;
        }

        head = tail = null;
        size = 0;
    }
}
