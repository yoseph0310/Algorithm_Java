package BackJoon.실버.S4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_10816_HashMap {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> hm = new HashMap<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int key = Integer.parseInt(st.nextToken());

            hm.put(key, hm.getOrDefault(key, 0) + 1);
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int key = Integer.parseInt(st.nextToken());

            bw.write(hm.getOrDefault(key, 0) + " ");
        }
        bw.flush();
        bw.close();
    }
}
