package SWEA.CodeBattle.No_24_보급로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class No_24_보급로_Dijkstra {

    static int N;
    static int[][] board;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < N; j++) {
                    board[i][j] = input.charAt(j) - '0';
                }
            }

            dijkstra(t, 0, 0);
        }
    }

    static void dijkstra(int t, int x, int y) {
        boolean[][] visited = new boolean[N][N];
        int[][] distance = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        distance[x][y] = 0;
        visited[0][0] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isBoundary(nx, ny) && !visited[nx][ny]) {
                distance[nx][ny] = board[nx][ny];
            }
        }

        for (int i = 0; i < N*N - 2; i++) {
            int min = Integer.MAX_VALUE;
            int minX = -1;
            int minY = -1;

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (!visited[j][k] && distance[j][k] != Integer.MAX_VALUE) {
                        if (distance[j][k] < min) {
                            min = distance[j][k];
                            minX = j;
                            minY = k;
                        }
                    }
                }
            }

            visited[minX][minY] = true;

            for (int d = 0; d < 4; d++) {
                int nx = minX + dx[d];
                int ny = minY + dy[d];

                if (isBoundary(nx, ny) && !visited[nx][ny]) {
                    if (distance[nx][ny] > distance[minX][minY] + board[nx][ny]) {
                        distance[nx][ny] = distance[minX][minY] + board[nx][ny];
                    }
                }
            }

        }

        System.out.println("#" + t + " " + distance[N-1][N-1]);
    }

    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static void printBoard(int[][] b) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.println();
        }
    }
}
