package Programmers.LV2;
import java.util.*;

public class 메뉴_리뉴얼_2 {
    Map<String, Integer> map;

    public String[] solution(String[] orders, int[] course) {

        List<String> res = new ArrayList<>();

        for (int i = 0; i < orders.length; i++) {
            char[] c_arr = orders[i].toCharArray();

            Arrays.sort(c_arr);

            orders[i] = String.valueOf(c_arr);
        }

        for (int i = 0; i < course.length; i++) {
            map = new HashMap<>();
            int max = Integer.MIN_VALUE;

            for (int j = 0; j < orders.length; j++) {
                StringBuilder sb = new StringBuilder();

                if (course[i] <= orders[j].length()) {
                    comb(orders[j], course[i], sb, 0, 0);
                }
            }

            for (String key : map.keySet()) {
                max = Math.max(max, map.get(key));
            }

            for (String key : map.keySet())  {
                if (max >= 2 && map.get(key) == max) {
                    res.add(key);
                }
            }
        }
        Collections.sort(res);

        String[] answer = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            answer[i] = res.get(i);
        }

        return answer;
    }

    void comb(String str, int len, StringBuilder sb, int depth, int idx) {
        if (depth == len) {
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            return;
        }

        for (int i = idx; i < str.length(); i++) {
            sb.append(str.charAt(i));
            comb(str, len, sb, depth+1, i+1);
            sb.delete(depth, depth+1);
        }
    }
}
