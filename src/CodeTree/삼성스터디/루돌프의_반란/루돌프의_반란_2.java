package CodeTree.삼성스터디.루돌프의_반란;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 루돌프의_반란_2 {

    static class Point {
        int x, y, dist, dir;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void calDist() {
            this.dist = (int)Math.pow(this.x - rudolf.x, 2) + (int)Math.pow(this.y - rudolf.y, 2);
        }

        void setBlank() {
            board[x][y] = 0;
        }
    }

    static class Rudolf extends Point {
        public Rudolf(int x, int y) {
            super(x, y);
            setPoint();
        }

        void setPoint() {
            board[x][y] = M_RUDOLF;
        }
    }

    static class Santa extends Point implements Comparable<Santa> {
        int id, stunTurn, score;
        boolean isStunned, isFailed;

        public Santa(int id, int x, int y) {
            super(x, y);
            this.id = id;
            setPoint();
        }

        void setPoint() {
            board[x][y] = id;
        }

        @Override
        public int compareTo(Santa s) {
            if (s.dist != this.dist) return this.dist - s.dist;
            if (s.x != this.x) return s.x - this.x;
            return s.y - this.y;
        }

        @Override
        public String toString() {
            return  "[" + id + "] 번 산타 좌표 = ("+ x + ", " + y + "), 루돌프와거리=" + dist +
                    ", 턴=" + stunTurn +
                    ", 점수=" + score +
                    ", 기절여부=" + isStunned +
                    ", 탈락여부=" + isFailed +
                    '}';
        }
    }

    static final int M_RUDOLF = 31;
    static final int M_SANTA = 2;

    // 루돌프 8방 - 상 우상 우 우하 하 좌하 좌 좌상
    static final int[] rdx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] rdy = {0, 1, 1, 1, 0, -1, -1, -1};

    // 산타 4방 - 상 우 하 좌
    static final int[] sdx = {-1, 0, 1, 0};
    static final int[] sdy = {0, 1, 0, -1};

    static int N, M, P, C, D;

    static int[][] board;
    static Rudolf rudolf;
    static Santa[] santaPool;
    static HashMap<Integer, Santa> livedSantaHM;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        board = new int[N + 1][N + 1];
        santaPool = new Santa[P + 1];
        livedSantaHM = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        rudolf = new Rudolf(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for (int i = 1; i <= P; i++) {
            st = new StringTokenizer(br.readLine());

            int id = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            Santa s = new Santa(id, x, y);
            santaPool[id] = s;
            livedSantaHM.put(id, s);
        }
    }

    public static void main(String[] args) throws Exception {
        input();

        for (int turn = 1; turn <= M; turn++) {
            wakeUpSanta(turn);

            moveRudolf(turn);
            calDist();

            for (int i = 1; i <= P; i++) {
                Santa santa = santaPool[i];
                if (!santa.isStunned && !santa.isFailed) moveSanta(santa, turn);
            }

            if (livedSantaHM.isEmpty()) break;
            else {
                for (Santa s: livedSantaHM.values()) {
                    s.score++;
                }
            }
        }

        printAnswer();
    }

    static void moveSanta(Santa santa, int turn) {
        int dir = findDir(santa, rudolf, santa.id, M_SANTA);

        if (dir == -1) return;

        int nx = santa.x + sdx[dir];
        int ny = santa.y + sdy[dir];

        santa.setBlank();
        santa.x = nx;
        santa.y = ny;

        if (board[nx][ny] == M_RUDOLF) crash(santa, dir, turn, M_SANTA);
        else santa.setPoint();
    }

    static void moveRudolf(int turn) {
        calDist();

        PriorityQueue<Santa> pq = new PriorityQueue<>();
        pq.addAll(livedSantaHM.values());

        Santa targetSanta = pq.poll();

        int dir = findDir(rudolf, targetSanta, targetSanta.id, M_RUDOLF);

        int nx = rudolf.x + rdx[dir];
        int ny = rudolf.y + rdy[dir];

        rudolf.setBlank();

        if (board[nx][ny] != 0) crash(targetSanta, dir, turn, M_RUDOLF);

        rudolf.x = nx;
        rudolf.y = ny;
        rudolf.setPoint();
    }

    static int findDir(Point from, Point to, int santaId, int mOption) {
        int dir = -1;

        if (mOption == M_RUDOLF) {
            // 상
            if (from.x > to.x && from.y == to.y) dir = 0;
                // 우상
            else if (from.x > to.x && from.y < to.y) dir = 1;
                // 우
            else if (from.x == to.x && from.y < to.y) dir = 2;
                // 우하
            else if (from.x < to.x && from.y < to.y) dir = 3;
                // 하
            else if (from.x < to.x && from.y == to.y) dir = 4;
                // 좌하
            else if (from.x < to.x && from.y > to.y) dir = 5;
                // 좌
            else if (from.x == to.x && from.y > to.y) dir = 6;
                // 좌상
            else if (from.x > to.x && from.y > to.y) dir = 7;
        } else if (mOption == M_SANTA) {
            PriorityQueue<Point> pq = new PriorityQueue<>((p1, p2) -> {return p1.dist - p2.dist;});

            for (int d = 0; d < 4; d++) {
                int nx = from.x + sdx[d];
                int ny = from.y + sdy[d];

                if (isNotBoundary(nx, ny) || board[nx][ny] != M_RUDOLF && board[nx][ny] != 0) continue;

                Point checkPoint = new Point(nx, ny);
                checkPoint.calDist();
                checkPoint.dir = d;
                pq.add(checkPoint);
            }

            while (!pq.isEmpty()) {
                int dist = pq.peek().dist;

                if (dist > santaPool[santaId].dist) pq.poll();
                else {
                    dir = pq.poll().dir;
                    break;
                }
            }
        }

        return dir;
    }

    static void crash(Santa santa, int dir, int turn, int mOption) {
        if (mOption == M_RUDOLF) {
            santa.score += C;

            int nx = santa.x + rdx[dir] * C;
            int ny = santa.y + rdy[dir] * C;

            if (isNotBoundary(nx, ny)) {
                santa.isFailed = true;
                livedSantaHM.remove(santa.id);
            } else {
                santa.isStunned = true;
                santa.stunTurn = turn + 2;

                if (board[nx][ny] != 0) push(nx, ny, dir, mOption);

                santa.x = nx;
                santa.y = ny;
                santa.setPoint();
            }
        } else if (mOption == M_SANTA) {
            santa.score += D;

            int nDir = (dir + 2) % 4;
            int nx = santa.x + sdx[nDir] * D;
            int ny = santa.y + sdy[nDir] * D;

            if (isNotBoundary(nx, ny)) {
                santa.isFailed = true;
                livedSantaHM.remove(santa.id);
            } else {
                santa.isStunned = true;
                santa.stunTurn = turn + 2;

                if (board[nx][ny] != 0) push(nx, ny, nDir, mOption);

                santa.x = nx;
                santa.y = ny;
                santa.setPoint();;
            }
        }
    }

    static void push(int x, int y, int dir, int mOption) {
        Santa santa = livedSantaHM.get(board[x][y]);

        int nx = 0;
        int ny = 0;

        if (mOption == M_RUDOLF) {
            nx = santa.x + rdx[dir];
            ny = santa.y + rdy[dir];
        } else if (mOption == M_SANTA) {
            nx = santa.x + sdx[dir];
            ny = santa.y + sdy[dir];
        }

        if (isNotBoundary(nx, ny)) {
            santa.isFailed = true;
            livedSantaHM.remove(santa.id);
        } else {
            if (board[nx][ny] != 0) push(nx, ny, dir, mOption);

            santa.x = nx;
            santa.y = ny;
            santa.setPoint();
        }
    }

    static void calDist() {
        for (Santa s: livedSantaHM.values()) {
            s.dist = (int) Math.pow(s.x - rudolf.x, 2) + (int) Math.pow(s.y - rudolf.y, 2);
        }
    }

    static void wakeUpSanta(int turn) {
        for (Santa s: livedSantaHM.values()) {
            if (s.isStunned && s.stunTurn == turn) s.isStunned = false;
        }
    }

    static boolean isNotBoundary(int x, int y) {
        return !(1 <= x && x <= N && 1 <= y && y <= N);
    }

    static void printAnswer() {
        for (int i = 1; i <= P; i++) {
            System.out.print(santaPool[i].score + " ");
        }
    }

    static void TEST_STATE() {
        PRINT_BOARD();
        PRINT_SANTA_STATE();
    }
    static void PRINT_BOARD() {
        System.out.println(":: BOARD STATE ::");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (board[i][j] == M_RUDOLF) System.out.print("R\t");
                else System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
    static void PRINT_SANTA_STATE() {
        System.out.println(":: SANTA STATE ::");
        for (int i = 1; i <= P; i++) {
            System.out.println(santaPool[i].toString());
        }
        System.out.println();
    }
}