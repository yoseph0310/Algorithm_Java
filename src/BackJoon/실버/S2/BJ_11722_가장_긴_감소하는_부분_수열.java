package BackJoon.실버.S2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11722_가장_긴_감소하는_부분_수열 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[i] + 1;
                }
            }
        }

        int max = -1;
        for (int i = 0; i < N; i++) {
            if (max < dp[i]) max = dp[i];
        }

        System.out.println(max);
    }
}
