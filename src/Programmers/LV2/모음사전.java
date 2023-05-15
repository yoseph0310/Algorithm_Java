package Programmers.LV2;
import java.util.*;

public class 모음사전 {
    String[] arr;
    List<String> list;

    public int solution(String word) {
        int answer = 0;

        arr = new String[]{"A", "E", "I", "O", "U"};
        list = new ArrayList<>();

        recur(word, "", 0);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(word)) {
                answer = i;
                break;
            }
        }

        return answer;
    }

    void recur(String word, String str, int depth) {
        list.add(str);

        if (depth == 5) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            recur(word, str+arr[i], depth + 1);
        }
    }
}
