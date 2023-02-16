package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_15685_드래곤_커브 {

    static int N;
    static boolean[][] board;

    static final int RIGHT = 0;
    static final int UP = 1;
    static final int LEFT = 2;
    static final int DOWN = 3;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new boolean[101][101];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            drawing(x, y, getDir(d, g));
        }

        System.out.println(getNumberOfSquares());
    }

    static int getNumberOfSquares() {
        int cnt = 0;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (board[i][j] && board[i + 1][j] && board[i][j + 1] && board[i + 1][j + 1]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void drawing(int x, int y, List<Integer> dirs) {
        board[x][y] = true;

        for (int dir: dirs) {
            switch (dir) {
                case RIGHT:
                    board[++x][y] = true;
                    break;
                case UP:
                    board[x][--y] = true;
                    break;
                case LEFT:
                    board[--x][y] = true;
                    break;
                case DOWN:
                    board[x][++y] = true;
                    break;
            }
        }
    }

    static List<Integer> getDir(int d, int g) {
        List<Integer> dirs = new ArrayList<>();
        dirs.add(d);

        while (g-- > 0) {
            for (int i = dirs.size() - 1; i >= 0; i--) {
                int dir = (dirs.get(i) + 1) % 4;
                dirs.add(dir);
            }
        }
        return dirs;
    }
}

/*

y, x : 시작점
d : 시작 방향
g : 세대

3
3 3 0 1
4 2 1 3
4 2 2 1

 * K세대 드래곤 커브는 K-1세대 드래곤 커브 끝점을 기준으로 90도 시계 방향 회전한다음 끝 점에 붙인 것 *

 */