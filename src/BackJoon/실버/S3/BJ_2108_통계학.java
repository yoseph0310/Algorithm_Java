package BackJoon.실버.S3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_2108_통계학 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[8001];

        /*
         * sun = 총 합
         * max = 최댓값
         * min = 최솟값
         * median = 중앙값
         * mode = 최빈값
         */

        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        int median = 10000;
        int mode = 10000;

        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(br.readLine());
            sum += value;
            arr[value + 4000]++;

            if (max < value) max = value;
            if (min > value) min = value;
        }

        int cnt = 0;        // 중앙값 빈도 누적 수
        int mode_max = 0;   // 최빈값의 최대값

        boolean flag = false;   // 이전의 동일한 최빈값이 1번만 등장했을 경우 true, 아닐 경우 false

        for (int i = min + 4000; i <= max + 4000; i++) {
            if (arr[i] > 0) {
                if (cnt < (N + 1) / 2) {
                    cnt += arr[i];
                    median = i - 4000;
                }

                if (mode_max < arr[i]) {
                    mode_max = arr[i];
                    mode = i - 4000;
                    flag = true;
                } else if (mode_max == arr[i] && flag) {
                    mode = i - 4000;
                    flag = false;
                }
            }
        }

        System.out.println((int)Math.round((double)sum / N));
        System.out.println(median);
        System.out.println(mode);
        System.out.println(max - min);
    }
}
