package BackJoon.골드.G2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_19236_청소년_상어 {

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    static int maxSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] board = new int[4][4];
        List<Fish> fishList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;

                fishList.add(new Fish(i, j, dir, num, true));
                board[i][j] = num;
            }
        }

        fishList.sort(new Comparator<Fish>() {
            @Override
            public int compare(Fish o1, Fish o2) {
                return o1.num - o2.num;
            }
        });

        Fish f = fishList.get(board[0][0] - 1);
        Shark shark = new Shark(f.x, f.y, f.dir, f.num);
        f.isAlive = false;
        board[0][0] = -1;

        dfs(board, shark, fishList);
        System.out.println(maxSum);
    }

    static void dfs(int[][] board, Shark shark, List<Fish> fishList) {
        if (maxSum < shark.eatSum) {
            maxSum = shark.eatSum;
        }

        // 모든 물고기 이동
        fishList.forEach(e -> moveFish(e, board, fishList));

        // 상어 이동
        for (int dist = 1; dist < 4; dist++) {
            int nx = shark.x + dx[shark.dir] * dist;
            int ny = shark.y + dy[shark.dir] * dist;

            // 경계선 넘거나 빈칸이면 이동 못함. (1보다 작으면 빈칸임)
            if (isBoundary(nx, ny) && board[nx][ny] > 0) {
                // 상어는 이동하면서 많은 경우의 수를 내면서 많은 값들을 변경 시키므로 depth 별로 체크할 복사본들이 필요함
                int[][] copyBoard = copyBoard(board);
                List<Fish> copyFishList = copyFishList(fishList);

                copyBoard[shark.x][shark.y] = 0;
                Fish f = copyFishList.get(board[nx][ny] - 1);
                Shark newShark = new Shark(f.x, f.y, f.dir, shark.eatSum + f.num);
                f.isAlive = false;
                copyBoard[newShark.x][newShark.y] = -1;

                dfs(copyBoard, newShark, copyFishList);
            }
        }
    }

    static void moveFish(Fish f, int[][] board, List<Fish> fishList) {
        if (!f.isAlive) return;

        for (int d = 0; d < 8; d++) {
            int nd = (f.dir + d) % 8;
            int nx = f.x + dx[nd];
            int ny = f.y + dy[nd];

            // 경계선을 넘거나 다음 좌표 값이 상어이면 (0보다 작으면 상어이므로) 이동 불가
            if (isBoundary(nx, ny) && board[nx][ny] > -1) {
                board[f.x][f.y] = 0;

                // 다음 좌표가 빈칸이 아니라면 서로 바꿔야함.
                if (board[nx][ny] != 0) {
                    Fish temp = fishList.get(board[nx][ny] - 1);
                    temp.x = f.x;
                    temp.y = f.y;
                    board[f.x][f.y] = temp.num;
                }

                f.x = nx;
                f.y = ny;

                board[nx][ny] = f.num;
                f.dir = nd;
                return;
            }
        }
    }

    static int[][] copyBoard(int[][] board) {
        int[][] temp = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[i][j] = board[i][j];
            }
        }

        return temp;
    }

    static List<Fish> copyFishList(List<Fish> fishList) {
        List<Fish> temp = new ArrayList<>();

        fishList.forEach(e -> temp.add(new Fish(e.x, e.y, e.dir, e.num, e.isAlive)));

        return temp;
    }

    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 4;
    }

    static class Shark {
        int x, y, dir, eatSum;

        public Shark(int x, int y, int dir, int eatSum) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.eatSum = eatSum;
        }
    }

    static class Fish {
        int x, y, dir, num;
        boolean isAlive;

        public Fish(int x, int y, int dir, int num, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.num = num;
            this.isAlive = isAlive;
        }
    }
}
