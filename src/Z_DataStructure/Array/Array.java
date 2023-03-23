package Z_DataStructure.Array;

public class Array {
    int[] intArr;
    int count;                  // 개수

    public int ARRAY_SIZE;
    public static final int ERROR_NUM = -999999999;

    // 기본 생성자. 이 예제에서 배열의 사이즈는 10으로 기본 생성한다.
    public Array() {
        count = 0;
        ARRAY_SIZE = 10;
        intArr = new int[ARRAY_SIZE];
    }

    // 사이즈를 받아 생성하는 생성자. 배열의 사이즈는 Size의 크기대로 생성한다.
    public Array(int size) {
        count = 0;
        ARRAY_SIZE = size;
        intArr = new int[size];
    }

    // 배열에 요소를 추가한다.
    public void addElement(int num) {
        if (count >= ARRAY_SIZE) {
            System.out.println("Not enough memory.");
            return;
        }
        intArr[count++] = num;
    }

    // 원하는 위치에 요소를 삽입.
    public void insertElement(int position, int num) {
        int i;

        if (count >= ARRAY_SIZE) {
            System.out.println("Not enough memory");
            return;
        }

        if (position < 0 || position > count) {
            System.out.println("IndexOutOfException");
            return;
        }

        for (i = count - 1; i >= position; i--) {
            intArr[i + 1] = intArr[i];
        }

        intArr[position] = num;
        count++;
    }

    // 원하는 위치의 요소를 삭제.
    public int removeElement(int position) {
        int ret = ERROR_NUM;

        if (isEmpty()) {
            System.out.println("There is no element");
            return ret;
        }

        if (position < 0 || position >= count) {
            System.out.println("IndexOutOfException");
            return ret;
        }

        ret = intArr[position];

        for (int i = position; i < count - 1; i++) {
            intArr[i] = intArr[i + 1];
        }
        count--;

        return ret;
    }

    // 원하는 위치의 요소 검색
    public int getElement(int position) {
        if (position < 0 || position > count - 1) {
            System.out.println("검색 위치 오류. 현재 리스트 개수는 " + count + "개 입니다.");
            return ERROR_NUM;
        }
        return intArr[position];
    }

    // 모든 요소 출력
    public void printAll() {
        if (count == 0) {
            System.out.println("출력할 내용이 없습니다.");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println(intArr[i]);
        }
    }

    // 모든 요소 삭제
    public void removeAll() {
        for (int i = 0; i < count; i++) {
            intArr[i] = 0;
        }
        count = 0;
    }

    // 배열 사이즈 반환
    public int getSize() {
        return count;
    }

    // 배열이 비었는지 확인
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }
}
