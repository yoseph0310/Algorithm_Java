package BackJoon.골드.G5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14503_로봇청소기 {

    static int N, M, ans;
    static int[][] board;

    // 북 동 남 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()); // 로봇 r좌표
        int c = Integer.parseInt(st.nextToken()); // 로봇 c좌표
        int d = Integer.parseInt(st.nextToken()); // 로봇 방향

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(r, c, d);
        System.out.println(ans);
    }

    static void dfs(int r, int c, int dir){
        if (board[r][c] == 0) {
            board[r][c] = 2;
            ans++;
        }

        boolean flag = false;
        int origin = dir;
        for (int d = 0; d < 4; d++) {
            int nd = (dir + 3) % 4;
            int nr = r + dx[nd];
            int nc = c + dy[nd];

            if (isBoundary(nr, nc)) {
                if (board[nr][nc] == 0){
                    dfs(nr, nc, nd);
                    flag = true;
                    break;
                }
            }
            dir = (dir + 3) % 4;
        }

        if (!flag) {
            int nd = (origin + 2) % 4;
            int nr = r + dx[nd];
            int nc = c + dy[nd];

            if (isBoundary(nr, nc)) {
                if (board[nr][nc] != 1) {
                    dfs(nr, nc, origin);
                }
            }
        }

    }

    static boolean isBoundary(int r, int c){
        if (0 <= r && r < N && 0 <= c && c < M){
            return true;
        } else {
            return false;
        }
    }
}
