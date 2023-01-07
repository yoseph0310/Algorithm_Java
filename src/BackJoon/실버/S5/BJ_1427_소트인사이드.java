package BackJoon.실버.S5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BJ_1427_소트인사이드 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String N = br.readLine();

        ArrayList<Character> numList = new ArrayList<>();

        for (int i = 0; i < N.length(); i++) {
            char num = N.charAt(i);
            numList.add(num);
        }

        Collections.sort(numList, Collections.reverseOrder());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numList.size(); i++) {
            sb.append(numList.get(i));
        }

        System.out.println(sb);
    }
}
