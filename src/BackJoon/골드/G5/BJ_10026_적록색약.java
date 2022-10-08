package BackJoon.골드.G5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_10026_적록색약 {

    static int N, ans1, ans2;
    static char[][] board;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]){
                    dfs(i, j, board[i][j]);
                    ans1++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 'G') board[i][j] = 'R';
            }
        }

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]){
                    dfs(i, j, board[i][j]);
                    ans2++;
                }
            }
        }

        System.out.println(ans1+ " " + ans2);
    }

    static void dfs(int x, int y, char color){
        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isBoundary(nx, ny)){
                if (!visited[nx][ny] && board[nx][ny] == color){
                    dfs(nx, ny, color);
                }
            }
        }
    }

    static boolean isBoundary(int x, int y){
        if (0 <= x && x < N && 0 <= y && y < N){
            return true;
        } else {
            return false;
        }
    }
}
