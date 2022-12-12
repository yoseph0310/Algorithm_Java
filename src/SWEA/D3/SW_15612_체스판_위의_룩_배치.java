package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SW_15612_체스판_위의_룩_배치 {

    static int ans;
    static char[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            ans = 0;
            board = new char[8][8];
            int rookCnt = 0;

            for (int i = 0; i < 8; i++) {
                String s = br.readLine();
                for (int j = 0; j < 8; j++) {
                    board[i][j] = s.charAt(j);
                    if (board[i][j] == 'O') rookCnt++;
                }
            }

            if (rookCnt != 8) System.out.println("#"+t+" "+"no");
            else if (horizonCheck() && verticalCheck()) System.out.println("#"+t+" "+"yes");
            else System.out.println("#"+t+" "+"no");
        }

    }

    static boolean horizonCheck() {

        for (int i = 0; i < 8; i++) {
            int cnt = 0;
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'O') cnt++;
                if (cnt > 1) return false;
            }
        }

        return true;
    }

    static boolean verticalCheck() {

        for (int i = 0; i < 8; i++) {
            int cnt = 0;
            for (int j = 0; j < 8; j++) {
                if (board[j][i] == 'O') cnt++;
                if (cnt > 1) return false;
            }
        }

        return true;
    }
}
