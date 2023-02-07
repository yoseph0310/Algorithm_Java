package BackJoon.골드.G2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_11967_불켜기 {

    static int N, M;
    static boolean[][] switched, visited;
    static ArrayList<Node>[][] graph;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        switched = new boolean[N][N];
        visited = new boolean[N][N];

        graph = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;               // (x, y)위치에서
            int y = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;               // (a, b)위치를 킬 수 있음
            int b = Integer.parseInt(st.nextToken()) - 1;

            Node endNode = new Node(a, b);
            graph[x][y].add(endNode);

        }

        int cnt = bfs() + 1;

        System.out.println(cnt);
    }

    static int bfs() {
        int cnt = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0));

        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        switched[0][0] = true;
        visited[0][0] = true;

        boolean turnOn = false;
        while(!q.isEmpty()) {
            Node cur = q.poll();

            for (Node n : graph[cur.x][cur.y]) {
                if (!switched[n.x][n.y]) {
                    switched[n.x][n.y] = true;
                    cnt++;
                    turnOn = true;
                }
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (isBoundary(nx, ny)) {
                    if (switched[nx][ny] && !visited[nx][ny]) {
                        q.add(new Node(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        if (turnOn) {
            cnt += bfs();
        }

        return cnt;
    }



    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
