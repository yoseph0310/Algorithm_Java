package BackJoon.골드.G1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2042_구간_합_구하기 {

    static long[] arr, tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // 2^h >= N 인 최소의 h를 구하는 것이 최적의 트리를 저장할 배열의 크기
        // 양변에 log 를 취하면
        // k >= logN / log2
        // (logN / log2) 의 값을 올림한 후 + 1을 하면 h 가 된다.
        // 위에서 구한 h를 제곱하면 세그먼트 트리의 size 를 구할 수 있다.

        int h = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        int size = (int) Math.pow(2, h);

        tree = new long[size];

        init(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                // b 번째 수를 c로 변경한다.
                // 차이는 변경된 값과 원래 값의 차를 넣어야 함
                long diff = c - arr[b];
                arr[b] = c;
                update(1, N, 1, b, diff);
            } else if (a == 2) {
                // b 번째 수 ~ c 번째 수 까지의 합을 구한다.
                sb.append(sum(1, N, 1, b, (int) c) + "\n");
            }
        }

        System.out.println(sb.toString());
    }

    static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;

        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    static void update(int start, int end, int node, int idx, long diff) {
        if (idx < start || end < idx) return;

        tree[node] += diff;

        // 단말 노드가 아니면 아래 노드들도 확인
        if (start != end) {
            int mid = (start + end) / 2;

            update(start, mid, node * 2, idx, diff);
            update(mid + 1, end, node * 2 + 1, idx, diff);
        }
    }

    static long sum(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return 0;

        // 범위 안에 완전히 포함되면 그대로 리턴
        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }
}
