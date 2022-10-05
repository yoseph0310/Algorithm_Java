package BackJoon.실버.S2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_4963_섬의개수 {

    static int w, h, ans;
    static int[][] board;
    static boolean[][] visited;

    static int [] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int [] dy = {0, 1, 0, -1, 1, -1, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

//        boolean flag = true;

        while(true){
            st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0){
                break;
            }

            board = new int[h][w];
            visited = new boolean[h][w];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (board[i][j] == 1 && !visited[i][j]){
                        dfs(i, j);
                        ans++;
                    }
                }
            }
            System.out.println(ans);
        }
    }
    static void dfs(int i, int j){
        visited[i][j] = true;

        for (int d = 0; d < 8; d++) {
            int nx = i + dx[d];
            int ny = j + dy[d];

            if (0 <= nx && nx < h && 0 <= ny && ny < w && !visited[nx][ny] && board[nx][ny] == 1){
                dfs(nx, ny);
            }
        }

    }

    static void printBoard(int[][] board){
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
