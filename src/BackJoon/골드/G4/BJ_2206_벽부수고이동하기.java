package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2206_벽부수고이동하기 {

    static int N, M, ans;
    static char[][] board;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        bfs();

    }

    static void bfs(){
        Queue<Point> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2];

        q.add(new Point(0, 0, 1, false));

        while(!q.isEmpty()){
            Point cur = q.poll();
            int cx = cur.x;
            int cy = cur.y;

            if (cx == N - 1 && cy == M - 1) {
                System.out.println(cur.cnt);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isBoundary(nx, ny)){
                    int ncnt = cur.cnt + 1;

                    if (board[nx][ny] == '0'){
                        if (!cur.destroyed && !visited[nx][ny][0]) {
                            q.add(new Point(nx, ny, ncnt, false));
                            visited[nx][ny][0] = true;
                        } else if (cur.destroyed && !visited[nx][ny][1]) {
                            q.add(new Point(nx, ny, ncnt, true));
                            visited[nx][ny][1] = true;
                        }
                    } else if (board[nx][ny] == '1'){
                        if (!cur.destroyed){
                            q.add(new Point(nx, ny, ncnt, true));
                            visited[nx][ny][1] = true;
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }

    static boolean isBoundary(int x, int y){
        if (0 <= x && x < N && 0 <= y && y < M){
            return true;
        } else {
            return false;
        }
    }

    static class Point{
        int x, y, cnt;
        boolean destroyed;

        public Point(int x, int y, int cnt, boolean destroyed){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.destroyed = destroyed;
        }
    }
}