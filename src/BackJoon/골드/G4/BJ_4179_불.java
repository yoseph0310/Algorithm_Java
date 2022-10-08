package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_4179_불 {

    static int R, C, ans;
    static char[][] board;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static Queue<Point> fire;
    static Queue<Point> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        fire = new LinkedList<>();
        q = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == 'J') q.add(new Point(i, j));
                else if (board[i][j] == 'F') fire.add(new Point(i, j));
            }
        }

        ans = 0;
        bfs();
        if (ans == 0) System.out.println("IMPOSSIBLE");
        else System.out.println(ans);
    }
    static void bfs(){
        int size = 0;
        while(!q.isEmpty()){
            size = fire.size();
            for (int f = 0; f < size; f++) {
                Point cur = fire.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];

                    if (isBoundary(nx, ny)){
                        if (board[nx][ny] == '.' || board[nx][ny] == 'J'){
                            board[nx][ny] = 'F';
                            fire.add(new Point(nx, ny));
                        }
                    }
                }
            }
            size = q.size();
            for (int p = 0; p < size; p++) {
                Point cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];

                    if (!isBoundary(nx, ny)){
                        ans = cur.cnt + 1;
                        return;
                    }
                    if (board[nx][ny] == '.'){
                        board[nx][ny] = 'J';
                        q.add(new Point(nx, ny, cur.cnt + 1));
                    }
                }
            }
        }
    }

    static boolean isBoundary(int x, int y){
        if (0 <= x && x < R && 0 <= y && y < C){
            return true;
        } else {
            return false;
        }
    }
    static class Point{
        int x, y, cnt;

        public Point(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
