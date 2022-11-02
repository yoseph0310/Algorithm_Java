package BackJoon.브론즈.B2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1100_하얀_칸 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] board = new char[8][8];
        int[][] chess_board = new int[8][8];

        int cnt = 0;

        for (int i = 0; i < 8; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 8; j += 2) {
                    chess_board[i][j] = 1;
                }
            }
            else {
                for (int j = 1; j < 8; j += 2) {
                    chess_board[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'F' && chess_board[i][j] == 1){
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}
