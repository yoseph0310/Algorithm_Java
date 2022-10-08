package BackJoon.실버.S4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class BJ_1302_베스트셀러 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        HashMap<String, Integer> hm = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String title = br.readLine();
            if (hm.containsKey(title)){
                hm.replace(title, hm.get(title)+1);
            } else {
                hm.put(title, 1);
            }
        }

        int max = 0;
        for (String title: hm.keySet()) {
            max = Math.max(max, hm.get(title));
        }

        ArrayList<String> titleList = new ArrayList<>(hm.keySet());
        Collections.sort(titleList);
        for (String title: titleList) {
            if (hm.get(title) == max){
                System.out.println(title);
                break;
            }
        }
    }
}
