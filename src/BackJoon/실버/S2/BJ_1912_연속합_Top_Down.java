package BackJoon.실버.S2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1912_연속합_Top_Down {

    static int[] arr;
    static Integer[] dp;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        dp = new Integer[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = arr[1];
        ans = dp[1];

        solve(N);

        System.out.println(ans);
    }

    static int solve(int N) {

        if (dp[N] == null) {
            dp[N] = Math.max(solve(N-1) + arr[N], arr[N]);
            ans = Math.max(dp[N], ans);
        }

        return dp[N];
    }
}
