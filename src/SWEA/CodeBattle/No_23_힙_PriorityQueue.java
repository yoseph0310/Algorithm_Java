package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class No_23_힙_PriorityQueue {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb = new StringBuilder();
            sb.append("#").append(t).append(" ");

            int N = Integer.parseInt(br.readLine());

            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int command = Integer.parseInt(st.nextToken());
                if (command == 1) {
                    int x = Integer.parseInt(st.nextToken());
                    pq.add(x);
                } else {
                    int p = 0;
                    if (pq.isEmpty()) p = -1;
                    else p = pq.poll();

                    sb.append(p).append(" ");
                }
            }

            System.out.println(sb);
        }
    }
}
