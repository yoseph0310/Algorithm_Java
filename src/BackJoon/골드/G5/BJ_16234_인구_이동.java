package BackJoon.골드.G5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16234_인구_이동 {

    static int N, L, R, ans;
    static int[][] board;
    static boolean[][] visited;
    static ArrayList<Point> list;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(move());
    }

    static int move() {
        int result = 0;

        while(true) {
            boolean isMove = false;
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        int sum = bfs(i, j);
                        if (list.size() > 1) {
                            changePopulation(sum);
                            isMove = true;
                        }
                    }
                }
            }

            if (!isMove) return result;
            result++;
        }
    }

    static int bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        list = new ArrayList<>();

        q.add(new Point(x, y));
        visited[x][y] = true;
        list.add(new Point(x, y));

        int sum = board[x][y];
        while(!q.isEmpty()) {
            Point cur = q.poll();

            int cx = cur.x;
            int cy = cur.y;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isBoundary(nx, ny)) {
                    if (!visited[nx][ny]) {
                        int diff = Math.abs(board[cx][cy] - board[nx][ny]);
                        if (L <= diff && diff <= R) {
                            q.add(new Point(nx, ny));
                            list.add(new Point(nx, ny));
                            sum += board[nx][ny];
                            visited[nx][ny] = true;
                        }
                    }
                }
            }
        }

        return sum;
    }

    static void changePopulation(int sum) {
        int avg = sum / list.size();
        for (Point p: list) {
            board[p.x][p.y] = avg;
        }
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
