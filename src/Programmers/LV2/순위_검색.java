package Programmers.LV2;
import java.util.*;

public class 순위_검색 {
    Map<String, List<Integer>> map;

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        map = new HashMap<String, List<Integer>>();

        for (int i = 0; i < info.length; i++) {
            String[] in = info[i].split(" ");
            makeSentence(in, "", 0);
        }

        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        for (int i = 0; i < query.length; i++) {
            query[i] = query[i].replaceAll(" and ", "");
            String[] q = query[i].split(" ");

            answer[i] = map.containsKey(q[0]) ? binarySearch(q[0], Integer.parseInt(q[1])) : 0;
        }

        return answer;
    }

    int binarySearch(String key, int score) {
        List<Integer> list = map.get(key);

        int start = 0, end = list.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (list.get(mid) < score) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return list.size() - start;
    }

    void makeSentence(String[] in, String str, int cnt) {
        if (cnt == 4) {
            if (!map.containsKey(str)) {
                List<Integer> list = new ArrayList<>();
                map.put(str, list);
            }
            map.get(str).add(Integer.parseInt(in[4]));
            return;
        }

        makeSentence(in, str+"-", cnt+1);
        makeSentence(in, str+in[cnt], cnt+1);
    }
}
