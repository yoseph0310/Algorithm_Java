package BackJoon.실버.S2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1912_연속합_Bottom_Up {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        int[] dp = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = arr[1];

        int ans = dp[1];

        for (int i = 2; i <= N; i++) {
            dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);
    }
}
