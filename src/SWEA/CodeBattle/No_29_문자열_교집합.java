package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class No_29_문자열_교집합 {

    static int N, M, ans;
    static HashMap<String, Integer> hm;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            ans = 0;

            hm = new HashMap<>();

            String data = "";
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                data = st.nextToken();
                hm.put(data, hm.getOrDefault(data, -1) + 1);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                data = st.nextToken();
                hm.put(data, hm.getOrDefault(data, -1) + 1);
            }

            for (String key: hm.keySet()) {
                if (hm.get(key) > 0) ans++;
            }

            System.out.println("#" + t + " " + ans);
        }
    }
}
