package BackJoon.플래티넘.P5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3197_백조의_호수 {

    static int R, C;
    static char[][] board;
    static boolean[][] visited;

    static Queue<Point> waterQ;
    static Queue<Point> q;
    static Point[] swan;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        visited = new boolean[R][C];

        waterQ = new LinkedList<>();
        q = new LinkedList<>();
        swan = new Point[2];

        int swanIdx = 0;
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = input.charAt(j);
                board[i][j] = c;

                if (c == 'L') swan[swanIdx++] = new Point(i, j);
                if (c != 'X') waterQ.add(new Point(i, j));
            }
        }

        System.out.println(solve());
    }

    static int solve() {
        int day = 0;
        Point start = swan[0];
        Point end = swan[1];

        q.add(start);
        visited[start.x][start.y] = true;

        boolean isFindSwan = false;

        while (true) {
            Queue<Point> nextQ = new LinkedList<>();
            while(!q.isEmpty()) {
                Point cur = q.poll();

                int cx = cur.x;
                int cy = cur.y;

                if (cx == end.x && cy == end.y) {
                    isFindSwan = true;
                    break;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = cx + dx[d];
                    int ny = cy + dy[d];

                    if (isBoundary(nx, ny)) {
                        if (!visited[nx][ny]) {
                            visited[nx][ny] = true;

                            if (board[nx][ny] == 'X') {
                                nextQ.add(new Point(nx, ny));
                                continue;
                            }

                            q.add(new Point(nx, ny));
                        }
                    }
                }
            }

            if (isFindSwan) break;
            q = nextQ;

            int size = waterQ.size();
            for (int i = 0; i < size; i++) {
                Point cur = waterQ.poll();

                int cx = cur.x;
                int cy = cur.y;

                for (int d = 0; d < 4; d++) {
                    int nx = cx + dx[d];
                    int ny = cy + dy[d];

                    if (isBoundary(nx, ny)) {
                        if (board[nx][ny] == 'X') {
                            board[nx][ny] = '.';
                            waterQ.add(new Point(nx, ny));
                        }
                    }
                }
            }
            day++;
        }

        return day;
    }

    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
