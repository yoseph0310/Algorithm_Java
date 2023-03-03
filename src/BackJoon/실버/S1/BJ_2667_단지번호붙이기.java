package BackJoon.실버.S1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_2667_단지번호붙이기 {

    static int N, apartNum;
    static int[] apart = new int[25 * 25];
    static int[][] board;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = input.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1 && !visited[i][j]) {
                    apartNum++;
                    bfs(i, j);
                }
            }
        }

        Arrays.sort(apart);

        System.out.println(apartNum);
        for (int n: apart) {
            if (n != 0) System.out.println(n);
        }
    }

    static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(x, y));
        visited[x][y] = true;
        apart[apartNum]++;

        while(!q.isEmpty()) {
            Point cur = q.poll();

            int cx = cur.x;
            int cy = cur.y;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isBoundary(nx, ny)) {
                    if (!visited[nx][ny] && board[nx][ny] == 1) {
                        q.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                        apart[apartNum]++;
                    }
                }
            }
        }

    }

    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
