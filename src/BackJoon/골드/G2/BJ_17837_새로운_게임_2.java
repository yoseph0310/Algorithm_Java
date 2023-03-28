package BackJoon.골드.G2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_17837_새로운_게임_2 {

    static int N, K, turn;
    static int[][] board;
    static LinkedList<Chess>[][] chessBoard;
    static Chess[] chessArray;

    static Stack<Chess> tempStack = new Stack<>();

    // 우 좌 상 하
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());       // 체스판 크기
        K = Integer.parseInt(st.nextToken());       // 말의 개수

        board = new int[N][N];                      // 체스판. 0 : 흰색 || 1 : 빨간색 || 2: 파란색
        chessArray = new Chess[K];
        chessBoard = new LinkedList[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                chessBoard[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 같은 칸에 말이 두 개 이상은 주어지지 않는다.
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            Chess chess = new Chess(x, y, d);

            chessArray[i] = chess;
            chessBoard[x][y].add(chess);
        }

        solve();

    }

    static void solve() {

        for (int t = 1; t <= 1000; t++) {
            for (int k = 0; k < K; k++) {

                int x = chessArray[k].x;
                int y = chessArray[k].y;
                int d = chessArray[k].d;
                int num = searchOrder(chessArray[k], x, y);

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (isNotBoundary(nx, ny) || board[nx][ny] == 2) {
                    if (d % 2 == 0) chessArray[k].d = d -= 1;
                    else chessArray[k].d = d += 1;

                    nx = x + dx[d];
                    ny = y + dy[d];

                    if (isNotBoundary(nx, ny) || board[nx][ny] == 2) continue;
                }

                if (move(x, y, nx, ny, num, board[nx][ny])) {
                    System.out.println(t);
                    return;
                }
            }
        }

        System.out.println("-1");

    }


    static boolean move(int x, int y, int nx, int ny, int num, int color) {
        while (chessBoard[x][y].size() > num) {
            Chess temp = null;
            if (color == 0) {
                temp = chessBoard[x][y].remove(num);
            } else {
                temp = chessBoard[x][y].removeLast();
            }

            temp.x = nx;
            temp.y = ny;
            chessBoard[nx][ny].add(temp);
        }

        if (chessBoard[nx][ny].size() >= 4) {
            return true;
        }
        return false;
    }

    static int searchOrder(Chess chess, int x, int y) {
        for (int i = 0; i < chessBoard[x][y].size(); i++) {
            if (chessBoard[x][y].get(i).equals(chess)) {
                return i;
            }
        }
        return -1;
    }

    static boolean isNotBoundary(int x, int y) {
        return 0 > x || x >= N || 0 > y || y >= N;
    }

    static class Chess {
        int x, y, d;

        public Chess(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
