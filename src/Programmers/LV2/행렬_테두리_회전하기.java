package Programmers.LV2;

public class 행렬_테두리_회전하기 {
    int[][] board;
    int[] answer;

    public int[] solution(int rows, int columns, int[][] queries) {
        answer = new int[queries.length];

        board = new int[rows][columns];
        int nums = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = nums++;
            }
        }

        // queries의 요소 만큼 회전시키고 최소값 구한다. (회전, 최소값 구하기)
        for (int i = 0; i < queries.length; i++) {
            rotate(queries[i], i);
            System.out.println();
        }

        return answer;
    }

    // board의 상태를 query의 좌표를 이용하여 시계방향으로 한칸씩 회전시키고 회전된 숫자들 중 최솟값을 구한다.
    void rotate(int[] query, int idx) {
        int x1 = query[0] - 1;
        int y1 = query[1] - 1;
        int x2 = query[2] - 1;
        int y2 = query[3] - 1;

        int min = Integer.MAX_VALUE;

        int tmp = board[x1][y1];
        // 좌단 (상향)
        for (int i = x1 + 1; i <= x2; i++) {
            min = Math.min(min, board[i][y1]);
            board[i-1][y1] = board[i][y1];
        }
        // 하단 (좌향)
        for (int i = y1 + 1; i <= y2; i++) {
            min = Math.min(min, board[x2][i]);
            board[x2][i-1] = board[x2][i];
        }
        // 우단 (하향)
        for (int i = x2 - 1; i >= x1; i--) {
            min = Math.min(min, board[i][y2]);
            board[i+1][y2] = board[i][y2];
        }
        // 상단 (우향)
        for (int i = y2 - 1; i >= y1; i--) {
            min = Math.min(min, board[x1][i]);
            board[x1][i+1] = board[x1][i];
        }
        board[x1][y1+1] = tmp;
        min = Math.min(min, board[x1][y1+1]);

        answer[idx] = min;
    }
}
