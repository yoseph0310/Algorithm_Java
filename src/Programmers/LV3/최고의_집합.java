package Programmers.LV3;
import java.util.*;

public class 최고의_집합 {
    public int[] solution(int n, int s) {
        if (n > s) {
            return new int[]{-1};
        }

        int[] answer = new int[n];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = s / n;
        }

        for (int i = 0; i < s % n; i++) {
            answer[i]++;
        }
        Arrays.sort(answer);

        return answer;
    }
}
