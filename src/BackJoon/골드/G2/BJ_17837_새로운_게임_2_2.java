package BackJoon.골드.G2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_17837_새로운_게임_2_2 {

    static int N, K;
    static int[][] color;
    static Chess[] chessArray;
    static LinkedList<Integer>[][] chessBoard;

    // 우 좌 상 하
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        color = new int[N][N];
        chessArray = new Chess[K];
        chessBoard = new LinkedList[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                chessBoard[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x, y, d;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken()) - 1;
            y = Integer.parseInt(st.nextToken()) - 1;
            d = Integer.parseInt(st.nextToken());

            if (d == 1) d = 0;
            else if (d == 4) d = 1;

            chessArray[i] = new Chess(x, y, d);
            chessBoard[x][y].add(i);
        }

        solve();
    }

    static void solve() {
        for (int t = 1; t <= 1000; t++) {
            for (int k = 0; k < K; k++) {
                int x = chessArray[k].x;
                int y = chessArray[k].y;
                int d = chessArray[k].d;
                int idx = searchIdx(k, x, y);

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (isNotBoundary(nx, ny) || color[nx][ny] == 2) {
                    chessArray[k].d = d = (d + 2) % 4;

                    nx = x + dx[d];
                    ny = y + dy[d];

                    if (isNotBoundary(nx, ny) || color[nx][ny] == 2) {
                        continue;
                    }
                }

                if (move(x, y, nx, ny, idx, color[nx][ny])) {
                    System.out.println(t);
                    return;
                }
            }
        }
        System.out.println("-1");
    }

    static boolean move(int x, int y, int nx, int ny, int idx, int color) {
        while (chessBoard[x][y].size() > idx) {
            int temp = -1;
            if (color == 0) {
                temp = chessBoard[x][y].remove(idx);
            } else {
                temp = chessBoard[x][y].removeLast();
            }

            chessArray[temp].x = nx;
            chessArray[temp].y = ny;
            chessBoard[nx][ny].add(temp);
        }

        if (chessBoard[nx][ny].size() >= 4) {
            return true;
        }
        return false;
    }

    static int searchIdx(int n, int x, int y) {
        for (int i = 0; i < chessBoard[x][y].size(); i++) {
            if (chessBoard[x][y].get(i) == n) {
                return i;
            }
        }
        return -1;
    }

    static boolean isNotBoundary(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }

    static class Chess {
        int x, y, d;

        public Chess(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
