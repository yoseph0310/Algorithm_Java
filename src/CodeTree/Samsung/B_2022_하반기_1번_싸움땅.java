package CodeTree.Samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_2022_하반기_1번_싸움땅 {

    static int N, M, K;

    static ArrayList<Integer>[][] board;

    static int[][] visited;
    static Player[] players;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new ArrayList[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                board[i][j] = new ArrayList<>();
            }
        }

        visited = new int[N + 1][N + 1];
        players = new Player[M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            players[i] = new Player(i, x, y, d, s);
            visited[x][y] = 1;
        }

        solve();
    }

    static void solve() {
        for (int k = 0; k < K; k++) {

            // 모든 사람 이동
            for (int i = 1; i <= M; i++) {
                move(players[i]);
            }
        }

        for (int i = 1; i <= M; i++) {
            System.out.print(players[i].score + " ");
        }
    }

    static void battle(Player p) {

        Player vp = null;

        for (int i = 1; i <= M; i++) {
            if (p.idx == players[i].idx) continue;
            if (p.x == players[i].x && p.y == players[i].y) {
                vp = players[i];
            }
        }

        int p_power = p.s + p.gun;
        int vp_power = vp.s + vp.gun;
        int score = Math.abs(p_power - vp_power);

        // p 승리
        if (p_power > vp_power) {
            // 격자에 총 내려놓고
            board[vp.x][vp.y].add(vp.gun);
            vp.gun = 0;
            loserMove(vp);

            getGun(p);
            calculateScore(p, score);

        } else if (p_power == vp_power) {
            // 초기 능력치는 모두 다름
            if (p.s > vp.s) {
                board[vp.x][vp.y].add(vp.gun);
                vp.gun = 0;
                loserMove(vp);

                getGun(p);
                calculateScore(p, score);
            } else if (p.s < vp.s) {
                board[p.x][p.y].add(p.gun);
                p.gun = 0;
                loserMove(p);

                getGun(vp);
                calculateScore(vp, score);
            }
        } else {
            board[p.x][p.y].add(p.gun);
            p.gun = 0;
            loserMove(p);

            getGun(vp);
            calculateScore(vp, score);
        }

    }

    static void calculateScore(Player p, int score) {
        p.score += score;
    }

    static void loserMove(Player p) {
        int cx = p.x;
        int cy = p.y;

        int nx = cx + dx[p.d];
        int ny = cy + dy[p.d];

        // 경계선 밖이거나 플레이어가 있으면
        while (!isBoundary(nx, ny) || visited[nx][ny] == 1) {
            p.d = (p.d + 1) % 4;

            nx = cx + dx[p.d];
            ny = cy + dy[p.d];
        }

        p.x = nx;
        p.y = ny;
        visited[cx][cy]--;
        visited[nx][ny]++;

        getGun(p);
    }

    static void getGun(Player p) {
        if (board[p.x][p.y].size() == 0) return;
        // 총을 들고 있다면
        if (p.gun != 0) {
            // TODO 플레이어가 든 총과 격자의 모든 총을 비교 한다.
            int maxIdx = 0;

            // 총을 놓는다.
            board[p.x][p.y].add(p.gun);
            p.gun = 0;

            // 내려놓은 총이랑 다같이 비교해서 제일 큰 것을 p.gun 에 넣는다.
            int size = board[p.x][p.y].size();
            for (int i = 0; i < size; i++) {
                if (board[p.x][p.y].get(i) > p.gun) {
                    p.gun = board[p.x][p.y].get(i);
                    maxIdx = i;
                }
            }

            // 격자에서 그 총을 없앤다.
            board[p.x][p.y].remove(maxIdx);
        }

        // 총을 안들고 있다면
        else {
            // 그 좌표에 있는 총들 중 가장 센거 들고 간다
            int max = 0;
            int maxIdx = 0;

            int size = board[p.x][p.y].size();
            for (int i = 0; i < size; i++) {
                if (board[p.x][p.y].get(i) > max) {
                    max = board[p.x][p.y].get(i);
                    maxIdx = i;
                }
            }

            // 플레이어는 격자에 있는 총을 들고 간다
            p.gun = max;
            board[p.x][p.y].remove(maxIdx);
        }
    }

    static void move(Player p) {
        int cx = p.x;
        int cy = p.y;

        int nx = cx + dx[p.d];
        int ny = cy + dy[p.d];

        if (!isBoundary(nx, ny)) {
            // TODO 방항 정반대로 바꿔서 한 칸 이동
            p.d = (p.d + 2) % 4;

            nx = cx + dx[p.d];
            ny = cy + dy[p.d];

        }
        p.x = nx;
        p.y = ny;
        visited[cx][cy]--;
        visited[nx][ny]++;

        if (visited[nx][ny] == 2) {
            battle(p);
        } else {
            getGun(p);
        }
    }

    static boolean isBoundary(int x, int y) {
        return 1 <= x && x <= N && 1 <= y && y <= N;
    }

    static class Player {
        int x, y, d, s, gun, score, idx;

        public Player(int idx, int x, int y, int d, int s) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.d = d;
            this.s = s;
        }
    }

    static void printBoard(int[][] board) {
        System.out.println();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
