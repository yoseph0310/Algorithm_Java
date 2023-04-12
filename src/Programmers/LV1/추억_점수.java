package Programmers.LV1;

import java.util.*;

public class 추억_점수 {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];

        HashMap<String, Integer> map = new HashMap<>();
        int N = name.length;

        for (int i = 0; i < N; i++) {
            map.put(name[i], yearning[i]);
        }

        for (int i = 0; i < photo.length; i++) {
            int sum = 0;
            for (int j = 0; j < photo[i].length; j++) {
                if (map.containsKey(photo[i][j])) {
                    sum += map.get(photo[i][j]);
                }
            }

            answer[i] = sum;
        }


        return answer;
    }
}
/*
    1.
    ["may", "kein", "kain"]
    [5점, 10점, 1점]

    2.
    ["kali", "mari", "don", "tony"]

    ["kali", "mari", "don"]
    [11점, 1점, 55점]

    tony 점수가 없으면 3명만 더한다.
*/