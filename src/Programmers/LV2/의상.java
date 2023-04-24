package Programmers.LV2;

import java.util.HashMap;
import java.util.Iterator;

public class 의상 {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String[] cloth : clothes) {
            String kind = cloth[1];
            map.put(kind, map.getOrDefault(kind, 0) + 1);
        }

        Iterator<Integer> it = map.values().iterator();
        int answer = 1;

        while(it.hasNext()) {
            answer *= it.next().intValue() + 1;
        }

        return answer - 1;
    }
}
