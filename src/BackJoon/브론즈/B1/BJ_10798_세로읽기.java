package BackJoon.브론즈.B1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_10798_세로읽기 {

    static int max_len;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[][] board = new char[5][15];
        int max = 0;

        for (int i = 0; i < board.length; i++) {
            String str = br.readLine();
            if (max < str.length()) max = str.length();

            for (int j = 0; j < str.length(); j++) {
                board[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[j][i] == '\0') continue;
                sb.append(board[j][i]);
            }
        }


        System.out.println(sb);
    }

}
