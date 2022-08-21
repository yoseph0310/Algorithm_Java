package Programmers.LV1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class 정수_내림차순으로_배치하기 {
    public static void main(String[] args) {
        long n = 118372;
        System.out.println(solution(n));
    }

    public static long solution(long n){
        String str = Long.toString(n);
        List<Character> list = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            list.add(str.charAt(i));
        }
        list.sort(Comparator.reverseOrder());

        String tmpStr = "";
        for (int i = 0; i < list.size(); i++) {
            tmpStr += list.get(i);
        }
        long answer = Long.parseLong(tmpStr);
        return answer;
    }
}
