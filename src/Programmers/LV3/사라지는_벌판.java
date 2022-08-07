package Programmers.LV3;

public class 사라지는_벌판 {
    public static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
    int boardRowLen, boardColLen;

    class Result{
        boolean win;
        int cnt;

        public Result(boolean win, int cnt){
            this.win = win;
            this.cnt = cnt;
        }
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        boardRowLen = board.length;
        boardColLen = board[0].length;
        return dfs(aloc[0], aloc[1], bloc[0], bloc[1], 0, board).cnt;
    }

    public Result dfs(int x1, int y1, int x2, int y2, int depth, int[][] board){
        boolean win = false;
        int minCnt = 5*5;
        int maxCnt = depth;

        if(board[x1][y1] == 1){
            for(int[] d : dir){
                int n_x1 = x1 + d[0];
                int n_y1 = y1 + d[1];
                if(isValid(n_x1, n_y1, board)){
                    board[x1][y1] = 0;

                    Result r = dfs(x2, y2, n_x1, n_y1, depth+1, board);
                    win |= !r.win;
                    if(!r.win){
                        minCnt = Math.min(minCnt, r.cnt);
                    }
                    else {
                        maxCnt = Math.max(maxCnt, r.cnt);
                    }
                    board[x1][y1] = 1;
                }
            }
        }
        return new Result(win, win ? minCnt : maxCnt);
    }

    public boolean isValid(int n_x1, int n_y1, int[][] board){
        if (0<= n_x1 && n_x1 < boardRowLen && 0 <= n_y1 && n_y1 < boardColLen && board[n_x1][n_y1] != 0){
            return true;
        }
        else {
            return false;
        }
    }
}
