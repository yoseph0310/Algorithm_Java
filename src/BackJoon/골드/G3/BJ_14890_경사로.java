package BackJoon.골드.G3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14890_경사로 {

    static int N, L, ans;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());
        L = stoi(st.nextToken());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = stoi(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            if (canPass(i, 0, 0)){
                ans++;
            }
            if (canPass(0, i, 1)){
                ans++;
            }
        }

        System.out.println(ans);
    }

    static boolean canPass(int x, int y, int dir){
        int[] height = new int[N];
        boolean[] visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            height[i] = (dir == 0) ? board[x][i] : board[i][y];
        }

        for (int i = 0; i < N - 1; i++) {
            // 높이가 같다면 패스
            if (height[i] == height[i + 1]){
                continue;
            }

            // 높이 차가 1보다 클 경우
            if (Math.abs(height[i] - height[i + 1]) > 1){
                return false;
            }

            // 내려가는 경우
            if (height[i+1] == height[i] - 1){
                for (int j = i + 1; j <= i + L; j++) {
                    if (j >= N || height[i + 1] != height[j] || visited[j]){
                        return false;
                    }
                    visited[j] = true;
                }
            }

            // 올라가는 경우
            else if (height[i + 1] == height[i] + 1) {
                for (int j = i; j > i - L; j--) {
                    if (j < 0 || height[i] != height[j] || visited[j]){
                        return false;
                    }
                    visited[j] = true;
                }
            }
        }
        return true;
    }
    static int stoi(String s){
        return Integer.parseInt(s);
    }
}
