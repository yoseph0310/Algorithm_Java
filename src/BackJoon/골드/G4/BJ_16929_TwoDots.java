package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16929_TwoDots {

    static int N, M, start_x, start_y;
    static char[][] board;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0};            // 상 우 하 좌
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited = new boolean[N][M];
                start_x = i;
                start_y = j;
                if (dfs(i, j, 1)){
                    System.out.println("Yes");
                    return;
                }

            }
        }

        System.out.println("No");
    }

    static boolean dfs(int x, int y, int cnt){
        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isBoundary(nx, ny)){
                if (board[x][y] == board[nx][ny]){
                    if (!visited[nx][ny]){
                        visited[nx][ny] = true;
                        if (dfs(nx, ny, cnt + 1)) return true;
                    } else {
                        if (cnt >= 4 && start_x == nx && start_y == ny) return true;
                    }
                }
            }
        }

        return false;
    }

    static boolean isBoundary(int x, int y){
        if (0 <= x && x < N && 0 <= y && y < M){
            return true;
        } else {
            return false;
        }
    }

    static class Dot{
        int x, y, dir;

        public Dot(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
