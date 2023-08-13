package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class No_43_K번째_문자열 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            String input = br.readLine();

            Set<String> set = new HashSet<>();

            for (int i = 0; i < input.length(); i++) {
                for (int j = i; j < input.length() + 1; j++) {
                    set.add(input.substring(i, j));
                }
            }

            String[] strList = new String[set.size()];

            int cnt = 0;
            for (String s: set) {
                strList[cnt++] = s;
            }
            Arrays.sort(strList);

            System.out.println("#" + t + " " + strList[N]);
        }
    }
}
