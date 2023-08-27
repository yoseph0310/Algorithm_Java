package BackJoon.골드.G1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11505_구간_곱_구하기 {

    static long[] segTree, arr;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        segTree = new long[N * 4];
        arr = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        initTree(1, 1, N);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int q = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Integer.parseInt(st.nextToken());

            // update Tree
            if (q == 1) {
                arr[a] = b;
                updateTree(1, 1, N, a, b);
            }
            // find Mul
            else {
                sb.append(findMul(1, 1, N, a, (int)b)).append("\n");
            }
        }

        System.out.println(sb);
    }

    static long initTree(int node, int start, int end) {
        // 단말노드이면
        if (start == end) {
            return segTree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return segTree[node] = (initTree(node * 2, start, mid) * initTree(node * 2 + 1, mid + 1, end)) % MOD;
    }

    static void updateTree(int node, int left, int right, int idx, long value) {
        if (idx < left || right < idx) return;

        if (left != right) {
            int mid = (left + right) / 2;
            updateTree(node * 2, left, mid, idx, value);
            updateTree(node * 2 + 1, mid + 1, right, idx, value);
            segTree[node] = (segTree[node * 2] * segTree[node * 2 + 1]) % MOD;
        }
        else {
            segTree[node] = value;
        }
    }

    static long findMul(int node, int left, int right, int start, int end) {
        if (end < left || right < start) return 1;
        if (start <= left && right <= end) {
            return segTree[node];
        }

        int mid = (left + right) / 2;
        return (findMul(node * 2, left, mid, start, end) * findMul(node * 2 + 1, mid + 1, right, start, end)) % MOD;
    }

    static void print_arr(long[] a) {
        for (int i = 1; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
