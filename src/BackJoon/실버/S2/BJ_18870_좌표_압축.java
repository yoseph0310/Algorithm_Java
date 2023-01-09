package BackJoon.실버.S2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_18870_좌표_압축 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] origin = new int[N];
        int[] sorted = new int[N];
        HashMap<Integer, Integer> hm = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sorted[i] = origin[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sorted);

        int rank = 0;
        for (int v: sorted) {
            if(!hm.containsKey(v)) {
                hm.put(v, rank);
                rank++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int key: origin) {
            int ranking = hm.get(key);
            sb.append(ranking).append(' ');
        }

        System.out.println(sb);
    }
}
