package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_5427_불 {

    static int w, h, ans;
    static char[][] board;

    static Queue<Point> fire;
    static Queue<Point> q;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int x = 0, y = 0;

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            board = new char[h][w];
            fire = new LinkedList<>();
            q = new LinkedList<>();

            for (int i = 0; i < h; i++) {
                String s = br.readLine();
                for (int j = 0; j < w; j++) {
                    board[i][j] = s.charAt(j);
                    if (board[i][j] == '*') fire.add(new Point(i, j));
                    else if (board[i][j] == '@') q.add(new Point(i, j));
                }
            }
            ans = 0;
            bfs();
            if (ans == 0) System.out.println("IMPOSSIBLE");
            else System.out.println(ans);
        }

    }

    static void bfs(){
        int size = 0;
        while(!q.isEmpty()){
            size = fire.size();
            for (int f = 0; f < size; f++) {
                Point f_cur = fire.poll();

                for (int d = 0; d < 4; d++) {
                    int fnx = f_cur.x + dx[d];
                    int fny = f_cur.y + dy[d];

                    if (isBoundary(fnx, fny)){
                        if (board[fnx][fny] == '.' || board[fnx][fny] == '@'){
                            fire.add(new Point(fnx, fny));
                            board[fnx][fny] = '*';
                        }
                    }
                }
            }
            size = q.size();
            for (int p = 0; p < size; p++) {
                Point p_cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int pnx = p_cur.x + dx[d];
                    int pny = p_cur.y + dy[d];

                    if (!isBoundary(pnx, pny)){
                        ans = p_cur.cnt + 1;
                        return;
                    }
                    if (board[pnx][pny] == '.'){
                        board[pnx][pny] = '@';
                        q.add(new Point(pnx, pny, p_cur.cnt + 1));
                    }
                }
            }
        }
    }

    static boolean isBoundary(int x, int y){
        if (0 <= x && x < h && 0 <= y && y < w){
            return true;
        } else {
            return false;
        }
    }

    static class Point{
        int x, y, cnt;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public Point(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}