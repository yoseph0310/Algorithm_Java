package BackJoon.실버.S2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_11724_연결요소의개수 {

    static int N, M, ans;
    static int [][] board;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N+1][N+1];
        visited = new boolean[N+1];

        for (int i = 1; i <=  M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = board[y][x] = 1;
        }

//        printBoard(board);
        ans = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]){
                bfs(i);
                ans++;
            }
        }
        System.out.println(ans);
    }

    static void bfs(int x){
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        visited[x] = true;

        while(!q.isEmpty()){
            int cur = q.poll();
            for (int i = 1; i <= N; i++) {
                if (!visited[i] && board[cur][i] == 1){
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    static void printBoard(int[][] board){
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
