package Programmers.LV1;

public class 행렬의덧셈 {
    public int[][] solution(int[][] arr1, int[][] arr2){
        int n = arr1.length;
        int m = arr2.length;

        int [][] answer = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer[i][j] = arr1[i][j] + arr[i][j];
            }
        }

        return answer;
    }
}
