package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1220_Magnetic {

    static int[][] board;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 1; t <= 10; t++) {
            int N = Integer.parseInt(br.readLine()); // 항상 100
            board = new int[N][N];
            ans = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            check();

            System.out.println("#" + t + " " + ans);
        }
    }
    
    static void check() {
        for (int i = 0; i < 100; i++) {
            int lastCheck = 0;

            for (int j = 0; j < 100; j++) {
                // 1 이면
                if (board[j][i] == 1) {
                    lastCheck = 1;
                }
                if (lastCheck == 1 && board[j][i] == 2) {
                    ans++;
                    lastCheck = 2;
                }
            }
        }
    }
}
