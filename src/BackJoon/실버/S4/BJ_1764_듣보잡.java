package BackJoon.실버.S4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ_1764_듣보잡 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            if (set.contains(s)){
                res.add(s);
            }
        }
        Collections.sort(res);

        System.out.println(res.size());
        for (String s: res) {
            System.out.println(s);
        }
    }
}
