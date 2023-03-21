package BackJoon.실버.S3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_2108_통계학 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int sum = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }
        Arrays.sort(arr);

        boolean flag = false;
        int mode_max = 0;
        int mode = 10000;

        for (int i = 0; i < N; i++) {
            int jump = 0;
            int cnt = 1;
            int i_value = arr[i];

            for (int j = i + 1; j < N; j++) {
                if (i_value != arr[j]) {
                    break;
                }
                cnt++;
                jump++;
            }

            if (cnt > mode_max) {
                mode_max = cnt;
                mode = i_value;
                flag = true;
            } else if (cnt == mode_max && flag) {
                mode = i_value;
                flag = false;
            }

            i += jump;
        }

        System.out.println((int)Math.round((double) sum / N));
        System.out.println(arr[N/2]);
        System.out.println(mode);
        System.out.println(arr[N - 1] - arr[0]);



    }
}
