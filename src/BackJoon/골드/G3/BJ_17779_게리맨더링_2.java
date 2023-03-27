package BackJoon.골드.G3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17779_게리맨더링_2 {

    static int N;
    static int[][] board;
    static int totalCnt = 0;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                totalCnt += board[i][j];
            }
        }

        // 1. 4중 포문으로 x,y,d1,d2 의 모든 경우의 수를 구해야함.
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (x + d1 + d2 >= N) continue;
                        if (y - d1 < 0 || y + d2 >= N) continue;

                        solution(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(min);
    }

    static void solution(int x, int y, int d1, int d2) {
        boolean[][] border = new boolean[N][N];
        int[] peopleCnt = new int[5];


        for (int i = 0; i <= d1; i++) {
            border[x + i][y - i] = true;
            border[x + d2 + i][y + d2 - i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            border[x + i][y + i] = true;
            border[x + d1 + i][y - d1 + i] = true;
        }

        // 1번 구역 인구수
        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (border[i][j]) break;
                peopleCnt[0] += board[i][j];
            }
        }

        // 2번 구역 인구수
        for (int i = 0; i <= x + d2; i++) {
            for (int j = N - 1; j > y; j--) {
                if (border[i][j]) break;
                peopleCnt[1] += board[i][j];
            }
        }

        // 3번 구역 인구수
        for (int i = x + d1; i < N; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (border[i][j]) break;
                peopleCnt[2] += board[i][j];
            }
        }
        // 4번 구역 인구수
        for (int i = x + d2 + 1; i < N; i++) {
            for (int j = N - 1; j >= y - d1 + d2; j--) {
                if (border[i][j]) break;
                peopleCnt[3] += board[i][j];
            }
        }

        // 5번 구역 인구수
        peopleCnt[4] = totalCnt;

        for (int i = 0; i < 4; i++) {
            peopleCnt[4] -= peopleCnt[i];
        }

        // 정렬
        Arrays.sort(peopleCnt);

        // 최대 - 최소
        min = Math.min(min, peopleCnt[4] - peopleCnt[0]);
    }
}
