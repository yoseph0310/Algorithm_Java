package Programmers.LV2;
import java.util.*;

public class 튜플 {
    public ArrayList<Integer> solution(String s) {
        ArrayList<Integer> answer = new ArrayList<>();
        s = s.substring(2, s.length() - 2);
        s = s.replace("},{", " ");


        String[] s_arr = s.split(" ");
        Arrays.sort(s_arr, new Comparator<String>(){
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        for (String num : s_arr) {
            String[] tmp = num.split(",");

            for (int i = 0; i < tmp.length; i++) {
                int n = Integer.parseInt(tmp[i]);

                if (!answer.contains(n)) {
                    answer.add(n);
                }
            }
        }


        return answer;
    }
}
