package Programmers.LV2;
import java.util.*;

public class 리코챗_로봇 {


    char[][] map;
    boolean[][] visited;

    int N, M, answer;

    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};

    Point robot, goal;

    public int solution(String[] board) {

        N = board.length;
        M = board[0].length();

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = board[i].charAt(j);

                if (map[i][j] == 'R') robot = new Point(i, j, 0);
                if (map[i][j] == 'G') goal = new Point(i, j, 0);
            }
        }

        answer = bfs();

        return answer;
    }

    int bfs() {
        Queue<Point> q = new LinkedList<>();
        visited = new boolean[N][M];

        q.add(robot);
        visited[robot.x][robot.y] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            int cx = cur.x;
            int cy = cur.y;
            int cnt = cur.cnt;

            if (cx == goal.x && cy == goal.y) {
                return cnt;
            }

            for (int d = 0; d < 4; d++) {
                Point nextPoint = roll(cur, d);

                int nx = nextPoint.x;
                int ny = nextPoint.y;

                if (!visited[nx][ny]) {
                    q.add(nextPoint);
                    visited[nx][ny] = true;
                }
            }
        }

        return -1;
    }

    Point roll(Point cur, int d) {
        int x = cur.x;
        int y = cur.y;

        while (true) {
            x += dx[d];
            y += dy[d];

            if (isNotBoundary(x, y) || map[x][y] == 'D') {
                x -= dx[d];
                y -= dy[d];

                break;
            }
        }


        return new Point(x, y, cur.cnt + 1);
    }

    boolean isNotBoundary(int x, int y) {
        return !(0 <= x && x < N && 0 <= y && y < M);
    }

    class Point {
        int x, y, cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
