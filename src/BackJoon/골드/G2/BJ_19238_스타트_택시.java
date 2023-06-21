package BackJoon.골드.G2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_19238_스타트_택시 {

    static int N, M, fuel;
    static int cnt = 0;

    static int[][] map;
    static boolean[][] visited;
    static Point taxi;
    static Point[] destination;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        destination = new Point[M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = map[i][j] == 1 ? -1 : map[i][j];
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = i;

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            destination[i] = new Point(x, y);
        }

        process();

    }

    static void process() {
        for (int i = 0; i < M; i++) {
            int passenger = findPassenger(taxi);
            fuel -= cnt;

            if (passenger == -1 || fuel <= 0) {
                System.out.println(-1);
                return;
            }

            int use = goDest(taxi, passenger);
            if (fuel < use || use == -1) {
                System.out.println(-1);
                return;
            }

            fuel += use;

            map[taxi.x][taxi.y] = 0;
            taxi = destination[passenger];
        }
        System.out.println(fuel);
    }

    static int findPassenger(Point p) {
        Queue<Point> q = new LinkedList<>();
        PriorityQueue<Point> pq = new PriorityQueue<>();
        for (int i = 1; i <=  N; i++) {
            Arrays.fill(visited[i], false);
        }

        cnt = 0;

        q.add(p);
        // 택시가 있는 곳이 승객이 있는 곳이면 거리가 0인 것이므로 그 승객을 바로 리턴
        if (map[p.x][p.y] > 0) {
            return map[p.x][p.y];
        }

        int size = 0;
        while (!q.isEmpty()) {
            cnt++;
            size = q.size();

            for (int step = 0; step < size; step++) {
                Point cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];

                    if (isBoundary(nx, ny) && !visited[nx][ny] && map[nx][ny] >= 0) {
                        if (map[nx][ny] > 0) {
                            pq.offer(new Point(nx, ny));
                        }
                        q.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }

            if (!pq.isEmpty()) {
                taxi = pq.poll();
                return map[taxi.x][taxi.y];
            }
        }

        return -1;
    }

    static int goDest(Point taxi, int passNo) {
        Queue<Point> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            Arrays.fill(visited[i], false);
        }

        q.add(taxi);
        visited[taxi.x][taxi.y] = true;

        int size = 0;
        cnt = 0;
        while (!q.isEmpty()) {
            size = q.size();

            for (int step = 0; step < size; step++) {
                Point cur = q.poll();

                if (cur.x == destination[passNo].x && cur.y == destination[passNo].y) {
                    return cnt;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];

                    if (isBoundary(nx, ny) && !visited[nx][ny] && map[nx][ny] >= 0) {
                        q.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
            cnt++;
        }

        return -1;
    }

    static boolean isBoundary(int x, int y) {
        return 1 <= x && x <= N && 1 <= y && y <= N;
    }

    static class Point implements Comparable<Point> {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x == o.x) return this.y - o.y;
            return this.x - o.x;
        }
    }
}
