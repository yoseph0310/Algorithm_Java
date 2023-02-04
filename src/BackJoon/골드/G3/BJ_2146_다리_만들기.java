package BackJoon.골드.G3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2146_다리_만들기 {

    static int N;
    static int landNum = 2;
    static int ans = Integer.MAX_VALUE;

    static int[][] board;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    makeLand(i, j);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] >= 2) {
                    visited = new boolean[N][N];
                    bfs(i, j);
                }
            }
        }

        System.out.println(ans);
    }

    static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        int currentLandNum = board[x][y];

        q.add(new Point(x, y, 0));
        visited[x][y] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();

            int cx = cur.x;
            int cy = cur.y;
            int c_cnt = cur.cnt;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isBoundary(nx, ny)) {
                    if (!visited[nx][ny] && board[nx][ny] != currentLandNum) {
                        visited[nx][ny] = true;
                        if (board[nx][ny] == 0) {
                            q.add(new Point(nx, ny,c_cnt+ 1));
                        } else {
                            ans = Math.min(ans, c_cnt);
                        }
                    }
                }
            }
        }
    }

    static void makeLand(int x, int y) {
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(x, y, 0));
        visited[x][y] = true;
        board[x][y] = landNum;

        while(!q.isEmpty()) {
            Point cur = q.poll();

            int cx = cur.x;
            int cy = cur.y;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isBoundary(nx, ny)) {
                    if (!visited[nx][ny] && board[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        q.add(new Point(nx, ny, 0));
                        board[nx][ny] = landNum;
                    }
                }
            }
        }
        landNum++;
    }

    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static class Point {
        int x, y, cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}