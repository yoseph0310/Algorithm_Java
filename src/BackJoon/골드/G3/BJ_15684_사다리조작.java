package BackJoon.골드.G3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_15684_사다리조작 {

    static int N, M, H, ans;
    static int[][] board;
    static boolean isFinish = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        board = new int[H+1][N+1];


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = 1;
            board[x][y+1] = 2;
        }

        for (int i = 0; i <= 3; i++) {
            ans = i;
            dfs(1, 1, 0);
            if (isFinish) break;
        }

        System.out.println((isFinish) ? ans : -1);
    }

    static void dfs(int x, int y, int garoCnt) {
        if (isFinish) return;
        if (ans == garoCnt) {
            if (isSuccess()) {
                isFinish = true;
            }
            return;
        }

        for (int i = x; i <= H; i++) {
            for (int j = y; j < N; j++) {
                if (board[i][j] == 0 && board[i][j + 1] == 0){
                    board[i][j] = 1;
                    board[i][j+1] = 2;

                    dfs(1, 1, garoCnt + 1);

                    board[i][j] = 0;
                    board[i][j + 1] = 0;
                }
            }
        }
    }

    static boolean isSuccess(){
        for (int i = 1; i <= N; i++) {
            int nx = 1;
            int ny = i;

            while (nx <= H){
                if (board[nx][ny] == 1) ny++;
                else if (board[nx][ny] == 2) ny--;
                nx++;
            }

            if (ny != i) return false;
        }
        return true;
    }
}