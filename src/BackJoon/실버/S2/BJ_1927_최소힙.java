package BackJoon.실버.S2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ_1927_최소힙 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) {
                sb.append(pq.size() == 0 ? 0 : pq.poll()).append('\n');
            } else {
                pq.add(n);
            }
        }

        System.out.println(sb);
    }
}
