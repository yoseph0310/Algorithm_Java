package SWEA.모의A형;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 물놀이를가자 {

    static int N, M, ans;
    static int[][] board;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < M; j++) {
                    board[i][j] = str.charAt(j);
                }
            }

            bfs();
            System.out.println("#"+t+" "+ans);
        }
    }

    static void bfs(){
        Queue<Point> q = new LinkedList<>();

        int sum = 0;
        int tmp = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'W') {
                    q.add(new Point(i, j));
                    visited[i][j] = true;
                }
            }
        }

        while(!q.isEmpty()){
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Point point = q.poll();

                int x = point.x;
                int y = point.y;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]){
                        visited[nx][ny] = true;
                        q.add(new Point(nx, ny));
                        sum += tmp;
                    }
                }
            }
            tmp++;
        }
        ans = sum;
    }

    static class Point{
        int x, y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
