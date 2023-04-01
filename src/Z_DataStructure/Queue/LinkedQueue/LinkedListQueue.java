package Z_DataStructure.Queue.LinkedQueue;

import Z_DataStructure.A_InterfaceForm.QueueInterface;
import Z_DataStructure.LinkedList.Singly.SinglyLinkedList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class LinkedListQueue<E> implements QueueInterface<E>, Cloneable {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean offer(E value) {
        Node<E> newNode = new Node<>(value);

        // 비어있을 경우
        if (size == 0) {
            head = newNode;
        } else {
            tail.next = newNode;        // 그 외의 경우 마지막 노드의 다음 노드 변수가 새노드를 가리키도록한다.
        }

        /**
         * tail을 새 노드로 변경
         */
        tail = newNode;
        size++;

        return true;
    }

    @Override
    public E poll() {

        if (size == 0) {
            return null;
        }

        // 삭제할 요소의 데이터와 다음 노드 정보를 담는다
        E element = head.data;
        Node<E> nextNode = head.next;

        head.data = null;
        head.next = null;

        // head 가 가리키는 노드를 삭제된 head 노드의 다음노드를 가리키도록 변경
        head = nextNode;
        size--;

        return element;
    }

    public E remove() {

        E element = poll();

        if (element == null) {
            throw new NoSuchElementException();
        }

        return element;
    }

    @Override
    public E peek() {

        if (size == 0) {
            return null;
        }

        return head.data;
    }

    public E element() {

        E element = peek();

        if (element == null) {
            throw new NoSuchElementException();
        }

        return element;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object value) {
        for(Node<E> x = head; x != null; x = x.next) {
            if (value.equals(x.data)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for(Node<E> x = head; x != null; x = x.next) {

            Node<E> next = x.next;
            x.data = null;
            x.next = null;
            x = next;
        }

        size = 0;
        head = tail = null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        @SuppressWarnings("unchecked")
        LinkedListQueue<? super E> clone = (LinkedListQueue<? super E>) super.clone();

        clone.head = null;
        clone.tail = null;
        clone.size = 0;

        for (Node<E> x = head; x != null; x = x.next) {
            clone.offer(x.data);
        }

        return clone;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        int idx = 0;
        for (Node<E> x = head; x != null; x = x.next){
            array[idx++] = (E) x.data;
        }

        return array;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            // Arrays.newInstance(컴포넌트 타입, 생성할 크기)
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = a;
        for (Node<E> x = head; x != null; x = x.next) {
            result[i++] = x.data;
        }

        return a;
    }

    public void sort() {
        /**
         * Comparator 를 넘겨주지 않는 경우 해당 객체의 Comparable 에 구현된 정렬 방식을 사용한다.
         * 만약 구현되어 있지 않다면 cannot be cast to class java.lang.Comparable 에러가 발생.
         * 만약 구현되어 있다면 null 로 파라미터를 넘기면
         * Arrays.sort() 가 객체의 compareTo 메소드에 정의된 방식대로 정렬한다.
         */
        sort(null);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);

        int i = 0;
        for (Node<E> x = head; x != null; x = x.next, i++) {
            x.data = (E) a[i];
        }

    }
}
