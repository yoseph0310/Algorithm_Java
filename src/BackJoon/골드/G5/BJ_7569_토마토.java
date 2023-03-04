package BackJoon.골드.G5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7569_토마토 {

    static int M, N, H, ans;
    static int[][][] board;
    static Queue<Point> q;

    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dh = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new int[H][N][M];
        q = new LinkedList<>();

        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    board[h][i][j] = Integer.parseInt(st.nextToken());

                    if (board[h][i][j] == 1) q.add(new Point(h, i, j));
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        while(!q.isEmpty()) {
            Point cur = q.poll();

            int ch = cur.h;
            int cx = cur.x;
            int cy = cur.y;

            for (int d = 0; d < 6; d++) {
                int nh = ch + dh[d];
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isBoundary(nh, nx, ny)) {
                    if (board[nh][nx][ny] == 0) {
                        q.add(new Point(nh, nx, ny));
                        board[nh][nx][ny] = board[ch][cx][cy] + 1;
                    }
                }
            }
        }

        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[h][i][j] == 0) return -1;
                    else ans = Math.max(ans, board[h][i][j]);
                }
            }
        }

        return ans - 1;
    }

    static boolean isBoundary(int h, int x, int y) {
        return 0 <= h && h < H && 0 <= x && x < N && 0 <= y && y < M;
    }

    static class Point {
        int h, x, y;

        public Point(int h, int x, int y) {
            this.h = h;
            this.x = x;
            this.y = y;
        }
    }
}
