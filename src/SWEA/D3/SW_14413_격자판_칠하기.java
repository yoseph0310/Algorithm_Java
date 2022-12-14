package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_14413_격자판_칠하기 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            char[][] board = new char[N][M];
            int[] arr = new int[4];
            String s = "";

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < M; j++) {
                    board[i][j] = input.charAt(j);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == '#') {
                        if ((i + j) % 2 == 0) {
                            arr[0] += 1;
                        } else if ((i + j) % 2 == 1) {
                            arr[1] += 1;
                        }
                    } else if (board[i][j] == '.') {
                        if ((i + j) % 2 == 0) {
                            arr[2] += 1;
                        } else if ((i + j) % 2 == 1) {
                            arr[3] += 1;
                        }
                    }
                }
            }

            if ((arr[0] != 0 && arr[1] != 0) || (arr[2] != 0 && arr[3] != 0) || (arr[0] != 0 && arr[2] != 0) || (arr[1] != 0 && arr[3] != 0)) s = "impossible";
            else s = "possible";

            System.out.println("#" + t + " " + s);
        }
    }


}