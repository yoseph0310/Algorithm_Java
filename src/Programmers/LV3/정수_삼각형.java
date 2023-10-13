package Programmers.LV3;

public class 정수_삼각형 {
    public int solution(int[][] t) {
        int N = t.length;
        int[][] board = new int[N + 1][N + 1];

        board[0][0] = t[0][0];

        for (int i = N - 2; i >= 0; i--) {
            for (int j = 0; j < t[i].length; j++) {
                int left = t[i + 1][j];
                int right = t[i + 1][j + 1];

                t[i][j] = Math.max(t[i][j] + left, t[i][j] + right);
            }
        }

        return t[0][0];
    }
}
