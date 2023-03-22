package BackJoon.골드.G5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_6593_상범_빌딩 {

    static int L, R, C;
    static char[][][] building;
    static int[][][] board;
    static boolean[][][] visited;
    static boolean check = false;
    static Point start, end;

    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            check = false;
            StringTokenizer st = new StringTokenizer(br.readLine());
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) {
                break;
            }

            building = new char[L][R][C];
            board = new int[L][R][C];
            visited = new boolean[L][R][C];

            for (int l = 0; l < L; l++) {
                for (int r = 0; r < R; r++) {
                    String data = br.readLine();

//                    if (data.equals("")) {
//                        data = br.readLine();
//                    }

                    for (int c = 0; c < C; c++) {
                        building[l][r][c] = data.charAt(c);

                        if (building[l][r][c] == 'S') {
                            start = new Point(l, r, c);
                        } else if (building[l][r][c] == 'E') {
                            end = new Point(l, r, c);
                        }

                    }

                }
                String emptyInput = br.readLine();

            }

            bfs(start);
            if (!check) {
                System.out.println("Trapped!");
            }

        }

    }

    static void bfs(Point p) {
        Queue<Point> q = new LinkedList<>();

        q.add(p);
        visited[p.z][p.x][p.y] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();

            int cz = cur.z;
            int cx = cur.x;
            int cy = cur.y;

            if (cz == end.z && cx == end.x && cy == end.y) {
                System.out.println("Escaped in " + board[cz][cx][cy] + " minute(s).");
                check = true;
                return;
            }

            for (int d = 0; d < 6; d++) {
                int nz = cz + dz[d];
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isBoundary(nz, nx, ny)) {
                    if (!visited[nz][nx][ny] && building[nz][nx][ny] != '#') {
                        visited[nz][nx][ny] = true;
                        board[nz][nx][ny] = board[cz][cx][cy] + 1;
                        q.add(new Point(nz, nx, ny));
                    }
                }
            }
        }
    }

    static class Point {
        int z, x, y;

        public Point(int z, int x, int y) {
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }

    static boolean isBoundary(int z, int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C && 0 <= z && z < L;
    }
}

