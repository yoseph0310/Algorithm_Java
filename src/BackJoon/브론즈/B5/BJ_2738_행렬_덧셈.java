package BackJoon.브론즈.B5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2738_행렬_덧셈 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] board = new int[10][10];
        int max = 0;
        int ansR = 0;
        int ansC = 0;

        for (int i = 1; i <= 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (max <= board[i][j]) {
                    max = board[i][j];
                    ansR = i;
                    ansC = j;
                }
            }
        }

        System.out.println(max);
        System.out.println(ansR + " " + ansC);
    }
}
