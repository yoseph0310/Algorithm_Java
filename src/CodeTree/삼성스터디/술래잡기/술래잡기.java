package CodeTree.삼성스터디.술래잡기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * - 개체
 *  1. 격자
 *      - N * N 크기
 *
 *  2. 술래
 *      - 정중앙 위치
 *
 *  3. 도망자
 *      - M 명 존재
 *      - 처음 지정된 곳에 서있음.
 *      - 좌우 이동, 상하 이동 유형 2가지 존재
 *      - 좌우 이동은 항상 오른쪽 보고 시작
 *      - 상하 이동은 항상 아래를 보고 시작
 *
 *  4. 나무
 *      - h 개의 나무 존재
 *      - 도망자와 초기에 겹쳐서 주어지는 것 가능
 *
 *  - 진행
 *   1. 도망자 이동
 *      - 술래와 거리가 3 이하인 도망자만 이동.
 *      - 거리 : |x1 - x2| + |y1 - y2|
 *      - 현재 방향으로 1칸 움직일 때 격자를 벗어나지 않는 경우
 *          - 움직이려는 칸이 술래라면 움직이지 않는다.
 *          - 움직이려는 칸에 술래가 없다면 해당 칸으로 이동. 해당 칸에 나무 있어도 무방
 *      - 현재 방향으로 1칸 움직일 때 격자를 벗어나는 경우
 *          - 방향을 반대로 튼다. 이후 바라보고 있는 방향으로 1칸 움직일 때 해당 위치에 술래가 없다면 1칸 이동
 *
 *   2. 술래 이동
 *      - 술래는 처음 위 방향으로 시작하여 달팽이 모양으로 움직인다. 끝에 도달하면 다시 거꾸로 중심으로 이동한다.
 *          - 방향을 2번 바꿀 때마다 이동할 거리 1씩 증가
 *      - 방향이 틀어지는 지점이라면 바로 방향을 튼다. 1,1이나 정중앙에 도착하면 아래, 위로 즉시 방향을 틀어야함
 *      - 술래가 방향을 틀면 바라보고 있는 방향 3칸 안에 있는 도망자를 잡게된다.
 *      - 하지만 나무가 놓여있다면 해당 칸의 도망자는 보이지 않아 안잡힌다.
 *      - 잡힌 도망자는 사라지고, 술래는 현재 턴을 t번째 턴이라 했을 때 t * (잡힌 도망자 수) 만큼 점수를 얻는다.
 *
 *   1,2번을 총 k번 반복
 *
 */
public class 술래잡기 {

    static final int B_RUNNER = 1;
    static final int B_TREE = 2;
    static final int B_SEEKER = 3;

    // 상 우 하 좌
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Runner extends Point {
        int dir, dist;

        public Runner(int x, int y, int dir) {
            super(x, y);
            this.dir = dir;
        }

        public void setDist() {
            this.dist = Math.abs(this.x - seeker.x) + Math.abs(this.y - seeker.y);
        }

        @Override
        public String toString() {
            return "Runner 좌표 : (" + x + ", " + y +
                    "), dir=" + dir +
                    ", dist=" + dist +
                    '}';
        }
    }

    static class Seeker extends Point {
        int moveCnt, rotateCnt, dir;
        boolean isStart;

        public Seeker(int x, int y) {
            super(x, y);
            this.moveCnt = 0;
            this.rotateCnt = 0;
            this.dir = 0;
            this.isStart = true;
        }

        @Override
        public String toString() {
            return "Seeker{" +
                    "moveCnt=" + moveCnt +
                    ", rotateCnt=" + rotateCnt +
                    ", dir=" + dir +
                    ", isStart=" + isStart +
                    '}';
        }
    }

    static int N, M, H, K, score;
    static List<Runner>[][] runnerBoard;
    static int[][] board;

    static List<Runner> runnerList;
    static Seeker seeker;
    static int mMoveCnt, mRotateCnt;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        mMoveCnt = 1;
        mRotateCnt = 2;

