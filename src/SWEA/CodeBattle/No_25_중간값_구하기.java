package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *  N : 경근이가 처음에 쓴 자연수 A  (1 <= N <= 200,000)
 *  N 개의 줄에 걸쳐 X, Y 가 주어진다.  (1 <= X <= 10^9,  1<= Y <= 10^9)
 */

public class No_25_중간값_구하기 {

    static final int MOD = 20_171_109;
    static int N, A, ans;
    static PriorityQueue<Integer> pq;
    static PriorityQueue<Integer> tmp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            // 중간 값을 기준으로 한 최소 힙 -> 항상 왼쪽에 하나 더 많도록 유지
            PriorityQueue<Integer> leftPQ = new PriorityQueue<>(Collections.reverseOrder());        // 내림차순
            PriorityQueue<Integer> rightPQ = new PriorityQueue<>();                                 // 오름차순
            leftPQ.add(A);

            ans = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                // X, Y 둘다 중간값보다 작은 경우
                if (X < leftPQ.peek() && Y < leftPQ.peek()) {
                    leftPQ.add(X);
                    leftPQ.add(Y);

                    rightPQ.add(leftPQ.poll());
                }
                // X, Y 둘다 중간값보다 큰 경우
                else if (X > leftPQ.peek() && Y > leftPQ.peek()) {
                    rightPQ.add(X);
                    rightPQ.add(Y);

                    leftPQ.add(rightPQ.poll());
                }
                else {
                    if (X < Y) {
                        leftPQ.add(X);
                        rightPQ.add(Y);
                    }
                    else {
                        leftPQ.add(Y);
                        rightPQ.add(X);
                    }
                }

                ans = (ans + leftPQ.peek()) % MOD;
            }

            System.out.println("#" + t + " " + (ans % MOD));
        }
    }


}
