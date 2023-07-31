package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_21_Knapsack {

    static int N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());       // N : 물건 개수
            K = Integer.parseInt(st.nextToken());       // K : 가방 부피

            int[] w = new int[N + 1];                   // 각 물건의 부피
            int[] cost = new int[N + 1];                // 각 물건의 가치

            int[][] dp = new int[N + 1][K + 1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());

                w[i] = Integer.parseInt(st.nextToken());
                cost[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= K; j++) {
                    // 각 물건의 부피가 j 보다 크면
                    if (w[i] > j) {
                        dp[i][j] = dp[i - 1][j];
                    }
                    // 각 물건의 부피가 j 보다 작으면
                    else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - w[i]] + cost[i]);
                    }
                }
            }

            System.out.println("#" + t + " " + dp[N][K]);

        }

    }
}
