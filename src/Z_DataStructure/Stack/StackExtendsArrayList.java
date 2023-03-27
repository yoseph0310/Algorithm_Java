package Z_DataStructure.Stack;

import Z_DataStructure.A_InterfaceForm.StackInterface;
import Z_DataStructure.ArrayList.ArrayList;

import java.util.EmptyStackException;

public class StackExtendsArrayList<E> extends ArrayList<E> implements StackInterface<E> {

    // 초기 용량 할당 X
    public StackExtendsArrayList() {
        super();
    }

    public StackExtendsArrayList(int capacity) {
        super(capacity);
    }


    @Override
    public E push(E item) {
        addLast(item); // ArrayList 의 addLast()
        return item;
    }

    @Override
    public E pop() {
        int length = size();
        E obj = remove(length - 1); // ArrayList 의 remove()

        return obj;
    }

    @Override
    public E peek() {

        int length = size();
        if (length == 0) {
            throw new EmptyStackException();
        }

        E obj = get(length - 1); // ArrayList 의 get()

        return obj;
    }

    @Override
    public int search(Object value) {
        int idx = lastIndexOf(value); // ArrayList 의 lastIndexOf()

        if (idx >= 0) {
            return size() - idx;
        }

        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
