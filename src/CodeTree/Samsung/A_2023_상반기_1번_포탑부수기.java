package CodeTree.Samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class A_2023_상반기_1번_포탑부수기 {

    static int N, M, K, answer;
    static Turret[][] board;

    static PriorityQueue<Turret> pq;
    static List<Point> attackedList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new Turret[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = new Turret(i, j, 0, 0);
            }
        }

        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int power = Integer.parseInt(st.nextToken());
                if (power != 0) board[i][j] = new Turret(i, j, power, 0);
            }
        }
        System.out.println("최초 보드 상태");
        printBoard(board);
        System.out.println("\n");

        progress();

        System.out.println("정답 : " + answer);
    }

    static void progress() {
        for (int i = 0; i < K; i++) {
            // pq에 값 넣기
            pq.clear();
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (board[j][k].power > 0) pq.add(board[j][k]);
                }
            }

            // 만일 K번 하기 전에 0이 아닌 포탑이 하나만 있다면 종료하고 그 포탑의 power 를 출력
            if (pq.size() == 1) {
                Turret t = pq.poll();

                answer = t.power;
                break;
            }
            // 가장 약한 포탑을 고르고 N + M 만큼 공격력을 증가시킨다.
            System.out.println(" 1. 공격자 선정 - 가장 약한 포탑 선정 후 N + M 만큼 공격력 증가");
            Turret wTurret = selectWeakestTurret();
            System.out.println("<가장 약한 포탑> 공격력 : " + wTurret.power + "     좌표 : " + wTurret.x + " " + wTurret.y + "\n");

            System.out.println(" 2. 공격대상자 선정 - 가장 강한 포탑 선정 후 공격진행");
            Turret sTurret = selectStrongestTurret();
            System.out.println("<가장 강한 포탑> 공격력 : " + sTurret.power + "     좌표 : " + sTurret.x + " " + sTurret.y + "\n");

            System.out.println(" 3. 무기 선택 과정 - 최단경로탐색 시작");

            System.out.println("--- 공격 전 포탑들의 상태 --- ");
            printBoard(board);
            // 공격과 관련된 포탑 리스트
            attackedList = new ArrayList<>();
            if (findMinDist(wTurret, sTurret)) {
                System.out.println(" *** 최단 경로가 존재합니다. 아래 경로를 따라 레이저 공격 *** ");
                attackLazer(wTurret, sTurret);
            } else {
                System.out.println(" *** 최단 경로가 존재하지 않습니다. 포탄 공격 시도 *** ");
                attackBomb(wTurret, sTurret);
            }
            System.out.println("--- 공격 후 포탑들의 상태 --- ");
            printBoard(board);

            repair();
            System.out.println("--- 재정비 후 포탑들의 상태 --- ");
            printBoard(board);
            System.out.println("\n");
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer = Math.max(answer, board[i][j].power);
            }
        }

    }

    static void attackBomb(Turret w, Turret s) {
        // 공격은 공격대상자 좌표 기준 8방으로 이루어진다.
        // 이때, 경계선을 넘게되면 반대편 좌표를 찍는다.
        // 만약 자기 자신이 범위에 포함된다면 자기 자신은 피해를 입지 않는다.
        int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
        int[] dy = {0, 1, 0, -1, 1, -1, 1, -1};

        int attackPower = w.power;

        attackedList.add(new Point(w.x, w.y));
        attackedList.add(new Point(s.x, s.y));
        s.power -= attackPower;
        if (s.power <= 0) s.power = 0;

        for (int d = 0; d < 8; d++) {
            int nx = s.x + dx[d];
            int ny = s.y + dy[d];

            if (ny >= M) ny = 0;
            if (0 > ny) ny = M - 1;
            if (nx >= N) nx = 0;
            if (0 > nx) nx = N - 1;

            if (board[nx][ny].power != 0 && !board[nx][ny].equals(w)) {
                attackedList.add(new Point(nx, ny));
                board[nx][ny].power -= (attackPower / 2);
                if (board[nx][ny].power <= 0) board[nx][ny].power = 0;
            }
        }
    }

    static void repair() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isNotAttacked(i, j) && board[i][j].power != 0) {

                    board[i][j].power++;

                }
            }
        }

    }

    static boolean isNotAttacked(int i, int j) {
        for (Point p : attackedList) {
            if (i == p.x && j == p.y) return false;
        }
        return true;
    }

    // 공격 대상자는 공격자의 공격력 만큼 감소 / 경로위의 포탑은 공격자 공격력 / 2 만큼 감소
    static void attackLazer(Turret w, Turret s) {
        int attackPower = w.power;

        for (Point p : attackedList) {
            int x = p.x;
            int y = p.y;

            // 만일 자기 자신이라면 아무일도 하지 않는다.
            if (x == w.x && y == w.y) continue;
            // 만일 공격 대상자라면 공격자의 공격력 만큼 줄인다.
            if (x == s.x && y == s.y) {
                s.power -= attackPower;
                if (s.power <= 0) s.power = 0;
            } else {
                board[x][y].power -= (attackPower / 2);
                if (board[x][y].power <= 0) board[x][y].power = 0;
            }
        }
    }

    static boolean findMinDist(Turret w, Turret s) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        // 상 좌 하 우
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        int sx = s.x;
        int sy = s.y;
        Point startPoint = new Point(sx, sy,  new ArrayList<>());

        q.add(startPoint);
        visited[sx][sy] = true;

        int idx = 0;
        boolean isFind = false;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Point cur = q.poll();

                int cx = cur.x;
                int cy = cur.y;
                List<Point> cList = cur.list;

                System.out.println(idx + "번째 방문하는 지점들 : " + cx + " " + cy);
                for (Point p: cList) {
                    System.out.print("("+p.x + ", " + p.y + ") -> ");
                }
                System.out.println("("+cx + ", " + cy + ")");

                if (cx == w.x && cy == w.y) {
                    System.out.println(" 도착 !! \n");
                    attackedList.addAll(cList);
                    attackedList.add(new Point(cx, cy));
                    isFind = true;
                    break;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = cx + dx[d];
                    int ny = cy + dy[d];

                    // 경계선 넘으면 반대쪽으로 이동
                    if (ny >= M) ny = 0;
                    if (0 > ny) ny = M - 1;
                    if (nx >= N) nx = 0;
                    if (0 > nx) nx = N - 1;

                    if (!visited[nx][ny] && board[nx][ny].power != 0) {
                        List<Point> nList = new ArrayList<>(cList);
                        nList.add(new Point(cx, cy));
                        Point nextPoint = new Point(nx, ny, nList);
                        q.add(nextPoint);
                        visited[nx][ny] = true;
                    }
                }
            }
            idx++;
            if (isFind) break;
        }

        return isFind;

    }

    static boolean isNotBoundary(int x, int y) {
        return !(0 <= x && x < N && 0 <= y && y < M);
    }

    static Turret selectStrongestTurret() {
        Turret turret = null;
        PriorityQueue<Turret> temp = new PriorityQueue<>();

        while (pq.size() > 1) {
            temp.add(pq.poll());
        }

        turret = pq.poll();

        while (!temp.isEmpty()) {
            pq.add(temp.poll());
        }

        return turret;
    }

    static Turret selectWeakestTurret() {
        Turret turret = null;
        if (!pq.isEmpty()) {
            turret = pq.poll();
            System.out.println("공격력 : " + turret.power + "     좌표 : " + turret.x + " " + turret.y);
            turret.aTurn++;
            turret.power += (N + M);
        }

        return turret;
    }

    static class Point {
        int x, y;
        List<Point> list;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, List<Point> list) {
            this.x = x;
            this.y = y;
            this.list = list;
        }
    }

    static class Turret implements Comparable<Turret>{
        // 좌표, 공격력, 최근 공격한 턴
        int x, y, power, aTurn;

        public Turret(int x, int y, int power, int aTurn) {
            this.x = x;
            this.y = y;
            this.power = power;
            this.aTurn = aTurn;
        }

        @Override
        public int compareTo(Turret o) {
            if (this.power != o.power) return this.power - o.power;
            if (this.aTurn != o.aTurn) return o.aTurn - this.aTurn;
            if (this.x + this.y != o.x + o.y) return (o.x + o.y) - (this.x + this.y);
            return o.y - this.y;
        }
    }

    static void printBoard(Turret[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(board[i][j].power + " ");
            }
            System.out.println();
        }
    }
}
