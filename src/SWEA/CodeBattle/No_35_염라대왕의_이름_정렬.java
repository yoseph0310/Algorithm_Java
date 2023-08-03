package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class No_35_염라대왕의_이름_정렬 {

    static int N;           // 1 <= N <= 20,000
    static HashSet<String> nameHS;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            nameHS = new HashSet<>();

            for (int i = 0; i < N; i++) {
                nameHS.add(br.readLine());
            }

            ArrayList<String> nameList = new ArrayList<>(nameHS);
            nameList.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (o1.length() == o2.length()) return o1.compareTo(o2);    // 길이가 같은 경우 사전순
                    return o1.length() - o2.length();                           // 길이가 짧은 순
                }
            });

            System.out.println("#" + t);
            for (String name: nameList) {
                System.out.println(name);
            }
        }
    }
}
