package BackJoon.실버.S1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2468_안전영역_BFS {

    static int N, maxHeight, ans;
    static int[][] board;
    static boolean[][] rained;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                maxHeight = Math.max(maxHeight, board[i][j]);
            }
        }

        int cnt;
        for (int height = 0; height < maxHeight; height++) {
            rained = new boolean[N][N];
            cnt = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!rained[i][j] && board[i][j] > height) {
                        cnt += bfs(i, j, height);
                    }
                }
            }
            ans = Math.max(ans, cnt);
        }
        System.out.println(ans);
    }

    static int bfs(int x, int y, int height) {
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(x, y));
        rained[x][y] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();

            int cx = cur.x;
            int cy = cur.y;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if (!rained[nx][ny] && board[nx][ny] > height) {
                        q.add(new Point(nx, ny));
                        rained[nx][ny] = true;
                    }
                }
            }
        }

        return 1;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
