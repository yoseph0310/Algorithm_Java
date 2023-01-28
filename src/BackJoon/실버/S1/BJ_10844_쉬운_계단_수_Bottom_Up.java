package BackJoon.실버.S1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_10844_쉬운_계단_수_Bottom_Up {

    final static long MOD = 1000000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N+1][10];
        long ans = 0;

        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[i][0] = dp[i-1][1] % MOD;
                } else if (j == 9) {
                    dp[i][9] = dp[i-1][8] % MOD;
                } else {
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % MOD;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            ans += dp[N][i];
        }

        System.out.println(ans % MOD);
    }
}
