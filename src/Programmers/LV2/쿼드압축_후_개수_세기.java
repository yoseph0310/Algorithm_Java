package Programmers.LV2;

public class 쿼드압축_후_개수_세기 {
    int[] answer = new int[2];

    public int[] solution(int[][] arr) {

        divide(0, 0, arr.length, arr);

        return answer;
    }

    void divide(int sx, int sy, int size, int[][] arr) {
        if (isAllSame(sx, sy, size, arr)) {
            answer[arr[sx][sy]]++;
            return;
        }

        divide(sx, sy, size / 2, arr);
        divide(sx + size / 2, sy, size / 2, arr);
        divide(sx, sy + size / 2, size / 2, arr);
        divide(sx + size / 2, sy + size / 2, size / 2, arr);
    }

    boolean isAllSame(int x, int y, int size, int[][] arr) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[x][y] != arr[i][j]) return false;
            }
        }
        return true;
    }
}