        // (1,1) 부터 시작하기 위함
        runnerBoard = new ArrayList[N + 1][N + 1];
        board = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                runnerBoard[i][j] = new ArrayList<>();
            }
        }

        runnerList = new ArrayList<>();
        seeker = new Seeker(N / 2 + 1, N / 2 + 1);

        // 도망자 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());       // 1 : 좌우, 2 : 상하

            int dir;
            if (d == 1) dir = 1;
            else dir = 2;

            Runner runner = new Runner(x, y, dir);
            runner.setDist();

            runnerList.add(runner);
            runnerBoard[x][y].add(runner);
        }

        // 나무 입력
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[x][y] = B_TREE;
        }
    }

    public static void main(String[] args) throws Exception {
        input();

//        System.out.println("<< 초기 위치 확인 >>");
//        printBoard(runnerBoard);
//        printBoard(board);
//        printRunnerStatus();

        // K 번의 턴 진행
        for (int t = 1; t <= K; t++) {
//            System.out.println("<< " + t + " 턴 진행 >>");
            // 도망자들 이동
//            System.out.println("-- 1. 도망자 이동 시작 -- ");
            for (Runner r: runnerList) {
                if (r.dist <= 3) {
                    moveRunner(r);
//                    printBoard(runnerBoard);
//                    printRunnerStatus();
                }
            }
//            System.out.println("-- 1. 도망자 이동 끝 -- ");

            // 술래 이동
//            System.out.println("-- 2. 술래 이동 시작 -- ");
            moveSeeker();

//            printBoard(board);
//            System.out.println("-- 2. 술래 이동 끝 -- ");

            // 도망자 잡기
//            System.out.println("-- 3. 도망자 사냥 시작 -- ");
            catchRunner(t);

//            printBoard(runnerBoard);
//            printBoard(board);
//            System.out.println("-- 3. 도망자 사냥 끝 -- ");
        }

        System.out.println(score);
    }

    static void catchRunner(int turn) {
        // 현재 술래좌표를 포함, 술래가 바라보는 방향 3칸 좌표를 확인한다.
        int cnt = 0;

        for (int i = 1; i <= 3; i++) {
            int nx = seeker.x + dx[seeker.dir] * i;
            int ny = seeker.y + dy[seeker.dir] * i;

            // 만약 나무가 있는 칸이거나 해당 칸에 도망자가 없다면 넘어간다.
            if (isNotBoundary(nx, ny) || board[nx][ny] == B_TREE || runnerBoard[nx][ny].isEmpty()) continue;

            // 해당 칸에 있는 도망자 수
            cnt += runnerBoard[nx][ny].size();

            // 도망자 리스트에서 잡힌 도망자 삭제
            for (Runner runner: runnerBoard[nx][ny]) {
                runnerList.remove(runner);
            }
            runnerBoard[nx][ny].clear();
        }

        score += cnt * turn;
    }

    static void moveRunner(Runner r) {
        int nx = r.x + dx[r.dir];
        int ny = r.y + dy[r.dir];

        if (isNotBoundary(nx, ny)) {
            r.dir = (r.dir + 2) % 4;

            nx = r.x + dx[r.dir];
            ny = r.y + dy[r.dir];
        }
        if (nx == seeker.x && ny == seeker.y) return;

        runnerBoard[r.x][r.y].remove(r);
        r.x = nx;
        r.y = ny;
        runnerBoard[r.x][r.y].add(r);
    }

    static void moveSeeker() {
        int nx = seeker.x + dx[seeker.dir];
        int ny = seeker.y + dy[seeker.dir];

        // 방향 바꾸지 않고 이동
        seeker.moveCnt++;

        board[seeker.x][seeker.y] = 0;
        seeker.x = nx;
        seeker.y = ny;
        board[seeker.x][seeker.y] = B_SEEKER;

//        System.out.println(turn + "턴 - 술래 : 이동");

        // 중앙에서 시작
        if (seeker.isStart) {
            // 방향을 바꾸는 경우
            if (seeker.moveCnt == mMoveCnt) {
//                System.out.println("방향바꿈");
                seeker.dir = (seeker.dir + 1) % 4;
                seeker.rotateCnt++;
                seeker.moveCnt = 0;
            }

            // 방향을 바꾸고 이동해야할횟수가 증가하는 경우
            if (seeker.rotateCnt == mRotateCnt) {
//                System.out.println("이동횟수 증가");
//                seeker.dir = (seeker.dir + 1) % 4;
                seeker.rotateCnt = 0;
                seeker.moveCnt = 0;

                mMoveCnt++;
            }


//            System.out.println("");
//            System.out.println(seeker.toString());
//            System.out.println("mMoveCnt : " + mMoveCnt);
        }
        // (1, 1) 에서 시작
        else {
//            System.out.println("(1,1)");
            // 방향을 바꾸는 경우
            if (seeker.moveCnt == mMoveCnt) {
//                System.out.println("방향바꿈");
                seeker.dir = (seeker.dir - 1 < 0) ? 3 : seeker.dir - 1;
                seeker.rotateCnt++;
                seeker.moveCnt = 0;
            }

            // 방향을 바꾸고 이동해야할횟수가 증가하는 경우
            if (seeker.rotateCnt == mRotateCnt) {
//                System.out.println("이동횟수 감소");
//                seeker.dir = (seeker.dir + 1) % 4;
                seeker.rotateCnt = 0;
                seeker.moveCnt = 0;

                mMoveCnt--;
            }

//            System.out.println("");
//            System.out.println(seeker.toString());
//            System.out.println("mMoveCnt : " + mMoveCnt);
        }

        if (seeker.x == 1 && seeker.y == 1) {
            seeker.isStart = false;
            seeker.moveCnt = 1;
            seeker.rotateCnt = 1;
            seeker.dir = (seeker.dir + 2) % 4;
        }
        else if (seeker.x == N / 2 + 1 && seeker.y == N / 2 + 1) {
            seeker.isStart = true;
            seeker.moveCnt = 0;
            seeker.rotateCnt = 0;
            seeker.dir = 0;

            mMoveCnt = 1;
        }


    }

    static boolean isNotBoundary(int x, int y) {
        return !(1 <= x && x <= N && 1 <= y && y <= N);
    }

    static void printRunnerStatus() {
        for (Runner r: runnerList) {
            System.out.println(r.toString());
        }
        System.out.println();
    }

    static void printBoard(int[][] b) {
        System.out.println(":: 술래 위치 확인 ::");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == seeker.x && j == seeker.y) System.out.print(B_SEEKER + "\t");
                else System.out.print("0\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printBoard(List<Runner>[][] b) {
        System.out.println(":: 도망자 위치 확인 ::");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (b[i][j].isEmpty()) System.out.print("0\t");
                else System.out.print(b[i][j].size() + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
