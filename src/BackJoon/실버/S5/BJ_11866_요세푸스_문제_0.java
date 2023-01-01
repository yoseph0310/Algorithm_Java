package BackJoon.실버.S5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_11866_요세푸스_문제_0 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        sb.append("<");

        int turn = 0;

        while (N > 1) {
            turn = (turn + (K - 1)) % N;

            sb.append(list.remove(turn)).append(", ");
            N--;
        }

        sb.append(list.remove()).append(">");
        System.out.println(sb);
    }
}
