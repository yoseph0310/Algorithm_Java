package Programmers.LV3;

public class 보행자_천국 {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] map) {
        // dp[][][0] : 아래쪽, dp[][][1] : 오른쪽
        int[][][] dp = new int[m+1][n+1][2];

        dp[1][1][0] = dp[1][1][1] = 1;

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                // 이전 좌표가 통행 가능인 경우
                if (map[i-1][j-1] == 0) {
                    dp[i][j][0] += (dp[i-1][j][0] + dp[i][j-1][1]) % MOD;
                    dp[i][j][1] += (dp[i-1][j][0] + dp[i][j-1][1]) % MOD;
                }
                // 이전 좌표가 통행 금지인 경우
                else if (map[i-1][j-1] == 1) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = 0;
                }
                // 이전 좌표가 좌/우회전 금지인 경우
                else {
                    dp[i][j][0] = dp[i-1][j][0];
                    dp[i][j][1] = dp[i][j-1][1];
                }
            }
        }

        return dp[m][n][0];
    }
}
