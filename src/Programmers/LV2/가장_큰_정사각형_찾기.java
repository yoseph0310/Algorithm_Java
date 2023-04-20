package Programmers.LV2;

public class 가장_큰_정사각형_찾기 {
    public int solution(int[][] board) {
        int answer = 0;

        int[][] newBoard = new int[board.length+1][board[0].length + 1];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                newBoard[i+1][j+1] = board[i][j];
            }
        }

        for (int i = 1; i < newBoard.length; i++) {
            for (int j = 1; j < newBoard[i].length; j++) {
                if (newBoard[i][j] == 1) {
                    newBoard[i][j] = Math.min(newBoard[i-1][j-1], Math.min(newBoard[i-1][j], newBoard[i][j-1])) + 1;
                    answer = Math.max(answer, newBoard[i][j]);
                }
            }
        }

        return answer * answer;
    }
}
