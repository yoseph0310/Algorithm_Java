package BackJoon.골드.G5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14503_로봇청소기 {

    static int N, M, ans;
    static int[][] board;
    static boolean[][] visited;

    // 좌 상 우 하
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

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

        bfs(r, c, d);
        System.out.println(ans);
    }
    /*

    bfs 이던 dfs 이던 구현해놓을 것
    현재 방향 기준 좌측이라는 게 키포인트
     */
    static void bfs(int r, int c, int dir){
        Queue<Point> q = new LinkedList<>();
        visited[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int nr = r + dx[d];
            int nc = c + dy[d];

            if (isBoundary(nr, nc)){

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

    static class Point{
        int x, y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
