package SWEA.모의A형;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 홈_방범_서비스 {

    static int N, M, ans;
    static int[][] board;
    static boolean[][] visited;
    static Queue<Point> q;

    static int K;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            ans = 0;

            q = new LinkedList<>();
            board = new int[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    init();
                    bfs(i, j);
                }
            }

            System.out.println("#"+t+" "+ans);
        }
    }

    static void bfs(int x, int y) {
        q.offer(new Point(x, y));
        visited[x][y] = true;

        int K = 1;
        int house = board[x][y] == 1 ? 1 : 0;

        // 운영 비용이 수익(집 * M)
        if (getOperationCost(K) <= house * M) {
            ans = Math.max(K, ans);
        }

        while (!q.isEmpty()) {
            int size = q.size();
            K++;

            for (int i = 0; i < size; i++) {
                Point cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];

                    if (isNotBoundary(nx, ny) || visited[nx][ny]) continue;

                    if (board[nx][ny] == 1) house++;

                    q.offer(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }

            if (getOperationCost(K) <= house * M) {
                ans = Math.max(ans, house);
            }
        }
    }

    static void init() {
        q.clear();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j] = false;
            }
        }
    }

    static int getOperationCost(int k) {
        return k * k + (k - 1) * (k - 1);
    }

    static boolean isNotBoundary(int x, int y) {
        return !(0 <= x && x < N && 0 <= y && y < N);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
