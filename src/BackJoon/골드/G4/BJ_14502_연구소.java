package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14502_연구소 {

    static int N, M, ans;
    static int[][] board;
    static int[][] copy_board;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
//        printBoard(board);
//        printBoard(copy_board);
        System.out.println(ans);
    }
    static void dfs(int depth) {
        if (depth == 3){
            bfs();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0){
                    board[i][j] = 1;
                    dfs(depth + 1);
                    board[i][j] = 0;
                }
            }
        }
    }
    static void bfs(){
        Queue<Virus> q = new LinkedList<>();
        copy_board = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy_board[i][j] = board[i][j];
                if (copy_board[i][j] == 2) q.add(new Virus(i, j));
            }
        }

        while(!q.isEmpty()){
            Virus virus = q.poll();

            int cx = virus.x;
            int cy = virus.y;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isBoundary(nx, ny)){
                    if (copy_board[nx][ny] == 0) {
                        q.add(new Virus(nx, ny));
                        copy_board[nx][ny] = 2;
                    }
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy_board[i][j] == 0){
                    cnt++;
                }
            }
        }
        ans = Math.max(ans, cnt);
    }

    static boolean isBoundary(int x, int y){
        if (0 <= x && x < N && 0 <= y && y < M){
            return true;
        } else {
            return false;
        }
    }

    static void printBoard(int[][] board){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class Virus {
        int x, y;

        public Virus(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}