package BackJoon.실버.S3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_2193_이친수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Long[] dp = new Long[91];

        dp[0] = 0L;
        dp[1] = 1L;
        dp[2] = 1L;

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        System.out.println(dp[N]);
    }
}