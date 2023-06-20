package BackJoon.골드.G5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17070_파이프_옮기기1_DP {

    static int N;
    static int[][] board;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        dp = new int[N][N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0, 1 에 가로 방향으로 놓여져 있음
        dp[0][1][0] = 1;
        System.out.println(dp());
    }
    // 가로 0, 대각선 1, 세로 2
    static int dp() {
        for (int i = 0; i < N; i++) {
            for (int j = 2; j < N; j++) {
                // 벽인 경우
                if (board[i][j] == 1) continue;
                dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];    // 가로는 이전 파이프가 가로이거나 대각선인 경우만 가능

                // 맨 윗줄에는 세로로 놓아질 수 없음
                if (i == 0) continue;
                dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2];    // 세로는 이전 파이프가 세로이거나 대각선인 경우만 가능

                // 대각선이면 위나 왼쪽이 벽이면 안됨.
                if (board[i - 1][j] == 1 || board[i][j - 1] == 1) continue;
                dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
            }
        }

        return dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
    }
}
