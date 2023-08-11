package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class No_45_SegmentTree_연습_1 {

    static final int INF = 1_000_000_000;
    static int N, Q;
    static int[] arr;
    static int[] segTreeMax, segTreeMin;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            Q = Integer.parseInt(st.nextToken());

            arr = new int[N + 1];
            segTreeMax = new int[4 * (N + 1)];
            segTreeMin = new int[4 * (N + 1)];

            // 배열 값 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            initMax(1, 1, N);
            initMin(1, 1, N);

            bw.write("#" + t + " ");
            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());

                int option = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken()) + 1;
                int b = Integer.parseInt(st.nextToken());

                // 0 일 때는 a 를 b 로 바꾼다.
                if (option == 0) {
                    arr[a] = b;
                    updateMax(1, 1, N, a, b);
                    updateMin(1, 1, N, a, b);

                }
                // 1 일 때는 최댓값, 최솟값을 구해서 그 차를 구한다.
                else if (option == 1) {
                    int min = findMin(1, 1, N, a, b);
                    int max = findMax(1, 1, N, a, b);

                    bw.write((max - min) + " ");
                }
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    static int initMax(int node, int start, int end) {
        int mid = (start + end) / 2;

        if (start == end) {
            return segTreeMax[node] = arr[start];
        } else {
            return segTreeMax[node] = Math.max(initMax(node * 2, start, mid), initMax(node * 2 + 1, mid + 1, end));
        }
    }

    static int initMin(int node, int start, int end) {
        int mid = (start + end) / 2;

        if (start == end) {
            return segTreeMin[node] = arr[start];
        } else {
            return segTreeMin[node] = Math.min(initMin(node * 2, start, mid), initMin(node * 2 + 1, mid + 1, end));
        }
    }

    static void updateMax(int node, int start, int end, int idx, int diff) {
        if (idx < start || end < idx) return;

        if (start != end) {
            int mid = (start + end) / 2;

            updateMax(node * 2, start, mid, idx, diff);
            updateMax(node * 2 + 1, mid + 1, end, idx, diff);
            segTreeMax[node] = Math.max(segTreeMax[node * 2], segTreeMax[node * 2 + 1]);
        } else {
            segTreeMax[node] = diff;
        }
    }

    static void updateMin(int node, int start, int end, int idx, int diff) {
        if (idx < start || end < idx) return;
        // 자식 노드가 있을 때 두 자식 값중 최대최솟값 계산
        if (start != end) {
            int mid = (start + end) / 2;

            updateMin(node * 2, start, mid, idx, diff);
            updateMin(node * 2 + 1, mid + 1, end, idx, diff);
            segTreeMin[node] = Math.min(segTreeMin[node * 2], segTreeMin[node * 2 + 1]);
        }
        // 단일노드면 바로 업데이트
        else {
            segTreeMin[node] = diff;
        }
    }

    static int findMax(int node, int start, int end, int left, int right) {
        if (left > end || start > right) return 0;
        else if (left <= start && end <= right) {
            return segTreeMax[node];
        }
        else {
            int mid = (start + end) / 2;
            return Math.max(findMax(node * 2, start, mid, left, right), findMax(node * 2 + 1, mid + 1, end, left, right));
        }
    }

    static int findMin(int node, int start, int end, int left, int right) {
        if (left > end || start > right) return INF;
        else if (left <= start && end <= right) {
            return segTreeMin[node];
        }
        else {
            int mid = (start + end) / 2;
            return Math.min(findMin(node * 2, start, mid, left, right), findMin(node * 2 + 1, mid + 1, end, left, right));
        }
    }
}
