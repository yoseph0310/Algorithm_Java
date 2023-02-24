package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_17140_이차원_배열과_연산 {

    static int R, C, K;
    static int[][] A = new int[101][101];
    static int xLength = 3;
    static int yLength = 3;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());
    }

    static int solve() {
        for (int time = 0; time <= 100; time++) {
            if (A[R][C] == K) {
                return time;
            }
            operating();
        }
        return -1;
    }

    static void operating() {
        if (xLength >= yLength) {
            for (int i = 1; i <= xLength; i++) {
                R(i);
            }
        } else {
            for (int i = 1; i <= yLength; i++) {
                C(i);
            }
        }
    }

    static void R(int key) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 1; i <= yLength; i++) {
            if (A[key][i] == 0) continue;
            map.compute(A[key][i], (num, cnt) -> cnt == null ? 1 : cnt + 1);
        }

        map.forEach((k, v) -> pq.add(new Pair(k, v)));

        int i = 1;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            A[key][i++] = p.number;
            A[key][i++] = p.cnt;
        }

        yLength = Math.max(yLength, i);

        while (i <= 99) {
            A[key][i++] = 0;
            A[key][i++] = 0;
        }
    }

    static void C(int key) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 1; i <= xLength; i++) {
            if (A[i][key] == 0) continue;
            map.compute(A[i][key], (num, cnt) -> cnt == null ? 1 : cnt + 1);
        }

        map.forEach((k, v) -> pq.add(new Pair(k, v)));

        int i = 1;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            A[i++][key] = p.number;
            A[i++][key] = p.cnt;
        }

        xLength = Math.max(xLength, i);

        while (i <= 99) {
            A[i++][key] = 0;
            A[i++][key] = 0;
        }
    }

    static class Pair implements Comparable<Pair> {
        int number, cnt;

        public Pair(int number, int cnt) {
            this.number = number;
            this.cnt = cnt;
        }

        public int compareTo(Pair o) {
            if (this.cnt > o.cnt) {
                return 1;
            } else if (this.cnt == o.cnt) {
                return this.number - o.number;
            } else {
                return -1;
            }
        }
    }
}
