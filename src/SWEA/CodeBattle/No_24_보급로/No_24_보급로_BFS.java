package SWEA.CodeBattle.No_24_보급로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 보급로 BFS 풀이
 */
public class No_24_보급로_BFS {

    static int N, min;
    static int[][] board, ans;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            ans = new int[N][N];
            visited = new boolean[N][N];

            min = Integer.MAX_VALUE;

            // board 배열 입력
            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < N; j++) {
                    board[i][j] = input.charAt(j) - '0';
                }
            }

            // ans 배열 초기화
            for (int i = 0; i < N; i++) {
                Arrays.fill(ans[i], Integer.MAX_VALUE);
            }
            ans[0][0] = 0;

            BFS(0, 0);
            System.out.println("#" + t + " " + ans[N-1][N-1]);
        }

    }

    static void BFS(int x, int y) {
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (cur.x == N-1 && cur.y == N-1){
                min = Math.min(min, ans[N-1][N-1]);
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (isNotBoundary(nx, ny)) continue;

                if (!visited[nx][ny] || ans[nx][ny] > ans[cur.x][cur.y] + board[nx][ny]) {
                    ans[nx][ny] = ans[cur.x][cur.y] + board[nx][ny];
                    q.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

    }

    static boolean isNotBoundary(int x, int y) {
        return !(0 <= x && x < N && 0 <= y && y < N);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
