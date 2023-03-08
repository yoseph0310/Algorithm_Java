package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14500_테트로미노 {

    static int N, M;
    static int max = Integer.MIN_VALUE;
    static int[][] board;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                solve(i, j, board[i][j], 1);
                visited[i][j] = false;
            }
        }

        System.out.println(max);
    }

    static void solve(int x, int y, int sum, int depth) {
        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int cx = x + dx[d];
            int cy = y + dy[d];

            if (0 <= cx && cx < N && 0 <= cy && cy < M) {
                if (!visited[cx][cy]) {
                    if (depth == 2) {
                        visited[cx][cy] = true;
                        solve(x, y, sum + board[cx][cy], depth + 1);
                        visited[cx][cy] = false;
                    }

                    visited[cx][cy] = true;
                    solve(cx, cy, sum + board[cx][cy], depth  +1);
                    visited[cx][cy] = false;
                }
            }
        }
    }

}
