package BackJoon.골드.G1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16933_벽_부수고_이동하기_3 {

    static int N, M, K, ans;
    static char[][] board;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j);
            }
        }

        ans = bfs();
        System.out.println(ans);
    }

    static int bfs() {
        Queue<Point> q = new LinkedList<>();
        boolean[][][][] visited = new boolean[N][M][K + 1][2];

        q.add(new Point(0, 0, 1, 0, 0));
        visited[0][0][0][0] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();

            int cx = cur.x;
            int cy = cur.y;

            if (cx == N-1 && cy == M-1) {
                return cur.cnt;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isBoundary(nx, ny)) {
                    // 벽이 아닌 경우
                    if (board[nx][ny] == '0') {
                        if (cur.isDay == 0 && !visited[nx][ny][cur.destroyedCnt][cur.isDay + 1]) {
                            q.add(new Point(nx, ny, cur.cnt + 1, cur.destroyedCnt, cur.isDay + 1));
                            visited[nx][ny][cur.destroyedCnt][cur.isDay + 1] = true;
                        } else if (cur.isDay == 1 && !visited[nx][ny][cur.destroyedCnt][cur.isDay - 1]) {
                            q.add(new Point(nx, ny, cur.cnt + 1, cur.destroyedCnt, cur.isDay - 1));
                            visited[nx][ny][cur.destroyedCnt][cur.isDay - 1] = true;
                        }
                    }
                    // 벽인 경우
                    else {
                        // 낮인 경우 부수고 감
                        if (cur.isDay == 0 && cur.destroyedCnt < K && !visited[nx][ny][cur.destroyedCnt + 1][cur.isDay + 1]) {
                            q.add(new Point(nx, ny, cur.cnt + 1, cur.destroyedCnt + 1, cur.isDay + 1));
                            visited[nx][ny][cur.destroyedCnt + 1][cur.isDay + 1] = true;
                        }
                        // 밤인 경우 머무르고 기다림
                        else if (cur.isDay == 1 && cur.destroyedCnt < K && !visited[nx][ny][cur.destroyedCnt][cur.isDay - 1]) {
                            q.add(new Point(nx, ny, cur.cnt + 1, cur.destroyedCnt, cur.isDay - 1));
                            visited[nx][ny][cur.destroyedCnt + 1][cur.isDay - 1] = true;
                        }

                    }
                }
            }
        }

        return -1;
    }


    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    static class Point {
        int x, y, cnt, destroyedCnt, isDay;

        public Point(int x, int y, int cnt, int destroyedCnt, int isDay) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.destroyedCnt = destroyedCnt;
            this.isDay = isDay;
        }
    }
}
