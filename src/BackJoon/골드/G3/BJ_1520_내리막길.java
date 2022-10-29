package BackJoon.골드.G3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1520_내리막길 {

    static int N, M, ans;
    static int[][] board;
    static int[][] DP;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DP = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                DP[i][j] = -1;
            }
        }

        ans = dfs(1, 1);
        System.out.println(ans);
    }

    static int dfs(int x, int y){
        if (x == N && y == M){
            return 1;
        }
        if (DP[x][y] != -1){
            return DP[x][y];
        }

        DP[x][y] = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (1 <= nx && nx < N && 1 <= ny && ny < M){
                if (board[nx][ny] < board[x][y]){
                    DP[x][y] += dfs(nx, ny);
                }
            }
        }
        return DP[x][y];
    }
    static class Point {
        int x, y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
