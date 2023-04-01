package Programmers.LV1;

import java.util.Arrays;
import java.util.Collections;

public class 과일_장수 {
    public int sol1(int k, int m, int[] score) {
        int answer = 0;

        Integer[] w_score = new Integer[score.length];
        for (int i = 0; i < score.length; i++) {
            w_score[i] = score[i];
        }

        Arrays.sort(w_score, Collections.reverseOrder());

        for (int i = 0; i < w_score.length; i++) {
            if ((i + 1) % m == 0) answer += w_score[i] * m;
        }

        return answer;
    }

    public int sol2(int k, int m, int[] score) {
        int answer = 0;

        Arrays.sort(score);

        for (int i = score.length - 1; i >= 0; i--) {
            if ((score.length - i) % m == 0) answer += score[i] * m;
        }

        return answer;
    }
}
