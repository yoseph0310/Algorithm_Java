package Programmers.LV2;
import java.util.*;

public class 숫자_카드_나누기 {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        Arrays.sort(arrayA);
        Arrays.sort(arrayB);

        int gcdA = cal(arrayA, arrayB);
        int gcdB = cal(arrayB, arrayA);
        answer = gcdA > gcdB ? gcdA : gcdB;

        return answer;
    }

    int cal(int[] arr1, int[] arr2) {
        List<Integer> div = get_div(arr1[0]);
        Collections.sort(div, Collections.reverseOrder());
        int i, j;
        boolean GCD;

        for (i = 0; i < div.size() - 1; i++) {
            GCD = false;
            int num = div.get(i);

            for (j = 0; j < arr1.length; j++) {
                if (arr1[j] % num != 0) break;
            }

            if (j == arr1.length) GCD = true;

            if (GCD) {
                for (j = 0; j < arr2.length; j++) {
                    if (arr2[j] % num == 0) break;
                }
                if (j == arr2.length) return num;
            }
        }

        return 0;
    }

    List<Integer> get_div(int a) {
        List<Integer> arr = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(a); i++) {
            if (a % i == 0) {
                arr.add(i);
                arr.add(a/i);
            }
        }
        return arr;
    }
}
