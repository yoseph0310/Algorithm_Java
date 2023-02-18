package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17141_연구소_2 {

    static int N, M;
    static int cnt = 0;
    static int ans = Integer.MAX_VALUE;
    static int[][] board;
    static boolean[] check;
    static ArrayList<Point> virus = new ArrayList<>();

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == 2) virus.add(new Point(i, j));
                if (board[i][j] == 0) cnt++;
            }
        }

        cnt += virus.size() - M;
        check = new boolean[virus.size()];

        if (cnt == 0) ans = 0;
        else combination(0, 0);

        System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans);
    }

    static void combination(int depth, int start) {
        if (depth == M) {
            int[][] copyBoard = copy();
            bfs(copyBoard, cnt);
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            check[i] = true;
            combination(depth + 1, i + 1);
            check[i] = false;
        }
    }

    static void bfs(int[][] board, int cnt) {
        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < virus.size(); i++) {
            if (check[i]) q.add(virus.get(i));
        }

        int time = 0;
        while(!q.isEmpty()) {
            if (ans <= time) break;

            int len = q.size();
            for (int i = 0; i < len; i++) {
                Point cur = q.poll();

                int cx = cur.x;
                int cy = cur.y;

                for (int d = 0; d < 4; d++) {
                    int nx = cx + dx[d];
                    int ny = cy + dy[d];

                    if (isBoundary(nx, ny)) {
                        if (board[nx][ny] == 0) {
                            board[nx][ny] = 2;
                            q.add(new Point(nx, ny));
                            cnt--;
                        }
                    }
                }
            }

            time++;
            if (cnt == 0) {
                ans = time;
                return;
            }
        }

    }

    static int[][] copy() {
        int[][] copyBoard = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copyBoard[i][j] = (board[i][j] == 2 ? 0 : board[i][j]);
            }
        }

        for (int i = 0; i < virus.size(); i++) {
            if (check[i]) {
                Point point = virus.get(i);
                copyBoard[point.x][point.y] = 2;
            }
        }
        return copyBoard;
    }

    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
