package BackJoon.골드.G5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_18405_경쟁적_전염 {

    static int N, K, S;        // N : 시험관 크기, K : 바이러스 개수, S : 경과해야하는 시간
    static int[][] board;
    static int X, Y;                // 확인해야할 바이러스 위치
    static ArrayList<Virus> virusList;
    static Queue<Virus> q;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        virusList = new ArrayList<>();
        q = new LinkedList<>();
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] != 0) virusList.add(new Virus(i, j, board[i][j], 0));
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        Collections.sort(virusList);
        for (Virus v: virusList) {
            q.add(v);
            visited[v.x][v.y] = true;
        }

        bfs();

        System.out.println(board[X-1][Y-1]);
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Virus cur = q.poll();

            int cx = cur.x;
            int cy = cur.y;

            if (cur.time == S) break;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isBoundary(nx, ny)) {
                    if (board[nx][ny] == 0 && !visited[nx][ny]) {
                        board[nx][ny] = cur.num;
                        visited[nx][ny] = true;
                        q.add(new Virus(nx, ny, cur.num, cur.time + 1));
                    }
                }
            }
        }


    }

    static boolean isBoundary(int x, int y) {
        if (0 <= x && x < N && 0 <= y && y < N) {
            return true;
        } else {
            return false;
        }
    }

    static class Virus implements Comparable<Virus>{
        int x, y, num, time;

        public Virus(int x, int y, int num, int time){
            this.x = x;
            this.y = y;
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Virus o) {
            if (this.num < o.num) return -1;
            else if (this.num > o.num) return 1;
            return 0;
        }
    }
}
