package Programmers.LV2;

import java.util.ArrayList;
import java.util.Collections;

public class 최댓값과최솟값 {
    public static void main(String[] args) {
        String s = "1 2 3 4";

        System.out.println(solution(s));
    }

    public static String solution(String s){
        String answer = "";
        ArrayList<Integer> arr = new ArrayList<>();
        String[] str = s.split(" ");

        for (int i = 0; i < str.length; i++) {
            arr.add(Integer.parseInt(str[i]));
        }

        answer = Collections.min(arr) + " " + Collections.max(arr);

        return answer;
    }
}
