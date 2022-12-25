package BackJoon.골드.G3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_2638_치즈 {

    static int N, M, ans;
    static int[][] board;
    static ArrayList<Point> cheeseList;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        cheeseList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    cheeseList.add(new Point(i, j));
                }
            }
        }

        progress();

    }

    static void progress() {
        while (cheeseList.size() != 0) {
            ans++;
            bfs();
            meltingCheese();
        }
        System.out.println(ans);
    }

    static void meltingCheese() {
        for (Iterator<Point> it = cheeseList.iterator(); it.hasNext(); ) {
            Point p = it.next();

            int x = p.x;
            int y = p.y;
            int cnt = 0;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (board[nx][ny] == 2) {
                    cnt++;
                }
            }

            if (cnt >= 2) {
                board[x][y] = 0;
                it.remove();
            }
        }
    }

    static void bfs() {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        q.add(new Point(0, 0));
        visited[0][0] = true;
        board[0][0] = 2;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            int cx = cur.x;
            int cy = cur.y;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isBoundary(nx, ny)) {
                    if (!visited[nx][ny] && board[nx][ny] != 1) {
                        board[nx][ny] = 2;
                        q.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }

    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
