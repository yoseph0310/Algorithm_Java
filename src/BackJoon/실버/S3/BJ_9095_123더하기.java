package BackJoon.실버.S3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_9095_123더하기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int[] arr = new int[11];

        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 4;

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            for (int j = 4; j <= N; j++) {
                arr[j] = arr[j-1] + arr[j-2] + arr[j-3];
            }
            System.out.println(arr[N]);
        }
    }
}
