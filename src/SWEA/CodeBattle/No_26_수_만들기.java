package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *  N 개의 수 A1 ~ AN 과 K 가 주어진다.
 *
 *  초기 X = 0, D = 1 -> X 를 K 로 만들어야 한다.
 *
 *  D 에 수를 몇번 곱하는지는 상관없이 X 에 D 를 더하여 K 가 되는 최소 횟수를 구하라
 */
public class No_26_수_만들기 {

    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            K = Integer.parseInt(br.readLine());

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(K, 0));

            int ans = 0;
            while (!pq.isEmpty()) {
                Node cur = pq.poll();

                if (cur.left == 0) {
                    ans = cur.cnt;
                    break;
                }

                pq.add(new Node(0, cur.cnt + cur.left));
                for (int i = 0; i < N; i++) {
                    pq.add(new Node(cur.left / arr[i], cur.cnt + cur.left % arr[i]));
                }
            }

            System.out.println("#" + t + " " + ans);
        }
    }

    static class Node implements Comparable<Node> {
        int left, cnt;

        public Node(int left, int cnt) {
            this.left = left;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node n) {
            return this.cnt - n.cnt;
        }
    }
}
