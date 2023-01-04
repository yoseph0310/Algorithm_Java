package BackJoon.실버.S1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_11729_하노이_탑_이동_순서 {

    static int moveCnt;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        hanoi(N, 1, 3, 2);
        System.out.println(moveCnt);
        System.out.println(sb);
    }

    static void hanoi(int n, int from, int to, int mid) {
        if (n == 0) return;

        hanoi(n-1, from, mid, to);
        moveCnt++;
        sb.append(from).append(" ").append(to).append('\n');
        hanoi(n-1, mid, to, from);
    }
}
