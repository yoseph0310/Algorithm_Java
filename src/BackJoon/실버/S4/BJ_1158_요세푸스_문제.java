package BackJoon.실버.S4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1158_요세푸스_문제 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Queue<Integer> q = new LinkedList<>();

        sb.append("<");

        for (int i = 1; i <= N; i++) {
            q.add(i);
        }

        int turn = 1;
        while(q.size() != 1) {

            if (turn % K == 0) {
                sb.append(q.poll()).append(", ");
            } else {
                q.add(q.poll());
            }
            turn++;
        }

        sb.append(q.poll()).append(">");

        System.out.println(sb);
    }

}
