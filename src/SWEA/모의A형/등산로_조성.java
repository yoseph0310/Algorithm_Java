package SWEA.모의A형;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 등산로_조성 {

    static int N, K, ans;
    static int[][] board;
    static ArrayList<Point> maxList;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            ans = 0;

            board = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            getMaxList();
            // 안깎고 출발
            build();

            // 깎고 출발
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 1; k <= K; k++) {
                        board[i][j] -= k;
                        build();
                        board[i][j] += k;
                    }

                }
            }


            System.out.println("#"+t+" "+ans);
        }

    }
    // TODO 등산로를 조성하고 가장 긴 등산로의 길이를 찾는다.
    static void build() {
        // TODO maxList 지점부터 시작해서 dfs 로 최대 길이를 찾아간다.
        for (Point p: maxList) {
            dfs(p.x, p.y, 1);
        }

    }

    static void dfs(int x, int y, int maxLength) {

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isNotBoundary(nx, ny)) continue;
            if (board[nx][ny] < board[x][y]) {
                dfs(nx, ny, maxLength + 1);
            } else {
                ans = Math.max(ans, maxLength);
            }
        }

    }

    static boolean isNotBoundary(int x, int y) {
        return !(0 <= x && x < N && 0 <= y && y < N);
    }

    static void getMaxList() {
        int max = 0;
        maxList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, board[i][j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (max == board[i][j]) {
                    maxList.add(new Point(i, j));
                }
            }
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
