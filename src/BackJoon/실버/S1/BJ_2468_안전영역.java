package BackJoon.실버.S1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2468_안전영역 {

    static int[][] board;
    static boolean[][] rained;
    static int N, min, max, ans;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        min = Integer.MAX_VALUE;
        max = 0;
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, board[i][j]);
                max = Math.max(max, board[i][j]);
            }
        }

        ans = 0;
        for (int i = 0; i < max; i++) {
            rained = new boolean[N][N];
            int cnt = 0;
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (board[x][y] > i && !rained[x][y]){
                        cnt += dfs(x, y, i);
                    }
                }
            }
            ans = Math.max(ans, cnt);
        }
        System.out.println(ans);
    }

    static int dfs(int i, int j, int height){

        rained[i][j] = true;
        for (int d = 0; d < 4; d++) {
            int nx = i + dx[d];
            int ny = j + dy[d];

            if (0<=nx && nx<N && 0<=ny && ny<N && !rained[nx][ny] && board[nx][ny] > height){
                dfs(nx, ny, height);
            }
        }
        return 1;
    }
}