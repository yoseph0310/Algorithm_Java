package BackJoon.골드.G3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14442_벽부수고이동하기2 {

    static int N, M, K, ans;
    static char[][] board;

    static int[] dx = {-1, 0, 1, 0,};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        ans = bfs();
        System.out.println(ans);
    }

    static int bfs(){
        Queue<Point> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][K+1];

        q.add(new Point(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()){
            Point cur = q.poll();
            int cx = cur.x;
            int cy = cur.y;

            if (cx == N-1 && cy == M-1){
                return cur.cnt;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isBoundary(nx, ny)){
                    int ncnt = cur.cnt + 1;
                    if (board[nx][ny] == '0'){
                        if (!visited[nx][ny][cur.destroyedCnt]) {
                            q.add(new Point(nx, ny, ncnt, cur.destroyedCnt));
                            visited[nx][ny][cur.destroyedCnt] = true;
                        }
                    }
                    else if (board[nx][ny] == '1'){
                        if (cur.destroyedCnt < K && !visited[nx][ny][cur.destroyedCnt]) {
                            q.add(new Point(nx, ny, ncnt, cur.destroyedCnt + 1));
                            visited[nx][ny][cur.destroyedCnt] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }

    static boolean isBoundary(int x, int y){
        if (0 <= x && x < N && 0 <= y && y < M) return true;
        else return false;
    }

    static class Point{
        int x, y, cnt, destroyedCnt;

        public Point(int x, int y, int cnt, int destroyedCnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.destroyedCnt = destroyedCnt;
        }
    }
}
