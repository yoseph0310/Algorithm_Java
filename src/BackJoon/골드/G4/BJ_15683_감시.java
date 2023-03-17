package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_15683_감시 {

    static int N, M;
    static int min = Integer.MAX_VALUE;

    static int[][] dist = {{}, {0, 1, 2, 3}, {}, {}, {}, {}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        ArrayList<Point> cctvList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] != 0 && board[i][j] != 6) {
                    cctvList.add(new Point(i, j, board[i][j]));
                }
            }
        }

        dfs(0, board, cctvList);
        System.out.println(min);
    }

    static void dfs(int cnt, int[][] board, ArrayList<Point> cctvList) {
        if (cnt == cctvList.size()) {
            min = Math.min(min, getZeroCnt(board));
            return;
        }

        int cctvNo = cctvList.get(cnt).cctvNo;
        int x = cctvList.get(cnt).x;
        int y = cctvList.get(cnt).y;
        int[][] copyBoard;

        if (cctvNo == 1) {
            copyBoard = copyBoard(board);
            checkLeft(copyBoard, x, y);
            dfs(cnt + 1, copyBoard, cctvList);

            copyBoard = copyBoard(board);
            checkRight(copyBoard, x, y);
            dfs(cnt + 1, copyBoard, cctvList);

            copyBoard = copyBoard(board);
            checkUp(copyBoard, x, y);
            dfs(cnt + 1, copyBoard, cctvList);

            copyBoard = copyBoard(board);
            checkDown(copyBoard, x, y);
            dfs(cnt + 1, copyBoard, cctvList);

        } else if (cctvNo == 2) {
            copyBoard = copyBoard(board);
            checkLeft(copyBoard, x, y);
            checkRight(copyBoard, x, y);
            dfs(cnt + 1, copyBoard, cctvList);

            copyBoard = copyBoard(board);
            checkUp(copyBoard, x, y);
            checkDown(copyBoard, x, y);
            dfs(cnt + 1, copyBoard, cctvList);

        } else if (cctvNo == 3) {
            copyBoard = copyBoard(board);
            checkUp(copyBoard, x, y);
            checkRight(copyBoard, x, y);
            dfs(cnt + 1, copyBoard, cctvList);

            copyBoard = copyBoard(board);
            checkRight(copyBoard, x, y);
            checkDown(copyBoard, x, y);
            dfs(cnt + 1, copyBoard, cctvList);

            copyBoard = copyBoard(board);
            checkDown(copyBoard, x, y);
            checkLeft(copyBoard, x, y);
            dfs(cnt + 1, copyBoard, cctvList);

            copyBoard = copyBoard(board);
            checkLeft(copyBoard, x, y);
            checkUp(copyBoard, x, y);
            dfs(cnt + 1, copyBoard, cctvList);

        } else if (cctvNo == 4) {
            copyBoard = copyBoard(board);
            checkLeft(copyBoard, x, y);
            checkUp(copyBoard, x, y);
            checkRight(copyBoard, x, y);
            dfs(cnt + 1, copyBoard, cctvList);

            copyBoard = copyBoard(board);
            checkUp(copyBoard, x, y);
            checkRight(copyBoard, x, y);
            checkDown(copyBoard, x, y);
            dfs(cnt + 1, copyBoard, cctvList);

            copyBoard = copyBoard(board);
            checkRight(copyBoard, x, y);
            checkDown(copyBoard, x, y);
            checkLeft(copyBoard, x, y);
            dfs(cnt + 1, copyBoard, cctvList);

            copyBoard = copyBoard(board);
            checkDown(copyBoard, x, y);
            checkLeft(copyBoard, x, y);
            checkUp(copyBoard, x, y);
            dfs(cnt + 1, copyBoard, cctvList);

        } else if (cctvNo == 5) {
            copyBoard = copyBoard(board);
            checkLeft(copyBoard, x, y);
            checkUp(copyBoard, x, y);
            checkRight(copyBoard, x, y);
            checkDown(copyBoard, x, y);
            dfs(cnt + 1, copyBoard, cctvList);
        }
    }

    static int[][] copyBoard(int[][] board) {
        int[][] tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = board[i][j];
            }
        }

        return tmp;
    }

    static int getZeroCnt(int[][] board) {
        int zeroCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) zeroCnt++;
            }
        }
        return zeroCnt;
    }

    static void checkLeft(int[][] board, int x, int y) {
        for (int i = y - 1; i >= 0; i--) {
            if (board[x][i] == 6) return;
            if (board[x][i] != 0) continue;
            board[x][i] = -1;
        }
    }

    static void checkRight(int[][] board, int x, int y) {
        for (int i = y + 1; i < M; i++) {
            if (board[x][i] == 6) return;
            if (board[x][i] != 0) continue;
            board[x][i] = -1;
        }
    }

    static void checkUp(int[][] board, int x, int y) {
        for (int i = x - 1; i >= 0; i--) {
            if (board[i][y] == 6) return;
            if (board[i][y] != 0) continue;
            board[i][y] = -1;
        }
    }

    static void checkDown(int[][] board, int x, int y) {
        for (int i = x + 1; i < N; i++) {
            if (board[i][y] == 6) return;
            if (board[i][y] != 0) continue;
            board[i][y] = -1;
        }
    }


    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    static class Point {
        int x, y, cctvNo;

        public Point(int x, int y, int cctvNo) {
            this.x = x;
            this.y = y;
            this.cctvNo = cctvNo;
        }
    }
}
