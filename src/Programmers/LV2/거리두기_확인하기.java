package Programmers.LV2;
import java.util.*;

public class 거리두기_확인하기 {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int i = 0; i < places.length; i++) {
            String[] place = places[i];
            boolean isPossible = true;

            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (place[j].charAt(k) == 'P') {
                        if (!bfs(j, k, place)) isPossible = false;
                    }
                }
            }

            answer[i] = isPossible ? 1 : 0;
        }

        return answer;
    }

    boolean bfs(int x, int y, String[] p) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            Point cur = q.poll();

            int cx = cur.x;
            int cy = cur.y;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isNotBoundary(nx, ny) || (nx == x && ny == y)) continue;

                int dist = Math.abs(nx - x) + Math.abs(ny - y);

                if (p[nx].charAt(ny) == 'P' && dist <= 2) return false;
                else if (p[nx].charAt(ny) == 'O' && dist < 2) q.add(new Point(nx, ny));
            }
        }

        return true;
    }

    boolean isNotBoundary(int x, int y) {
        return !(0 <= x && x < 5 && 0 <= y && y < 5);
    }

    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
