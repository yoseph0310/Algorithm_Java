package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class No_16_파핑파핑_지뢰찾기 {

    static int N, ans;
    static char[][] board;
    static int[][] cntMineBoard;

    // 상 우상 우 우하 하 좌하 좌 좌상
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            board = new char[N][N];
            cntMineBoard = new int[N][N];
            ans = 0;

            for (int i = 0; i < N; i++) {
                board[i] = br.readLine().toCharArray();
            }

            cntMine();

            // 현재 좌표가 지뢰가 아니고 주변의 지뢰 개수가 0인 부분 먼저 누른다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (cntMineBoard[i][j] == 0 && board[i][j] != '*') {
                        click(i, j);
                        ans++;
                    }
                }
            }

            // 눌리지 않은 나머지 좌표들도 cnt 한다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (cntMineBoard[i][j] > 0 && board[i][j] != '*') {
                        ans++;
                    }
                }
            }

            System.out.println("#" + t + " " + ans);
        }
    }

    static void click(int x, int y) {
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(x, y));
        cntMineBoard[x][y] = -1;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int d = 0; d < 8; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                // 경계를 벗어나며, 이미 클릭되었거나, 지뢰인 경우는 아무 동작도 하지 않는다.
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (cntMineBoard[nx][ny] == -1) continue;
                if (board[nx][ny] == '*') continue;

                // 만약 다음 좌표도 주변에 지뢰가 0인 곳이면 큐에 넣고 확인한다.
                if (cntMineBoard[nx][ny] == 0) q.add(new Point(nx, ny));
                cntMineBoard[nx][ny] = -1;
            }
        }
    }

    static void cntMine() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;

                if (board[i][j] == '.') {
                    for (int d = 0; d < 8; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N || board[nx][ny] == '.') continue;

                        cnt++;
                    }
                }

                cntMineBoard[i][j] = cnt;
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
