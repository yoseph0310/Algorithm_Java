package BackJoon.실버.S1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_2156_포도주_시식_Top_Down {

    static Integer[] dp;
    static int[] wine;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        wine = new int[N + 1];
        dp = new Integer[N + 1];

        for (int i = 1; i <= N; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 0;
        dp[1] = wine[1];

        if (N > 1) {
            dp[2] = wine[1] + wine[2];
        }

        System.out.println(recur(N));
    }

    static int recur(int N) {
        if (dp[N] == null) {
            dp[N] = Math.max(Math.max(recur(N - 2), recur(N - 3) + wine[N - 1]) + wine[N], recur(N - 1));
        }
        return dp[N];
    }
}
