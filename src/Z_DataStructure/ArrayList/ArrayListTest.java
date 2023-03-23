package Z_DataStructure.ArrayList;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        Object[] array1 = list.toArray();

        Integer[] array2 = new Integer[10];
        array2 = list.toArray(array2);
    }
}
