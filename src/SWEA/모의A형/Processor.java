package SWEA.모의A형;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Processor {

    static int N, size, ans;
    static int [][] board;
    static Point [] coreList;
    static boolean[] check;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            size = 0;
            ans = Integer.MAX_VALUE;
            board = new int[N][N];
            coreList = new Point[12];
            check = new boolean[12];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < N - 1; j++) {
                    if(board[i][j] == 1){
                        coreList[size++] = new Point(i, j);
                    }
                }
            }

            for (int i = size; i >= 0; i--) {
                combination(0, 0, i);
                if (ans < Integer.MAX_VALUE) break;
            }

            System.out.println("#"+t+" "+ ans);
        }
    }
    public static void combination(int idx, int cnt, int R) {
        if (cnt == R){
            dfs(0, 0);
            return;
        }
        for (int i = idx; i < size; i++) {
            check[i] = true;
            combination(i + 1, cnt + 1, R);
            check[i] = false;
        }
    }

    public static void dfs(int idx, int cnt){
        if (idx == size){
            ans = Math.min(ans, cnt);
            return;
        }
        if (!check[idx]){
            dfs(idx +1, cnt);
            return;
        }
        for (int d = 0; d < 4; d++) {
            int x = coreList[idx].x;
            int y = coreList[idx].y;
            int tmp = 0;
            boolean success = false;

            while(true){
                x += dx[d];
                y += dy[d];
                if(x < 0 || x >= N || y < 0 || y >= N){
                    success = true;
                    break;
                }
                if(board[x][y] != 0) break;
                board[x][y] = 2;
                tmp++;
            }
            if(success) dfs(idx + 1, cnt + tmp);
            while(true){
                x -= dx[d];
                y -= dy[d];
                if (x == coreList[idx].x && y == coreList[idx].y) break;
                board[x][y] = 0;
            }

        }
    }

    static class Point{
        int x, y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
