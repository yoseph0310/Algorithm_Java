package SWEA.모의A형;

import Z_DataStructure.LinkedList.Doubly.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 무선_충전 {

    static int M, BC, ans;

    static ArrayList<BatteryCharge>[][] board;
    static ArrayList<BatteryCharge> bcList;

    static Person personA;
    static Person personB;
    static int[] moveA;
    static int[] moveB;

    // 상 우 하 좌
    static int[] dx = {0, -1, 0, 1, 0};
    static int[] dy = {0, 0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            BC = Integer.parseInt(st.nextToken());

            board = new ArrayList[11][11];
            bcList = new ArrayList<>();

            moveA = new int[M];
            moveB = new int[M];
            ans = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                moveA[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                moveB[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < BC; i++) {
                st = new StringTokenizer(br.readLine());

                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                bcList.add(new BatteryCharge(x, y, c, p));
            }

            personA = new Person(1,1);
            personB = new Person(10,10);

            solve();

            System.out.println("#"+t+" "+ans);
        }
    }

    static void solve() {

        for (BatteryCharge bc: bcList) {
            init(bc);
        }

        charge(personA, personB);

        for (int i = 0; i < M; i++) {
            movePerson(i);
            charge(personA, personB);
        }
    }

    static void init(BatteryCharge bc) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(bc.x, bc.y));

        if (board[bc.x][bc.y] == null) {
            board[bc.x][bc.y] = new ArrayList<>();
        }
        board[bc.x][bc.y].add(bc);

        int coverage = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Point cur = q.poll();

                for (int d = 1; d < 5; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];

                    if (isNotBoundary(nx, ny)) continue;
                    if (board[nx][ny] == null) {
                        board[nx][ny] = new ArrayList<>();
                    }
                    board[nx][ny].add(bc);
                    q.add(new Point(nx, ny));
                }
            }
            coverage++;

            if (coverage == bc.coverage) {
                break;
            }
        }

    }

    static void charge(Person pA, Person pB) {
        int max = Integer.MIN_VALUE;

        // A 의 위치에 BC 리스트 있을 때
        if (board[pA.x][pA.y] != null) {
            // B 위치에 BC 리스트 있을 때
            if (board[pB.x][pB.y] != null) {
                for (BatteryCharge bcA: board[pA.x][pA.y]) {
                    for (BatteryCharge bcB : board[pB.x][pB.y]) {
                        // 만약 둘이 같으면
                        if (bcA.equals(bcB)) {
                            max = Math.max(max, bcA.p);     // 어차피 총합이므로 나눌 필요 없음
                        }
                        // 다르면
                        else {
                            max = Math.max(max, bcA.p + bcB.p);
                        }
                    }
                }
            }
            // 없을 때
            else {
                for (BatteryCharge bcA : board[pA.x][pA.y]) {
                    max = Math.max(max, bcA.p);
                }
            }
        }
        // 없을 때
        else {
            // B 위치에 BC 리스트 있을 때
            if (board[pB.x][pB.y] != null) {
                for (BatteryCharge bcB: board[pB.x][pB.y]) {
                    max = Math.max(max, bcB.p);
                }
            }
            // 없을 때
            else {
                max = 0;
            }
        }

        ans += max;
    }

    static void movePerson(int idx) {
        personA.x += dx[moveA[idx]];
        personA.y += dy[moveA[idx]];

        personB.x += dx[moveB[idx]];
        personB.y += dy[moveB[idx]];
    }

    static boolean isNotBoundary(int x, int y) {
        return !(1 <= x && x <= 10 && 1 <= y && y <= 10);
    }

    static class Person {
        int x, y;

        public Person(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class BatteryCharge {
        int x, y, coverage, p;

        public BatteryCharge(int x, int y, int coverage, int p) {
            this.x = x;
            this.y = y;
            this.coverage = coverage;
            this.p = p;
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