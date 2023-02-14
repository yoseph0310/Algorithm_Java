package BackJoon.실버;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_2485_가로수 {

    static int[] gaps;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] trees = new int[N];
        gaps = new int[N - 1];

        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < N - 1; i++) {
            gaps[i] = trees[i + 1] - trees[i];
        }

        for (int i = 0; i <= gaps.length - 2; i++) {
            gaps[i + 1] = GCD(gaps[i], gaps[i + 1]);
        }

        int gap = gaps[gaps.length - 1];

        System.out.println((trees[N - 1] - trees[0]) / gap - (N - 1));

    }

    static int GCD(int a, int b) {
        if (b == 0) return a;
        else return GCD(b, a%b);
    }
}
