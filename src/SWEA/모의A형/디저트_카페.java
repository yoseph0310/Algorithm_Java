package SWEA.모의A형;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 디저트_카페 {

    static int N;
    static int[][] board;

    static int[] dx = {1, 1, -1, -1};
    static int[] dy = {1, -1, -1, 1};

    static boolean[][] visited;
    static boolean[] dessert;

    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            ans = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N - 2; i++) {
                for (int j = 1; j < N - 1; j++) {
                    visited = new boolean[N][N];
                    dessert = new boolean[101];
                    visited[i][j] = true;
                    dessert[board[i][j]] = true;
                    dfs(1, i, j, i, j, 0);
                }
            }

            if (ans == 0) ans = -1;

            System.out.println("#"+t+" "+ ans);

        }
    }

    static void dfs(int cnt, int x, int y, int startX, int startY, int prevDir) {

        for (int d = prevDir; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isBoundary(nx, ny)) {
                if (nx == startX && ny == startY && cnt > 2) {
                    ans = Math.max(ans, cnt);
                    return;
                }

                if (!visited[nx][ny] && !dessert[board[nx][ny]]) {
                    visited[nx][ny] = true;
                    dessert[board[nx][ny]] = true;
                    dfs(cnt + 1, nx, ny, startX, startY, d);
                    visited[nx][ny] = false;
                    dessert[board[nx][ny]] = false;
                }
            }

        }
    }

    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
