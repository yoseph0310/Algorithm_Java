package BackJoon.골드.G1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16933_벽_부수고_이동하기_3 {

    static int n, m, k, ans;
    static int[][] board;
    static boolean[][][][] visited;

    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m][k+1][2];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }

        ans = -1;
        bfs();

        System.out.println(ans);
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1, 0 ,0));
        visited[0][0][0][0] = true;

        while(!q.isEmpty()) {
            Node cur = q.poll();

            int x = cur.x;
            int y = cur.y;

            if (x == n-1 && y == m-1) {
                ans = cur.dist;
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (0 > nx || nx >= n || 0 > ny || ny >= m) continue;

                if (board[nx][ny] == 0) {
                    if (cur.day == 0 && !visited[nx][ny][cur.boom][cur.day+1]) {
                        q.add(new Node(nx, ny, cur.dist + 1, cur.boom, cur.day + 1));
                        visited[nx][ny][cur.boom][cur.day+1] = true;
                    } else if (cur.day == 1 && !visited[nx][ny][cur.boom][cur.day-1]) {
                        q.add(new Node(nx, ny, cur.dist + 1, cur.boom, cur.day - 1));
                        visited[nx][ny][cur.boom][cur.day-1] = true;
                    }
                } else {
                    if (cur.boom < k && cur.day == 0 && !visited[nx][ny][cur.boom + 1][cur.day+1]) {
                        q.add(new Node(nx, ny, cur.dist + 1, cur.boom + 1, cur.day + 1));
                        visited[nx][ny][cur.boom+1][cur.day+1] = true;
                    } else if (cur.boom < k && cur.day == 1 && !visited[x][y][cur.boom][cur.day-1]) {
                        q.add(new Node(x, y, cur.dist + 1, cur.boom, cur.day - 1));
                        visited[x][y][cur.boom][cur.day-1] = true;
                    }
                }
            }
        }
    }
}

class Node {
    int x, y, dist, boom, day;

    public Node(int x, int y, int dist, int boom, int day) {
        this.x = x;
        this.y = y;
        this.dist = dist;
        this.boom = boom;
        this.day = day;
    }
}