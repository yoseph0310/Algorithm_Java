package BackJoon.골드.G5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7576_토마토 {

    static int M, N;
    static int[][] board;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static Queue<Point> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) q.add(new Point(i, j));
            }
        }

        System.out.println(bfs());
    }
    static int bfs(){
        while(!q.isEmpty()){
            Point cur = q.poll();

            int cx = cur.x; int cy = cur.y;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d]; int ny = cy + dy[d];

                if (0<=nx && nx < N && 0<= ny && ny <M){
                    if (board[nx][ny] == 0){
                        q.add(new Point(nx, ny));
                        board[nx][ny] = board[cx][cy] + 1;
                    }
                }
            }
        }

        int res = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0){
                    return -1;
                }
                res = Math.max(res, board[i][j]);
            }
        }

        if (res == 1){
            return 0;
        } else {
            return res - 1;
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}