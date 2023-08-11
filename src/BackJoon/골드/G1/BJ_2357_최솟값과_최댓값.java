package BackJoon.골드.G1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_2357_최솟값과_최댓값 {

    static final int INF = 1_000_000_000;
    static int N, M, a, b;
    static int[] arr;
    static int[] segTreeMax, segTreeMin;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        segTreeMax = new int[4 * (N + 1)];
        segTreeMin = new int[4 * (N + 1)];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        initMax(1, 1, N);
        initMin(1, 1, N);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            bw.write(findTreeMin(1, 1, N, a, b) + " ");
            bw.write(findTreeMax(1, 1, N, a, b) + "\n");
        }
        bw.flush();
        bw.close();
    }

    static int initMax(int node, int start, int end) {
        int mid = (start + end) / 2;

        if (start == end) {
            return segTreeMax[node] = arr[start];
        }
        else {
            return segTreeMax[node] = Math.max(initMax(node * 2, start, mid), initMax(node * 2 + 1, mid + 1, end));
        }
    }

    static int initMin(int node, int start, int end) {
        int mid = (start + end) / 2;

        if (start == end) {
            return segTreeMin[node] = arr[start];
        }
        else {
            return segTreeMin[node] = Math.min(initMin(node * 2, start, mid), initMin(node * 2 + 1, mid + 1, end));
        }
    }

    static int findTreeMax(int node, int start, int end, int left, int right) {
        if (left > end || start > right) return 0;
        else if (left <= start && end <= right) return segTreeMax[node];
        else {
            int mid = (start + end) / 2;
            return Math.max(findTreeMax(node * 2, start, mid, left, right), findTreeMax(node * 2 + 1, mid + 1, end, left, right));
        }

    }

    static int findTreeMin(int node, int start, int end, int left, int right) {
        if (left > end || start > right) return INF;
        else if (left <= start && end <= right) return segTreeMin[node];
        else {
            int mid = (start + end) / 2;
            return Math.min(findTreeMin(node * 2, start, mid, left, right), findTreeMin(node * 2 + 1, mid + 1, end, left, right));
        }
    }
}
