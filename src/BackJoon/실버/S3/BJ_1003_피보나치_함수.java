package BackJoon.실버.S3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1003_피보나치_함수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            int zero = 1;
            int one = 0;
            int zero_plus_one = 1;

            for (int j = 0; j < N; j++) {
                zero = one;
                one = zero_plus_one;
                zero_plus_one = zero + one;
            }

            System.out.println(zero+ " " + one);
        }
    }
}
