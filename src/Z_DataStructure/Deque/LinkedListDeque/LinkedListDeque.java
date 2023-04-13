package Z_DataStructure.Deque.LinkedListDeque;

import Z_DataStructure.A_InterfaceForm.QueueInterface;
import Z_DataStructure.LinkedList.Singly.SinglyLinkedList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class LinkedListDeque<E> implements QueueInterface<E>, Cloneable {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedListDeque() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean offerFirst(E value) {
        Node<E> newNode = new Node<>(value);
        newNode.next = head;

        if (head != null) {
            head.prev = newNode;
        }

        head = newNode;
        size++;

        if (head.next == null) {
            tail = head;
        }

        return true;
    }

    @Override
    public boolean offer(E item) {
        return offerLast(item);
    }

    public boolean offerLast(E item) {
        if (size == 0) {
            return offerFirst(item);
        }

        Node<E> newNode = new Node<>(item);

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;

        return true;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    public E pollFirst() {
        if (size == 0) {
            return null;
        }

        E element = head.data;

        Node<E> nextNode = head.next;

        head.data = null;
        head.next = null;

        if (nextNode != null) {
            nextNode.prev = null;
        }
        head = null;
        head = nextNode;
        size--;

        if (size == 0) {
            tail = null;
        }

        return element;
    }

    public E remove() {
        return removeFirst();
    }

    public E removeFirst() {
        E element = poll();

        if (element == null) {
            throw new NoSuchElementException();
        }

        return element;
    }

    public E pollLast() {
        if (size == 0) {
            return null;
        }

        E element = tail.data;

        Node<E> prevNode = tail.prev;

        tail.data = null;
        tail.prev = null;

        if (prevNode != null) {
            prevNode.next = null;
        }

        tail = null;
        tail = prevNode;
        size--;

        if (size == 0) {
            head = null;
        }

        return element;
    }

    public E removeLast() {
        E element = pollLast();

        if (element == null) {
            throw new NoSuchElementException();
        }

        return element;
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    public E peekFirst() {
        if (size == 0) {
            return null;
        }

        return head.data;
    }

    public E peekLast() {
        if (size == 0) {
            return null;
        }

        return tail.data;
    }

    public E element() {
        return getFirst();
    }

    public E getFirst() {
        E item = peek();

        if (item == null) {
            throw new NoSuchElementException();
        }

        return item;
    }

    public E getLast() {
        E item = peekLast();

        if (item == null) {
            throw new NoSuchElementException();
        }

        return item;
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
        for (Node<E> x = head; x != null;) {
            Node<E> next = x.next;

            x.data = null;
            x.prev = null;
            x.next = null;
            x = next;
        }

        size = 0;
        head = tail = null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        @SuppressWarnings("unchecked")
        LinkedListDeque<? super E> clone = (LinkedListDeque<? super E>) super.clone();

        clone.head = null;
        clone.tail = null;
        clone.size = 0;

        for (Node<E> x = head; x != null; x = x.next) {
            clone.offerLast(x.data);
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
