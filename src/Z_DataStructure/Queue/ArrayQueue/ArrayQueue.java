package Z_DataStructure.Queue.ArrayQueue;

import Z_DataStructure.A_InterfaceForm.QueueInterface;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements QueueInterface<E>, Cloneable {

    private static final int DEFAULT_CAPACITY = 64;

    private Object[] array;
    private int size;

    private int front;
    private int rear;

    public ArrayQueue() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    public ArrayQueue(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    private void resize(int newCapacity) {
        int arrayCapacity = array.length;

        Object[] newArray = new Object[newCapacity];

        /**
         * i = new Array index
         * j = original array
         * index 요소 개수(size) 만큼 새 배열에 값 복사
         */
        for (int i = 1, j = front + 1; i <= size; i++, j++) {
            newArray[i] = array[j % arrayCapacity];
        }

        this.array = newArray;  // 새 배열을 기존 array 의 배열로 덮는다.

        front = 0;
        rear = size;
    }

    @Override
    public boolean offer(E item) {

        // 용량이 가득 찼을 경우
        if ((rear + 1) % array.length == front) {
            resize(array.length * 2);
        }

        // rear 을 rear 의 다음 위치로 갱신
        rear = (rear + 1) % array.length;

        array[rear] = item;
        size++;

        return true;
    }

    @Override
    public E poll() {

        if (size == 0) {
            return null;
        }

        front = (front + 1) % array.length;

        @SuppressWarnings("unchecked")
        E item = (E) array[front];  // 반환할 데이터 임시 저장

        array[front] = null;
        size--;

        if (array.length > DEFAULT_CAPACITY && size < (array.length) / 4) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }

        return item;
    }

    public E remove() {
        E item = poll();

        if (item == null) {
            throw new NoSuchElementException();
        }

        return item;
    }

    @Override
    public E peek() {

        if (size == 0) {
            return null;
        }

        @SuppressWarnings("unchecked")
        E item = (E) array[(front + 1)  % array.length];

        return item;
    }

    public E element() {
        E item = peek();

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
        int start = (front + 1) % array.length;

        /**
         * i : 요소 개수만큼만 반복한다.
         * idx : 요소 위치로, 매 회 (idx + 1) % array.length; 의 위치로 갱신
         */
        for (int i = 0, idx = start; i < size; i++, idx = (idx + 1) % array.length) {
            if (array[idx].equals(value)) {
                return true;
            }
        }

        return false;
    }

    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }

        front = rear = size = 0;
    }

    public Object[] toArray() {
        return toArray(new Object[size]);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        final T[] res;

        // 들어오는 배열의 길이가 큐의 요소 개수보다 작은 경우
        if (a.length < size) {

            /**
             * front 가 rear 보다 앞에 있을 경우 (또는 요소가 없을 경우 front == rear)
             */

            if (front <= rear) {
                return (T[]) Arrays.copyOfRange(array, front + 1, rear + 1, a.getClass());
            }

            /**
             * front 가 rear 보다 뒤에 있을 경우
             */
            res = (T[]) Arrays.copyOfRange(array, 0, size, a.getClass());
            int rearLength = array.length - 1 - front; // 뒷 부분 요소 개수

            // 뒷 부분 복사
            if (rearLength > 0) {
                System.arraycopy(array, front + 1, res, 0, rearLength);
            }

            // 앞 부분 복사
            System.arraycopy(array, 0, res, rearLength, rear + 1);

            return res;
        }

        /**
         * front 가 rear 보다 앞에 있을 경우 (또는 요소가 없을 경우 front == rear)
         */
        if (front <= rear) {
            System.arraycopy(array, front + 1, a, 0, size);
        }
        /**
         * front 가 rear 보다 뒤에 있을 경우
         */
        else {
            int rearLength = array.length - 1 - front;

            if (rearLength > 0) {
                System.arraycopy(array, front + 1, a, 0, rearLength);
            }

            System.arraycopy(array, 0, a, rearLength, rear + 1);
        }

        return a;
    }

    @Override
    public Object clone() {

        // super.clone()은 CloneNotSupportedException 예외를 처리해줘야 함.
        try {
            @SuppressWarnings("unchecked")
            ArrayQueue<E> clone = (ArrayQueue<E>) super.clone();

            // 깊은 복사
            clone.array = Arrays.copyOf(array, array.length);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    public void sort() {
        /**
         * Comparator 를 넘겨주지 않은 경우 해당 객체에 구현된 Comparable 에 따라 정렬한다.
         * 만양 구현되지 않았다면 cannot be cast to class java.lang.Comparable 에러가 발생한다.
         * 구현되어 있을 경우 Null 로 파라미터를 넘기면 Arrays.sort()가 객체의 compareTo 메소드에 정의된 방식대로 정렬.
         */
        sort(null);
    }

    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super E> c) {
        // null 접근 방지를 위해 toArray 로 요소만 있는 배열을 얻어서 이를 정렬한뒤 덮어 씌운다.
        Object[] res = toArray();

        Arrays.sort((E[]) res, 0, size, c);

        clear();

        /*
            정렬된 원소를 다시 array 에 0부터 다시 채운다.
            이 때 front = 0, front 는 비워야 하기 때문에 사실상 1 부터 채워야 한다.
         */
        System.arraycopy(res, 0, array, 1, res.length);

        this.rear = this.size = res.length;
    }
}
