package CodeTree.Samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class A_2023_상반기_1번_포탑부수기_우선순위큐사용_정답 {

    static int answer;
    static int N, M, K;

    static PriorityQueue<Turret> pq;
    static Turret[][] board;
    static boolean[][] isAttacked;

    static Turret attacker;
    static Turret target;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new Turret[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int power = Integer.parseInt(st.nextToken());

                if (power > 0) {
                    board[i][j] = new Turret(i, j, power, 0);
                }
            }
        }


        progress();

        System.out.println(answer);
    }

    static void progress() {

        for (int k = 1; k <= K; k++) {

            // 가장 약한 포탑, 강한 포탑을 뽑기 위해 turretList 정렬
            init();
            // 만일 turretList.size() 가 1 이라면 종료하고 그 타워의 파워를 정답으로 출력
            if (pq.size() == 1) {
                answer = pq.poll().power;
                break;
            }
            // 가장 약한 포탑 선정 -> 얘가 공격을 하기 때문에 현재 K 값을 turn 으로 넣어준다.
            // 가장 강한 포탑 선정
            attacker = pq.poll();
            target = selectStrongestTurret();

            attacker.turn = k;
            attacker.power += (N + M);

            // 공격 단계  ->  공격 경로에 있거나 범위에 있는 포탑들은 isAttacked 에 true 처리
            // 레이저로 공격 가능한지 판단
            // 레이저 공격
            // 포탑 공격
            if (!possibleLazer()) {
                attackBomb();
            }

            // 공격을 받아 power 가 0 이하인 포탑들은 0 처리
            // 공격과 무관한 포탑들은 power 1 증가
            repair();
        }

        // 가장 강한 포탑 power 찾아 정답으로 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != null) {
                    answer = Math.max(answer, board[i][j].power);
                }
            }
        }
    }

    static Turret selectStrongestTurret() {
        Turret t = null;
        while(!pq.isEmpty()) {
            t = pq.poll();
        }

        return t;
    }

    static void repair() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != null && !isAttacked[i][j]) board[i][j].power++;
            }
        }
    }

    static void attackBomb() {
        int[] dx = {0, -1, 0, 1, 0, -1, -1, 1, 1};
        int[] dy = {0, 0, 1, 0, -1, -1, 1, -1, 1};

        for (int d = 0; d < 9; d++) {
            int nx = target.x + dx[d];
            int ny = target.y + dy[d];

            // 끝 지점에 도달하면 반대쪽 처음으로 돌아간다.
            if (nx < 0) nx = N - 1;
            if (nx >= N) nx = 0;
            if (ny < 0) ny = M - 1;
            if (ny >= M) ny = 0;

            if (board[nx][ny] == null) continue;
            if (nx == attacker.x && ny == attacker.y) continue;

            isAttacked[nx][ny] = true;

            int power = attacker.power / 2;
            if (nx == target.x && ny == target.y) {
                power = attacker.power;
            }

            attack(nx, ny, power);
        }

    }

    // 레이저로 공격 가능한지 판단
    static boolean possibleLazer() {
        // 우 하 좌 상
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        Queue<Path> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        Path[][] prev = new Path[N][M];

        q.add(new Path(attacker.x, attacker.y));
        visited[attacker.x][attacker.y] = true;
        isAttacked[attacker.x][attacker.y] = true;

        while(!q.isEmpty()) {
            Path cur = q.poll();

            int cx = cur.x;
            int cy = cur.y;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                // 끝 지점에 도달하면 반대쪽 처음으로 돌아간다.
                if (nx < 0) nx = N - 1;
                if (nx >= N) nx = 0;
                if (ny < 0) ny = M - 1;
                if (ny >= M) ny = 0;

                if (visited[nx][ny] || board[nx][ny] == null) continue;

                Path nextPath = new Path(nx, ny);
                q.add(nextPath);
                visited[nx][ny] = true;
                prev[nx][ny] = cur;
            }
        }

        if (!visited[target.x][target.y]) return false;

        Path back = new Path(target.x, target.y);

        while (back.x != attacker.x || back.y != attacker.y) {
            int power = attacker.power / 2;

            if (back.x == target.x && back.y == target.y) {
                power = attacker.power;
            }

            attack(back.x, back.y, power);
            back = prev[back.x][back.y];
        }

        return true;
    }

    static void attack(int x, int y, int power) {
        isAttacked[x][y] = true;
        board[x][y].power = Math.max(0, board[x][y].power - power);

        if (board[x][y].power == 0) board[x][y] = null;
    }

    // 가장 약한 포탑, 강한 포탑을 뽑기 위해 turretList 정렬
    static void init() {

        pq = new PriorityQueue<>();
        isAttacked = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != null) pq.add(board[i][j]);
            }
        }

    }

    static class Path {
        int x, y;

        public Path(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Turret implements Comparable<Turret> {
        int x, y, power, turn;

        public Turret(int x, int y, int power, int turn) {
            this.x = x;
            this.y = y;
            this.power = power;
            this.turn = turn;
        }

        @Override
        public int compareTo(Turret o) {
            if (this.power != o.power) return this.power - o.power;
            if (this.turn != o.turn) return o.turn - this.turn;
            if ((this.x + this.y) != (o.x + o.y)) return (o.x + o.y) - (this.x + this.y);
            return o.y - this.y;
        }
    }
}
