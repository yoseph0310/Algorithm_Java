package SWEA.D3;

import Z_DataStructure.LinkedList.Doubly.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1209_Sum {

    static int[][] board;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 1; t <= 10; t++) {
            int N = Integer.parseInt(br.readLine());
            max = 0;

            board = new int[100][100];
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < 100; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            rowSum();
            colSum();
            leftDiagonalSum();
            rightDiagonalSum();

            System.out.println("#" + t + " " + max);
        }
    }

    // 각 행의 합을 담은 리스트
    static void rowSum() {
        for (int i = 0; i < 100; i++) {
            int sum = 0;

            for (int j = 0; j < 100; j++) {
                sum += board[i][j];
            }

            max = Math.max(max, sum);
        }
    }

    // 각 열의 합을 담은 리스트
    static void colSum() {
        for (int i = 0; i < 100; i++) {
            int sum = 0;

            for (int j = 0; j < 100; j++) {
                sum += board[j][i];
            }

            max = Math.max(max, sum);
        }

    }

    // 좌상 -> 우하로 가는 대각선 합
    static void leftDiagonalSum() {
        int sum = 0;

        for (int i = 0, j = 0; i < 100; i++, j++) {
            sum += board[i][j];
        }

        max = Math.max(max, sum);
    }

    // 우상 -> 좌하로 가는 대각선 합
    static void rightDiagonalSum() {
        int sum = 0;

        for (int i = 0, j = 99; i < 100; i++, j--) {
            sum += board[i][j];
        }

        max = Math.max(max, sum);
    }

    static void print_board(int[][] board) {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
