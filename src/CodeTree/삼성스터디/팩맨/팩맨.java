package CodeTree.삼성스터디.팩맨;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 팩맨 {

    // 상 좌상 좌 좌하 하 우하 우 우상
    static final int[] mdx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] mdy = {0, -1, -1, -1, 0, 1, 1, 1};

    // 상 좌 하 우
    static final int[] pdx = {-1, 0, 1, 0};
    static final int[] pdy = {0, -1, 0, 1};

    static final int MAX_DEAD_TURN = 2;
    static final int MAX_LEN = 4;
    static final int MAX_TIME = 25;
    static final int M_DIR_IDX = 8;
    static final int P_DIR_IDX = 4;

    static int M, T;
    static int N = 4;
    static int turn = 1;

    static int[][][][] monsterBoard = new int[MAX_TIME + 1][MAX_LEN][MAX_LEN][M_DIR_IDX];
    static int[][][] deadBoard = new int[MAX_LEN][MAX_LEN][MAX_DEAD_TURN + 1];
    static Point packMan;


    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int px = Integer.parseInt(st.nextToken()) - 1;
        int py = Integer.parseInt(st.nextToken()) - 1;
        packMan = new Point(px, py);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int mx = Integer.parseInt(st.nextToken()) - 1;
            int my = Integer.parseInt(st.nextToken()) - 1;
            int md = Integer.parseInt(st.nextToken()) - 1;
            monsterBoard[0][mx][my][md]++;
        }

    }
    public static void main(String[] args) throws Exception {
        input();

        while (turn <= T) {
            process();
            turn++;
        }

        System.out.println(getAnswer());
    }

    static void process() {
        moveMonster();
        movePackMan();
        decreaseDead();
        hatchMonster();
    }

    // moveMonster 관련 START
    static void moveMonster() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M_DIR_IDX; k++) {
                    Monster nextPoint = getNextPoint(i, j, k);

                    int x = nextPoint.x;
                    int y = nextPoint.y;
                    int dir = nextPoint.dir;

                    monsterBoard[turn][x][y][dir] += monsterBoard[turn - 1][x][y][k];
                }
            }
        }
    }

    static Monster getNextPoint(int x, int y, int dir) {
        for (int cd = 0; cd < M_DIR_IDX; cd++) {
            int nd = (dir + cd + M_DIR_IDX) % M_DIR_IDX;
            int nx = x + mdx[nd];
            int ny = y + mdy[nd];

            if (canMove(nx, ny)) return new Monster(nx, ny, nd);
        }

        return new Monster(x, y, dir);
    }

    static boolean canMove(int x, int y) {
        // 경계선 안이고 시체가 아니고 팩맨이 없으면 이동 가능하다.
        return isBoundary(x, y) && deadBoard[x][y][0] == 0 && deadBoard[x][y][1] == 0 && !new Point(x, y).isSame(packMan);
    }

    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
    // moveMonster 관련 END

    // movePackMan 관련 START
    static void movePackMan() {
        int maxCnt = -1;
        Route best = new Route(-1, -1, -1);

        for (int i = 0; i < P_DIR_IDX; i++) {
            for (int j = 0; j < P_DIR_IDX; j++) {
                for (int k = 0; k < P_DIR_IDX; k++) {
                    int monsterCnt = getMonsterCnt(i, j, k);

                    if (maxCnt < monsterCnt) {
                        maxCnt = monsterCnt;
                        best = new Route(i, j, k);
                    }
                }
            }
        }

        eatMonster(best);
    }

    static int getMonsterCnt(int dir1, int dir2, int dir3) {
        int[] dirs = new int[]{dir1, dir2, dir3};
        int x = packMan.x;
        int y = packMan.y;
        int monsterCnt = 0;

        ArrayList<Point> visited = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            int nx = x + pdx[dirs[i]];
            int ny = y + pdy[dirs[i]];

            if (!isBoundary(nx, ny)) return -1;

            if (!contains(visited, new Point(nx, ny))) {
                for (int j = 0; j < M_DIR_IDX; j++) {
                    monsterCnt += monsterBoard[turn][nx][ny][j];
                }

                visited.add(new Point(nx, ny));
            }

            x = nx;
            y = ny;
        }

        return monsterCnt;
    }
    static boolean contains(ArrayList<Point> p, Point point) {
        for (int i = 0; i < p.size(); i++) {
            if (p.get(i).isSame(point)) return true;
        }

        return false;
    }

    static void eatMonster(Route route) {
        int dir1 = route.dir1;
        int dir2 = route.dir2;
        int dir3 = route.dir3;

        int[] dirs = new int[]{dir1, dir2, dir3};
        for (int i = 0; i < 3; i++) {
            int nx = packMan.x + pdx[dirs[i]];
            int ny = packMan.y + pdy[dirs[i]];

            for (int j = 0; j < M_DIR_IDX; j++) {
                deadBoard[nx][ny][MAX_DEAD_TURN] += monsterBoard[turn][nx][ny][j];
                monsterBoard[turn][nx][ny][j] = 0;
            }

            packMan.x = nx;
            packMan.y = ny;
        }
    }

    static void decreaseDead() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < MAX_DEAD_TURN; k++) {
                    deadBoard[i][j][k] = deadBoard[i][j][k + 1];
                }
                deadBoard[i][j][MAX_DEAD_TURN] = 0;
            }
        }
    }

    static void hatchMonster() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M_DIR_IDX; k++) {
                    monsterBoard[turn][i][j][k] += monsterBoard[turn - 1][i][j][k];
                }
            }
        }
    }

    static int getAnswer() {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M_DIR_IDX; k++) {
                   cnt += monsterBoard[T][i][j][k];
                }
            }
        }

        return cnt;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isSame(Point p) {
            return this.x == p.x && this.y == p.y;
        }
    }

    static class Monster extends Point {
        int dir;

        public Monster(int x, int y, int dir) {
            super(x, y);
            this.dir = dir;
        }
    }

    static class Route {
        int dir1, dir2, dir3;

        public Route(int dir1, int dir2, int dir3) {
            this.dir1 = dir1;
            this.dir2 = dir2;
            this.dir3 = dir3;
        }
    }
}
