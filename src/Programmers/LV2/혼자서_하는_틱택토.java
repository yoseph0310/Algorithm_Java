package Programmers.LV2;

public class 혼자서_하는_틱택토 {
    public static void main(String[] args) {
        String[] board = {"O.X", ".O.", "..X"};
        String[] board2 = {"OOO", "...", "XXX"};
        String[] board3 = {"...", ".X.", "..."};
        String[] board4 = {"...", "...", "..."};

        System.out.println(solution(board));
        System.out.println(solution(board2));
        System.out.println(solution(board3));
        System.out.println(solution(board4));
    }

    static char[][] map;

    public static int solution(String[] board) {
        int answer = 1;
        int oCnt = 0;
        int xCnt = 0;

        map = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                map[i][j] = board[i].charAt(j);

                if (map[i][j] == 'O') oCnt++;
                if (map[i][j] == 'X') xCnt++;
            }
        }

        if (xCnt > oCnt || oCnt - xCnt > 1) return 0;
        if (win('O') > 0 && win('X') > 0) return 0;
        if (win('O') > 0) {
            if (oCnt == xCnt) return 0;
        }
        if (win('X') > 0) {
            if (oCnt > xCnt) return 0;
        }

        return answer;
    }

    public static int win(char c) {
        int game = 0;

        for (int i = 0; i < 3; i++) {
            if (map[i][0] == c && map[i][1] == c && map[i][2] == c) game++;
            if (map[0][i] == c && map[1][i] == c && map[2][i] == c) game++;
        }

        if (map[0][0] == c && map[1][1] == c && map[2][2] == c) game++;
        if (map[0][2] == c && map[1][1] == c && map[2][0] == c) game++;

        return game;
    }
}
