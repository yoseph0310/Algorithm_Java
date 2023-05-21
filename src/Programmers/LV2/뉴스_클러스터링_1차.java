package Programmers.LV2;

import java.util.ArrayList;
import java.util.Collections;

public class 뉴스_클러스터링_1차 {
    public int solution(String str1, String str2) {
        ArrayList<String> A = new ArrayList<>();
        ArrayList<String> B = new ArrayList<>();
        ArrayList<String> AnB = new ArrayList<>();
        ArrayList<String> AuB = new ArrayList<>();

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        for (int i = 0; i < str1.length() - 1; i++) {
            char first = str1.charAt(i);
            char second = str1.charAt(i+1);

            if ('a' <= first && first <= 'z' && 'a' <= second && second <= 'z') {
                A.add(first+""+second);
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            char first = str2.charAt(i);
            char second = str2.charAt(i+1);

            if ('a' <= first && first <= 'z' && 'a' <= second && second <= 'z') {
                B.add(first+""+second);
            }
        }

        Collections.sort(A);
        Collections.sort(B);

        // fr ra an nc ce
        // fr re en nc ch
        for (String s : A) {
            if (B.remove(s)) {
                AnB.add(s);
            }
            AuB.add(s);
        }

        for (String s : B) {
            AuB.add(s);
        }

        double J = 0;

        if (AuB.size() == 0) {
            J = 1;
        } else {
            J = (double) AnB.size() / AuB.size();
        }

        return (int)(J*65536);
    }
}
