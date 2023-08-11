package Z_DataStructure.Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Heap<E> {

    private final Comparator<? super E> comparator;
    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    private Object[] array;

    // 생성자 (초기 공간 할당 X)
    public Heap() {
        this(null);
    }

    public Heap(Comparator<? super E> comparator) {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.comparator = comparator;
    }

    // 생성자 (초기 공간 할당 O)
    public Heap(int capacity) {
        this(capacity, null);
    }

    public Heap(int capacity, Comparator<? super E> comparator) {
        this.array = new Object[capacity];
        this.size = 0;
        this.comparator = comparator;
    }

    private int getParent(int index) {
        return index / 2;
    }

    private int getLeftChild(int index) {
        return index * 2;
    }

    private int getRightChild(int index) {
        return index * 2 + 1;
    }

    /**
     * @param newCapacity 새로운 용량 크기
     */
    private void resize(int newCapacity) {
        Object[] newArray = new Object[newCapacity];

        for (int i = 1; i <= size; i++) {
            newArray[i] = array[i];
        }

        /**
         * 현재 배열은 GC 처리를 위해 Null 로 처리한 뒤,
         * 새 배열을 연결.
         */
        this.array = null;
        this.array = newArray;
    }

    public void add(E value) {

        // 용량이 꽉찼으면 resize()
        if (size + 1 == array.length) {
            resize(array.length * 2);
        }

        siftUp(size + 1, value);
        size++;
    }

    /**
     *
     * @param idx 추가할 노드의 인덱스
     * @param target 재배치할 노드
     */
    private void siftUp(int idx, E target) {
        if (comparator != null) {
            siftUpComparator(idx, target, comparator);
        } else {
            siftUpComparable(idx, target);
        }
    }

    // Comparator 를 이용한 sift-up
    @SuppressWarnings("unchecked")
    private void siftUpComparator(int idx, E target, Comparator<? super E> comp) {
        // root 노드 보다 클 때까지만 탐색한다.
        while (idx > 1) {
            int parent = getParent(idx);        // 삽입 노드의 부모 노드 인덱스 구하기
            Object parentVal = array[parent];   // 부모노드의 값

            // 타겟 노드 값이 부모노드보다 크면 반복문 종료
            if (comp.compare(target, (E) parentVal) >= 0) {
                break;
            }

            /**
             * 부모 노드가 타겟 노드보다 값이 크므로
             * 현재 삽입될 위치에 부모 노드 값으로 교체해주고
             * 타겟 노드의 위치를 부모 노드의 위치로 변경한다.
             */
            array[idx] = parentVal;
            idx = parent;
        }

        // 최종적으로 삽입될 위치에 타겟 노드 값을 저장한다.
        array[idx] = target;
    }

    // 삽입할 객체의 Comparable 을 이용한 sift-up
    @SuppressWarnings("unchecked")
    private void siftUpComparable(int idx, E target) {
        // 타겟 노드가 비교될 수 있도록 한 변수를 만든다.
        Comparable<? super E> comp = (Comparable<? super E>) target;

        while (idx > 1) {
            int parent = getParent(idx);
            Object parentVal = array[parent];

            if (comp.compareTo((E) parentVal) >= 0) {
                break;
            }

            array[idx] = parentVal;
            idx = parent;
        }

        array[idx] = comp;
    }

    @SuppressWarnings("unchecked")
    public E remove() {
        if (array[1] == null) {
            throw new NoSuchElementException();
        }

        E result = (E) array[1];    // 삭제된 요소를 반환하기 위한 임시 변수
        E target = (E) array[size]; // 타겟이 될 요소

        // 삭제할 노드의 인덱스와 이후 재배치할 타겟 노드를 넘겨준다.
        siftDown(1, target);    // 루트 노드가 삭제되므로 1을 넘겨준다.

        return result;
    }

    /**
     *
     * @param idx   삭제할 노드의 인덱스
     * @param target    재배치 할 노드
     */
    private void siftDown(int idx, E target) {
        if (comparator != null) {
            siftDownComparator(idx, target, comparator);
        } else {
            siftDownComparable(idx, target);
        }
    }

    @SuppressWarnings("unchecked")
    private void siftDownComparator(int idx, E target, Comparator<? super E> comp) {

        array[idx] = null;
        size--;

        int parent = idx;
        int child;

        // 왼쪽 자식 노드의 인덱스가 요소의 개수보다 작을 동안만 반복
        while ((child = getLeftChild(parent)) <= size) {

            int right = getRightChild(parent);  // 오른쪽 자식 인덱스

            Object childVal = array[child]; // 왼쪽 자식의 값 (교환될 값)

            /*
             * 오른쪽 자식 인덱스가 size 를 넘지 않으면서
             * 왼쪽 자식이 오른쪽 자식보다 큰 경우
             * 재배치할 노드는 작은 자식과 비교해야 하므로 child 와 childVal 을 오른쪽 자식으로 바꿔준다.
             */
            if (right <= size && comp.compare( (E) childVal, (E) array[right]) > 0) {
                child = right;
                childVal = array[child];
            }

            // 재배치할 노드가 자식 노드보다 작을 경우 반복문 종료
            if (comp.compare(target, (E) childVal) <= 0) {
                break;
            }

            /**
             * 현재 부모 인덱스에 자식 노드 값을 대체해주고
             * 부모 인덱스를 자식 인덱스로 교체
             */
            array[parent] = childVal;
            parent = child;
        }

        array[parent] = target;

        /**
         * 용량이 최소 용량보다는 크면서 요소의 개수가 전체 용량의 1/4일 경우 resize()
         */
        if (array.length > DEFAULT_CAPACITY && size < array.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }
    }

    @SuppressWarnings("unchecked")
    private void siftDownComparable(int idx, E target) {
        Comparable<? super E> comp = (Comparable<? super E>) target;

        array[idx] = null;
        size--;

        int parent = idx;
        int child;

        while ((child = getLeftChild(parent)) <= size) {
            int right = getRightChild(parent);

            Object childVal = array[child];

            if (right <= size && ((Comparable <? super E>) childVal).compareTo((E) array[right]) > 0) {
                child = right;
                childVal = array[child];
            }

            if (comp.compareTo((E) childVal) <= 0) {
                break;
            }

            array[parent] = childVal;
            parent = child;
        }

        array[parent] = comp;

        if (array.length > DEFAULT_CAPACITY && size < array.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }
    }

    public int size() {
        return size;
    }
    @SuppressWarnings("unchecked")
    public E peek() {
        if (array[1] == null) {
            throw new NoSuchElementException();
        }
        return (E) array[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {
        return Arrays.copyOf(array, size + 1);
    }
}
