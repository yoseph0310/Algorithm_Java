package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SW_1215_회문1 {

    static char[][] board;
    static int N, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 1; t <= 10; t++) {
            N = Integer.parseInt(br.readLine());            // 찾아야할 회문의 길이
            board = new char[8][8];
            ans = 0;

            for (int i = 0; i < 8; i++) {
                String input = br.readLine();
                for (int j = 0; j < 8; j++) {
                    board[i][j] = input.charAt(j);
                }
            }

            // 가로 회문 탐색
            for (int i = 0; i < 8; i++) {
                row(i);
            }

            // 세로 회문 탐색
            for (int i = 0; i < 8; i++) {
                col(i);
            }

            System.out.println("#" + t + " " + ans);
        }
    }
    // 회문 판단 로직
    static boolean isCircular(char[] arr) {
        int len = arr.length;

        for (int i = 0, j = N - 1; i < len / 2; i++, j--) {
            if (arr[i] != arr[j]) return false;
        }

        return true;
    }

    // 조건에 해당하는 가로 회문 모두 탐색
    static void row(int x) {
        for (int i = 0; i <= 8 - N; i++) {
            int idx = 0;
            char[] arr = new char[N];

            for (int j = i; j < i+N; j++) {
                arr[idx++] = board[x][j];
            }

            if (isCircular(arr)) ans++;
        }
    }

    // 조건에 해당하는 세로 회문 모두 탐색
    static void col(int y) {
        for (int i = 0; i <= 8 - N; i++) {
            int idx = 0;
            char[] arr = new char[N];

            for (int j = i; j < i + N; j++) {
                arr[idx++] = board[j][y];
            }

            if (isCircular(arr)) ans++;
        }
    }
}
