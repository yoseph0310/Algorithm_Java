package Z_DataStructure.ArrayList;

import Z_DataStructure.InterfaceForm.List;

import java.util.Arrays;

public class ArrayList<E> implements List<E>, Cloneable {

    private static final int DEFAULT_CAPACITY = 10; // 최소(기본) 크기
    private static final Object[] EMPTY_ARRAY = {};

    private int size;   // 요소 개수

    Object[] array; // 요소를 담을 배열

    // 생성자 (초기 공간 할당 X)
    public ArrayList() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    // 생성자 (초기 공간 할당 O)
    public ArrayList(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
    }

    private void resize() {
        int array_capacity = array.length;

        // 만일 array의 용량이 0이면
        if (Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        // 용량이 가득 찼을 경우
        if (size == array_capacity) {
            int new_capacity = array_capacity * 2;

            // copy
            array = Arrays.copyOf(array, new_capacity);
            return;
        }

        // 용량의 절반 미만으로 요소가 차지하고 있을 경우
        if (size < (array_capacity / 2)) {
            int new_capacity = array_capacity / 2;

            // copy
            array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
        }
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    public void addLast(E value) {

        // 꽉 차있으면 용량 재할당
        if (size == array.length) {
            resize();
        }

        array[size] = value;    // 마지막 위치에 요소 추가
        size++;                 // 용량 1 증가
    }

    public void addFirst(E value) {
        add(0, value);
    }

    @Override
    public void add(int index, E value) {
        if (index > size || index < 0) {    // 범위를 벗어나는지 먼저 확인
            throw new IndexOutOfBoundsException();
        }

        if (index == size) {                // index가 마지막 위치면 addLast 메소드로 요소 추가
            addLast(value);
        } else {

            if (size == array.length) {     // 꽉 차있으면 용량 재설정
                resize();
            }

            // index 기준 뒤에 있는 요소들을 뒤로 미루는 코드
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }

            array[index] = value;
            size++;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        E element = (E) array[index];       // 삭제될 요소를 임시로 담아줌
        array[index] = null;

        // 삭제한 요소의 뒤에 있는 요소들을 한 칸씩 당겨온다.
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
            array[i + 1] = null;
        }
        size--;
        resize();

        return element;
    }

    @Override
    public boolean remove(Object value) {

        int index = indexOf(value);

        // 만일 index 가 -1이라면 요소가 없다는 것이므로 false 반환
        if (index == -1) {
            return false;
        }

        // index 를 활용해 요소 삭제
        remove(index);

        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        // Object 타입에서 E 타입으로 캐스팅 후 반환
        return (E) array[index];
    }

    @Override
    public void set(int index, E value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            array[index] = value;
        }
    }

    @Override
    public boolean contains(Object value) {

        return indexOf(value) >= 0;

    }

    @Override
    public int indexOf(Object value) {
        int i = 0;

        for (i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    public int lastIndexOf(Object value) {
        for (int i = size; i >= 0; i--) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        resize();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        // 새로운 객체 생성
        ArrayList<?> cloneList = (ArrayList<?>) super.clone();

        // 새로운 객체의 배열도 생성해주어야 한다. (객체는 얕은 복사가 되기 때문)
        cloneList.array = new Object[size];

        // 배열의 값을 복사
        System.arraycopy(array, 0, cloneList.array, 0, size);

        return cloneList;
    }

    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            // copyOf(원본 배열, 복사할 길이, Class<? extends T[]> 타입)
            return (T[]) Arrays.copyOf(array, size, a.getClass());
        }

        // 원본 배열, 원본 배열 시작위치, 복사할 배열, 복사할 배열 시작위치, 복사할 요소 수
        System.arraycopy(array, 0, a, 0, size);

        return a;
    }
}
