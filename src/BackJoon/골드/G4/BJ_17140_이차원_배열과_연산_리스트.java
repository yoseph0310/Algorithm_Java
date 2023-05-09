package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_17140_이차원_배열과_연산_리스트 {

    static int r, c, k, answer;
    static int[][] board;
    static int[][] temp;

    static int row, col;

    static List<Num> numList;
    static int[] cnt_arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = -1;

        for (int t = 0; t <= 100; t++) {
            if (r < board.length && c < board[0].length) {
                if (board[r][c] == k) {
                    answer = t;
                    break;
                }
            }

            row = board.length;
            col = board[0].length;
            temp = new int[101][101];

            if (row >= col) R();
            else C();
        }

        System.out.println(answer);
    }

    static void R() {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < row; i++) {
            cnt_arr = new int[101];
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 0) continue;
                cnt_arr[board[i][j]]++;
            }

            numList = new ArrayList<>();
            for (int j = 1; j < cnt_arr.length; j++) {
                if (cnt_arr[j] != 0) numList.add(new Num(j, cnt_arr[j]));;
            }
            Collections.sort(numList);

            int z = 0;
            for (int j = 0; j < numList.size(); j++) {
                temp[i][z] = numList.get(j).idx;
                temp[i][z+1] = numList.get(j).cnt;
                z += 2;
            }

            if (max < numList.size() * 2) max = Math.min(100, numList.size() * 2);
        }

        board = new int[row][max];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = temp[i][j];
            }
        }
    }

    static void C() {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < col; i++) {
            cnt_arr = new int[101];
            for (int j = 0; j < row; j++) {
                if (board[j][i] == 0) continue;
                cnt_arr[board[j][i]]++;
            }

            numList = new ArrayList<>();
            for (int j = 1; j < cnt_arr.length; j++) {
                if (cnt_arr[j] != 0) numList.add(new Num(j, cnt_arr[j]));;
            }
            Collections.sort(numList);

            int z = 0;
            for (int j = 0; j < numList.size(); j++) {
                temp[z][i] = numList.get(j).idx;
                temp[z+1][i] = numList.get(j).cnt;
                z+=2;
            }

            if (max < numList.size() * 2) max = Math.min(100, numList.size() * 2);
        }

        board = new int[max][col];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = temp[i][j];
            }
        }

    }

    static class Num implements Comparable<Num> {
        int idx, cnt;

        public Num(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Num o) {
            if (o.cnt != this.cnt) return this.cnt - o.cnt;
            return this.idx - o.idx;
        }
    }
}
