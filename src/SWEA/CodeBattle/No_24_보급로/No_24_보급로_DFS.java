package SWEA.CodeBattle.No_24_보급로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

/**
 *  N : 최대 100
 *
 *  이동하면서 길을 복구하면서 간다.
 *  board 에 적힌 숫자만큼 시간이 걸리며 이를 최소로 하는 경로의 복구 시간을 찾아야한다.
 *
 *  - 출발지는 (0,0) 도착지는 (N-1, N-1)
 *  - 상하좌우 이동
 *
 *
 */
public class No_24_보급로_DFS {

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
            visited = new boolean[N][N];
            ans = new int[N][N];
            min = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < N; j++) {
                    board[i][j] = input.charAt(j) - '0';
                }
            }

            for (int i = 0; i < N; i++) {
                Arrays.fill(ans[i], Integer.MAX_VALUE);
            }
            ans[0][0] = 0;

            DFS(0, 0);
            System.out.println("#" + t + " " + ans[N-1][N-1]);
        }
    }

    static void DFS(int x, int y) {
        Stack<Point> stack = new Stack<>();

        stack.push(new Point(x, y));
        visited[x][y] = true;

        while (!stack.isEmpty()) {
            Point cur = stack.pop();

            int cx = cur.x;
            int cy = cur.y;

            if (cx == N-1 && cy == N-1) {
                min = Math.min(min, ans[N - 1][N - 1]);
            }

            if (min <= ans[cx][cy]) continue;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isNotBoundary(nx, ny)) continue;

                if (!visited[nx][ny] || ans[nx][ny] > ans[cx][cy] + board[nx][ny]) {
                    visited[nx][ny] = true;
                    ans[nx][ny] = ans[cx][cy] + board[nx][ny];
                    stack.push(new Point(nx, ny));
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
