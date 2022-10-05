package BackJoon.실버.S2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1260_DFS와BFS {

    static int N, M, V;
    static int[][] board;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        board = new int[N+1][N+1];
        visited = new boolean[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = board[y][x] = 1;
        }

//        printBoard(board);
        DFS(V);
        System.out.println();
        BFS();
    }
    static void BFS(){
        Queue<Integer> q = new LinkedList<>();
        visited = new boolean[N+1];
        q.add(V);
        visited[V] = true;

        while(!q.isEmpty()){
            int cur = q.poll();

            System.out.print(cur + " ");

            for (int i = 1; i <= N; i++) {
                if (board[cur][i] == 1 && board[i][cur] == 1 && !visited[i]){
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    static void DFS(int v){
        visited[v] = true;
        System.out.print(v + " ");
        for (int i = 1; i <= N; i++) {
            if (board[v][i] == 1 && !visited[i]) {
                DFS(i);
            }
        }
    }

    static void printBoard(int[][] board){
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
