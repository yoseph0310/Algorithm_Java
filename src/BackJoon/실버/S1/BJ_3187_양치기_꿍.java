package BackJoon.실버.S1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3187_양치기_꿍 {

    static int R, C;
    static char[][] board;
    static boolean[][] visited;
    static int[] res;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        visited = new boolean[R][C];
        res = new int[2];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] != '#' && !visited[i][j]) {
//                    System.out.println("start pos : " + i + ", " + j);
                    bfs(i, j);
                }
            }
        }

        System.out.println(res[0] + " " + res[1]);
    }

    static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        visited[x][y] = true;

        int sheep = 0;
        int wolf = 0;

        while(!q.isEmpty()) {
            Point cur = q.poll();

            int cx = cur.x; int cy = cur.y;

            if (board[cx][cy] == 'k') {
                sheep++;
            }
            else if (board[cx][cy] == 'v') {
                wolf++;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isBoundary(nx, ny)) {
                    if (board[nx][ny] != '#' && !visited[nx][ny]) {
                        q.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }

        }

        if (sheep > wolf) wolf = 0;
        else if (sheep <= wolf) sheep = 0;

        res[0] += sheep;
        res[1] += wolf;
    }

    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }

    static class Point {
        int x, y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
