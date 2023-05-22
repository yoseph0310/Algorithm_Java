package Programmers.LV2;

public class n의2승_배열_자르기 {
    public int[] solution(int n, long left, long right) {
        int len = (int)(right - left) + 1;
        int[] answer = new int[len];

        for (int i = 0; i < len; i++) {
            int row = (int)(left / n + 1);
            int col = (int)(left % n + 1);
            left++;

            answer[i] = Math.max(row, col);
        }

        return answer;
    }

}
