package BackJoon.골드.G3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_15684_사다리조작 {

    static int N, M, H, ans;
    static int[][] board;
    static boolean finish = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        board = new int[H+1][N+1];

        int x, y;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            board[x][y] = 1;
            board[x][y+1] = 2;
        }

        for (int i = 0; i <= 3; i++) {
            ans = i;
            dfs(1, 0);
            if (finish) break;
        }

        System.out.println((finish) ? ans : -1);
    }
    static void dfs(int x, int cnt){

    }

    static boolean check(){
        for (int i = 1; i <= N; i++) {
            int x = 1, y = i;
            for (int j = 0; j < H; j++) {
                if (board[x][y] == 1) y++;
                else if (board[x][y] == 2) y--;
                x++;
            }
            if (y != i) return false;
        }
        return true;
    }
}
