package Z_DataStructure.LinkedList.Singly;

import Z_DataStructure.A_InterfaceForm.List;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E>, Cloneable {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private Node<E> search(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> x = head;   // head 가 가리키는 노드부터 시작

        for (int i = 0; i < index; i++) {
            x = x.next;     // x 노드의 다음 노드를 x에 저장
        }

        return x;
    }

    public void addFirst(E value) {

        Node<E> newNode = new Node<>(value);    // 새 노드 생성
        newNode.next = head;
        head = newNode;
        size++;

        /**
         * 다음에 가리킬 노드가 없는 경우( 데이터가 새 노드 밖에 없는 경우)
         * 데이터가 한 개(새 노드)밖에 없으므로 새 노드는 처음 시작 노드이자
         * 마지막 노드다. 즉 tail = head 이다.
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

        /**
         * 마지막 노드와 다음 노드가 새 노드를 가리키도록 하고
         * tail이 가리키는 노드를 새 노드로 바꾼다.
         */
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    @Override
    public void add(int index, E value) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(value);
            return;
        }

        if (index == size) {
            addLast(value);
            return;
        }

        Node<E> prevNode = search(index - 1);
        Node<E> nextNode = prevNode.next;
        Node<E> newNode = new Node<>(value);

        /**
         * 이전 노드가 가리키는 노드를 끊고
         * 새 노드로 변경한다.
         * 또한 새 노드가 가리키는 노드는 next_Node 로 변경한다.
         */
        prevNode.next = null;
        prevNode.next = newNode;
        newNode.next = nextNode;
        size++;
    }

    public E remove() {
        Node<E> headNode = head;

        if (headNode == null) throw new NoSuchElementException();

        E element = headNode.data;

        Node<E> nextNode = head.next;

        head.data = null;
        head.next = null;

        head = nextNode;
        size--;

        /**
         * 삭제된 요소가 리스트의 유일한 요소일 경우
         * 그 요소는 head 이자 tail 이므로
         * 삭제 되면서 tail 도 가리킬 요소가 없기 때문에
         * size 가 0일 경우 tail 도 Null 로 해준다.
         */
        if (size == 0) {
            tail = null;
        }

        return element;
    }

    @Override
    public E remove(int index) {

        if (index == 0) {
            return remove();
        }

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> prevNode = search(index - 1);
        Node<E> removeNode = prevNode.next;
        Node<E> nextNode = removeNode.next;

        E element = removeNode.data;

        prevNode.next = nextNode;

        if (prevNode.next == null) {
            tail = prevNode;
        }

        removeNode.next = null;
        removeNode.data = null;
        size--;

        return element;
    }

    @Override
    public boolean remove(Object value) {
        Node<E> prevNode = head;
        boolean hasValue = false;
        Node<E> x = head;   // removeNode

        // value 와 일치하는 노드를 찾는다.
        for (; x != null; x = x.next) {
            if (value.equals(x.data)) {
                hasValue = true;
                break;
            }
            prevNode = x;
        }

        // 일치하는 요소가 없을 경우 false 반환
        if (x == null) {
            return false;
        }

        // 삭제하려는 노드가 head 라면 기존 remove 사용
        if (x.equals(head)) {
            remove();
            return true;
        } else {
            // 이전 노드의 링크를 삭제하려는 노드의 다음 노드로 연결
            prevNode.next = x.next;

            // 만약 삭제했던 도느가 마지막 노드라면 tail 을 prevNode 로 갱신
            if (prevNode.next == null) {
                tail = prevNode;
            }

            x.data = null;
            x.next = null;
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
            x = nextNode;
        }

        head = tail = null;
        size = 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        @SuppressWarnings("unchecked")
        SinglyLinkedList<? super E> clone = (SinglyLinkedList<? super E>) super.clone();

        clone.head = null;
        clone.tail = null;
        clone.size = 0;

        for (Node<E> x = head; x != null; x = x.next) {
            clone.addLast(x.data);
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
