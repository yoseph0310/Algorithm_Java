package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BJ_7662_이중우선순위큐 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> que = new TreeMap<>();

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int n = Integer.parseInt(st.nextToken());

                if (command.equals("I")){
                    que.put(n, que.getOrDefault(n, 0) + 1);
                } else {
                    if (que.size() == 0) continue;

                    int num = n == 1 ? que.lastKey() : que.firstKey();
                    if (que.put(num, que.get(num) - 1) == 1){
                        que.remove(num);
                    }
                }
            }
            System.out.println(que.size() == 0 ? "EMPTY" : que.lastKey() + " " + que.firstKey());

        }
    }
}
