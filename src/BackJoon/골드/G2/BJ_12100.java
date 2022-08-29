package BackJoon.골드.G2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
<<<<<<< HEAD

public class BJ_12100 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    }
}
=======
import java.util.StringTokenizer;

public class BJ_12100 {

    static int N;
    static int [][] board;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(ans);
    }

    public static void dfs(int depth){
        if (depth == 5){
            findMax();
            return;
        }

        int [][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            temp[i] = board[i].clone();
        }

        for (int d = 0; d < 4; d++) {
            move(d);
            dfs(depth + 1);
            for (int i = 0; i < N; i++) {
                board[i] = temp[i].clone();
            }
        }
    }

    public static void move(int d){
        switch (d){
            // 상
            case 0:
                for (int i = 0; i < N; i++) {
                    int idx = 0;
                    int block = 0;
                    for (int j = 0; j < N; j++) {
                        if (board[j][i] != 0){
                            if (block == board[j][i]){
                                board[idx - 1][i] = block * 2;
                                block = 0;
                                board[j][i] = 0;
                            }
                            else {
                                block = board[j][i];
                                board[j][i] = 0;
                                board[idx][i] = block;
                                idx++;
                            }
                        }

                    }
                }
                break;
            // 우
            case 1:
                for (int i = 0; i < N; i++) {
                    int idx = N - 1;
                    int block = 0;
                    for (int j = N - 1; j >= 0; j--) {
                        if (board[i][j] != 0){
                            if (block == board[i][j]){
                               board[i][idx + 1] = block * 2;
                               block = 0;
                               board[i][j] = 0;
                            }
                            else {
                                block = board[i][j];
                                board[i][j] = 0;
                                board[i][idx] = block;
                                idx--;
                            }
                        }
                    }
                }
                break;
            // 하
            case 2:
                for (int i = 0; i < N; i++) {
                    int idx = N - 1;
                    int block = 0;
                    for (int j = N - 1; j >= 0; j--) {
                        if (board[j][i] != 0){
                            if (block == board[j][i]){
                                board[idx + 1][i] = block * 2;
                                block = 0;
                                board[j][i] = 0;
                            }
                            else {
                                block = board[j][i];
                                board[j][i] = 0;
                                board[idx][i] = block;
                                idx--;
                            }
                        }
                    }
                }
                break;
            // 좌
            case 3:
                for (int i = 0; i < N; i++) {
                    int idx = 0;
                    int block = 0;
                    for (int j = 0; j < N; j++) {
                        if (board[i][j] != 0){
                            if (block == board[i][j]){
                                board[i][idx - 1] = block * 2;
                                block = 0;
                                board[i][j] = 0;
                            }
                            else {
                                block = board[i][j];
                                board[i][j] = 0;
                                board[i][idx] = block;
                                idx++;
                            }
                        }
                    }
                }
                break;
        }
    }

    public static void findMax(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans = Math.max(board[i][j], ans);
            }
        }
    }
}
>>>>>>> 0c283051c79c1cc6f9b35ed65bd5cf79774d2d60
