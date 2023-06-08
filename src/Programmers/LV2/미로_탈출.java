package Programmers.LV2;
import java.util.*;

public class 미로_탈출 {

    char[][] board;
    boolean[][] visited;

    int N, M;

    Point start, lever, end;

    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};

    public int solution(String[] maps) {
        int answer = 0;

        N = maps.length;
        M = maps[0].length();

        board = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = maps[i].toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'S') {
                    start = new Point(i, j, 0);
                }
                if (board[i][j] == 'L') {
                    lever = new Point(i, j, 0);
                }
                if (board[i][j] == 'E') {
                    end = new Point(i, j, 0);
                }
            }
        }

        // 레버로 가는 최단 경로부터
        answer = bfs(start.x, start.y, lever.x, lever.y);

        if (answer > -1) {
            visited = new boolean[N][M];

            int temp = bfs(lever.x, lever.y, end.x, end.y);
            if (temp == -1) answer = -1;
            else answer += temp;
        }

        return answer;
    }

    int bfs(int sx, int sy, int ex, int ey) {
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(sx, sy, 0));
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            int cx = cur.x;
            int cy = cur.y;
            int clevel = cur.level;

            if (cx == ex && cy == ey) return clevel;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isNotBoundary(nx, ny) || board[nx][ny] == 'X' || visited[nx][ny]) continue;

                q.add(new Point(nx, ny, clevel+1));
                visited[nx][ny] = true;
            }
        }

        return -1;
    }

    boolean isNotBoundary(int x, int y) {
        return !(0 <= x && x < N && 0 <= y && y < M);
    }


    class Point {
        int x, y, level;

        public Point(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }
    }
}
