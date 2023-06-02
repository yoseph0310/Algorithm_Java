package Programmers.LV2;
import java.util.*;

public class 무인도_여행 {
    char[][] board;
    boolean[][] visited;
    ArrayList<Integer> answer;

    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};

    public ArrayList<Integer> solution(String[] maps) {
        answer = new ArrayList<>();

        board = new char[maps.length][maps[0].length()];
        visited = new boolean[maps.length][maps[0].length()];

        for (int i = 0; i < maps.length; i++) {
            board[i] = maps[i].toCharArray();
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!visited[i][j] && board[i][j] != 'X') {
                    bfs(i, j);
                }
            }
        }

        if (answer.size() == 0) answer.add(-1);
        else Collections.sort(answer);

        return answer;
    }

    void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        int sum = 0;

        q.add(new Point(x,y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            int cx = cur.x;
            int cy = cur.y;

            sum += board[cx][cy] - '0';

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isNotBoundary(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (board[nx][ny] == 'X') continue;

                q.add(new Point(nx, ny));
                visited[nx][ny] = true;
            }
        }

        answer.add(sum);
    }

    boolean isNotBoundary(int x, int y) {
        return !(0 <= x && x < board.length && 0 <= y && y < board[0].length);
    }

    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
