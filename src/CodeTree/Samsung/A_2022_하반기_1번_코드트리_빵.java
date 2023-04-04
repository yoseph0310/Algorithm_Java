package CodeTree.Samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A_2022_하반기_1번_코드트리_빵 {

    static int N, M;
    static int[][] board;
    static int[][] dist;
    static Person[] people;

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N + 1][N + 1];
        dist = new int[N + 1][N + 1];

        people = new Person[31];
        for (int i = 0; i < people.length; i++) {
            people[i] = new Person(0, 0, 0, 0);
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            people[i].goalX = x;
            people[i].goalY = y;
        }

        solve();
    }

    static void solve() {
        int time = 0;

        while (!isFinished()) {     // 모든 사람이 도착했는지?
            time++;

            // step 1. 모든 사람이 한 칸 움직임
            for (int i = 1; i < time && i <= M ; i++) {
                move(people[i]);
            }

            // step 2. 도착한 사람 처리
            for (int i = 1; i < time && i <= M ; i++) {
                checkArrived(people[i]);
            }

            // step 3. 새로 출발할 사람 관리
            if (time <= M) {
                initiate(people[time]);
            }
        }

        System.out.println(time);
    }

    static void move(Person p) {   // 사람 P를 목표 위치로 한칸 이동
        // 이미 도착한 사람 무시하기
        if (isArrived(p)) {
            return;
        }

        // 편의점으로부터 모든 격자의 최단 거리 계산하기
        bfs(p.goalX, p.goalY);

        // 자신과 인접한 네 곳을 보면서, 어디로 갈지 정하기
        int minDist = 10000, minDir = -1;
        for (int d = 0; d < 4; d++) {
            int nx = p.x + dx[d];
            int ny = p.y + dy[d];

            if (!isBoundary(nx, ny)) continue;
            if (dist[nx][ny] < minDist) {
                minDist = dist[nx][ny];
                minDir = d;
            }
        }

        // 결정한 방향으로 한 칸 이동
        p.x += dx[minDir];
        p.y += dy[minDir];
    }

    static void checkArrived(Person p) {
        if (isArrived(p)) {
            board[p.x][p.y] = -1;
        }
    }

    static void initiate(Person p) {    // 사람 p가 어디서 출발할 지 관리
        // 먼저 목표 위치에서 각 베이스 캠프 까지의 최단 거리
        bfs(p.goalX, p.goalY);

        int minDist = 10000;
        int minX = 0;
        int minY = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (board[i][j] != 1) continue;
                if (dist[i][j] < minDist) {
                    minDist = dist[i][j];
                    minX = i;
                    minY = j;
                }
            }
        }

        p.x = minX;
        p.y = minY;

        board[p.x][p.y] = -1;
    }

    static void bfs(int sx, int sy) {   // sx 행, sy 열을 시작점으로 모든 격자까지의 최단거리 계산
        // 거리 배열 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dist[i][j] = 10000;
            }
        }

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sx, sy));

        dist[sx][sy] = 0;

        while(!q.isEmpty()) {
            Point cur = q.poll();

            int cx = cur.x;
            int cy = cur.y;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (!isBoundary(nx, ny)) continue;
                if (dist[nx][ny] != 10000) continue;
                if (board[nx][ny] == -1) continue;

                dist[nx][ny] = dist[cx][cy] + 1;
                q.add(new Point(nx, ny));
            }

        }
    }

    static boolean isFinished() {   // 모든 사람이 도착했는지?
        for (int i = 1; i <= M; i++) {
            if (!isArrived(people[i])) {
                return false;
            }
        }
        return true;
    }

    static boolean isArrived(Person p) {
        return p.x == p.goalX && p.y == p.goalY;
    }

    static boolean isBoundary(int x, int y) {
        return 1 <= x && x <= N && 1 <= y && y <= N;
    }

    static class Person {
        int x, y, goalX, goalY;

        public Person(int x, int y, int goalX, int goalY) {
            this.x = x;
            this.y = y;
            this.goalX = goalX;
            this.goalY = goalY;
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
