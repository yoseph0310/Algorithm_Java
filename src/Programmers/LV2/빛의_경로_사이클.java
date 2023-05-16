package Programmers.LV2;
import java.util.*;

public class 빛의_경로_사이클 {

    int R, C;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};

    boolean[][][] isVisited;

    public int[] solution(String[] grid) {
        ArrayList<Integer> answer = new ArrayList<>();

        R = grid.length;
        C = grid[0].length();
        isVisited = new boolean[R][C][4];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                for (int d = 0; d < 4; d++) {
                    if (!isVisited[i][j][d]) answer.add(light(grid, i, j, d));
                }
            }
        }

        return answer.stream().sorted().mapToInt(i->i).toArray();
    }

    int light(String[] grid, int x, int y, int d) {
        int cnt = 0;

        while (true){
            if (isVisited[x][y][d]) break;

            cnt++;
            isVisited[x][y][d] = true;

            if (grid[x].charAt(y) == 'L') {
                d = (d + 3) % 4;
            } else if (grid[x].charAt(y) == 'R') {
                d = (d + 1) % 4;
            }

            x = (x + dx[d] + R) % R;
            y = (y + dy[d] + C) % C;
        }

        return cnt;
    }
}
