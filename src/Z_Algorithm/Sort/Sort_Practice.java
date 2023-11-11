package Z_Algorithm.Sort;

public class Sort_Practice {

    static int[] arr;
    public static void main(String[] args) {
        initArr();

        // 정렬 종류
        // 버블
        System.out.println("---- Bubble Sort ----");
        sortByBubble(arr);

        // 선택
        System.out.println("---- Selection Sort ----");
        sortBySelection(arr);

        // 삽입
        System.out.println("---- Insertion Sort ----");
        sortByInsertion(arr);

        // 셸
        System.out.println("---- Shell Sort ----");
        sortByShell(arr);

        // 퀵
        System.out.println("---- Quick Sort ----");


        // 병합
        System.out.println("---- Merge Sort ----");

        // 힙
        System.out.println("---- Heap Sort ----");

        // 기수
        System.out.println("---- Radix Sort ----");

        // 계수
        System.out.println("---- Counting Sort ----");

    }

    public static void sortByQuick(int[] arr) {

    }
    private static void quickSort(int[] arr, int left, int right) {

    }
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];

        while (left <= right) {

        }

        return 0;
    }

    public static void sortByShell(int[] arr) {
        for (int h = arr.length / 2; h > 0; h /= 2) {
            for (int i = h; i < arr.length; i++) {
                int tmp = arr[i];
                int j = i - h;

                while (j >= 0 && arr[j] > tmp) {
                    arr[j + h] = arr[j];
                    j -= h;
                }

                arr[j + h] = tmp;
            }
        }

        printArr(arr);
        initArr();
    }

    public static void sortByInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];

            int j = i - 1;
            while (j >= 0 && tmp < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = tmp;
        }

        printArr(arr);
        initArr();
    }

    public static void sortBySelection(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }

            swap(arr, i, minIdx);
        }

        printArr(arr);
        initArr();
    }

    public static void sortByBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) swap(arr, j, j + 1);
            }
        }

        printArr(arr);
        initArr();
    }

    public static void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }

    public static void initArr() {
        arr = new int[]{1, 20, 13, 56, 99, 22, 65, 78, 23, 55};
    }

    public static void printArr(int[] arr) {
        for (int n: arr) {
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
