package BackJoon.실버.S1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1926_그림 {

    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static ArrayList<Point> picture_list;
    static int picture_width = 0;
    static int picture_cnt = 0;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];
        picture_list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == 1) picture_list.add(new Point(i, j));
            }
        }

        for (Point p: picture_list) {
            if (!visited[p.x][p.y]) {
                bfs(p);
                picture_cnt++;
            }
        }

        System.out.println(picture_cnt);
        System.out.println(picture_width);
    }

    static void bfs(Point p) {
        Queue<Point> q = new LinkedList<>();
        int width = 1;

        q.add(p);
        visited[p.x][p.y] = true;

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
                        width++;
                    }
                }
            }
        }

        if (picture_width < width) picture_width = width;

    }

    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    static class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
