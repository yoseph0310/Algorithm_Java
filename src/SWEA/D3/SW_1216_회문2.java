package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SW_1216_회문2 {

    static char[][] board;
    static final int L = 100;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test = 0; test < 10; test++) {
            int t = Integer.parseInt(br.readLine());
            board = new char[L][L];

            for (int i = 0; i < L; i++) {
                String input = br.readLine();
                for (int j = 0; j < L; j++) {
                    board[i][j] = input.charAt(j);
                }
            }

            for (int i = L; i > 0; i--) {
                if (solve(i)) {
                    System.out.println("#" + t + " " + i);
                    break;
                }
            }

        }
    }

    static boolean solve(int l) {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j <= (L - l); j++) {
                if (row(i, j, l) || col(j, i, l)) return true;
            }
        }

        return false;
    }

    static boolean row(int i, int j, int l) {
        String tmp = "";
        int cnt = 0;

        while (cnt < l) {
            tmp += board[i][j + cnt++];
        }

        StringBuffer bf = new StringBuffer(tmp);
        String reverse = bf.reverse().toString();

        return tmp.equals(reverse);
    }

    static boolean col(int i, int j, int l) {
        String tmp = "";
        int cnt = 0;

        while (cnt < l) {
            tmp += board[i + cnt++][j];
        }

        StringBuffer bf = new StringBuffer(tmp);
        String reverse = bf.reverse().toString();

        return tmp.equals(reverse);
    }

}
