package Programmers.LV1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 명예의_전당_1 {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < score.length; i++) {
            if (i < k - 1) {
                list.add(score[i]);
                list.sort(Collections.reverseOrder());
                answer[i] = list.get(list.size() - 1);
            } else {
                list.add(score[i]);
                list.sort(Collections.reverseOrder());
                answer[i] = list.get(k - 1);
            }
        }

        return answer;
    }
}
