package Programmers.LV2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 압축 {
    public int[] solution(String msg) {
        List<Integer> numList = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        makeDic(map);

        int dicIdx = 27;
        boolean isEnd = false;

        for (int idx = 0; idx < msg.length(); idx++) {
            String w = msg.charAt(idx)+"";

            while (map.containsKey(w)) {
                idx++;
                if (idx == msg.length()) {
                    isEnd = true;
                    break;
                }
                w += msg.charAt(idx);
            }

            if (isEnd) {
                numList.add(map.get(w));
                break;
            }

            numList.add(map.get(w.substring(0, w.length() - 1)));
            map.put(w, dicIdx++);

            idx--;
        }

        int[] answer = new int[numList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = numList.get(i);
        }

        return answer;
    }

    void makeDic(Map<String, Integer> map) {
        char c = 'A';

        for (int i = 1; i <= 26; i++) {
            map.put(c+"", i);

            c += 1;
        }

    }
}
