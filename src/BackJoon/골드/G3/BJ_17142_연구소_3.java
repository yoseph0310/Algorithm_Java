package BackJoon.골드.G3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17142_연구소_3 {

    static int N, M;
    static int[][] board;
    static Virus[] active;
    static ArrayList<Virus> virus = new ArrayList<>();
    static int emptySpace = 0;
    static int ans = Integer.MAX_VALUE;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        active = new Virus[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == 0) {
                    emptySpace++;
                } else if (board[i][j] == 2) {
                    virus.add(new Virus(i, j, 0));
                }
            }
        }

        if (emptySpace == 0) {
            System.out.println(0);
        } else {
            selectVirus(0, 0);
            System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
        }
    }

    static void selectVirus(int depth, int start) {
        if (depth == M) {
            bfs(emptySpace);
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            active[depth] = virus.get(i);
            selectVirus(depth + 1, i + 1);
        }
    }

    static void bfs(int emptySpace) {
        Queue<Virus> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < M; i++) {
            Virus virus = active[i];
            visited[virus.x][virus.y] = true;
            q.add(virus);
        }

        while(!q.isEmpty()) {
            Virus virus = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = virus.x + dx[d];
                int ny = virus.y + dy[d];

                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if (!visited[nx][ny] && board[nx][ny] != 1) {
                        if (board[nx][ny] == 0) emptySpace--;
                        if (emptySpace == 0) {
                            ans = Math.min(ans, virus.time + 1);
                            return;
                        }
                        visited[nx][ny] = true;
                        q.add(new Virus(nx, ny, virus.time + 1));
                    }
                }
            }
        }
    }

    static class Virus {
        int x, y, time;

        public Virus(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}