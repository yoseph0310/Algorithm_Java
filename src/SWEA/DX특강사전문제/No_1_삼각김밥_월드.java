package SWEA.DX특강사전문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1
9861 10000

1
9860 10000
 */
public class No_1_삼각김밥_월드 {

    static final int MAX_NODE = 10_011;

    static int ans, s, e;
    static Node[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            ans = Integer.MAX_VALUE;

            tree = new Node[MAX_NODE + 1];

            // 트리 구성
            mkTree();

            int c = 0;
            for (int i = 1; i <= MAX_NODE; i++) {
                if (tree[i].level == 141) {
                    System.out.println(tree[i].idx);

                }
            }


            // start 가 end 보다 작으면
            if (s < e) {
                solve(s, e, 0, new boolean[MAX_NODE + 1]);
            }
            // start 가 end 보다 크면
            else {
                solve(e, s, 0, new boolean[MAX_NODE + 1]);
            }

            System.out.println("#" + t + " " + ans);
        }

    }

    static void solve(int idx, int end, int cnt, boolean[] visited) {

        visited[idx] = true;

        // idx 가 end 에 도착하면 정답 반환
        if (idx == end) {
            ans = Math.min(ans, cnt);
            return;
        }

        // idx 와 end 의 level 이 같은 층일 때
        if (tree[idx].level == tree[end].level) {
            // 양쪽을 확인해서 왼쪽인지 오른쪽인지 확인
            // 찾으려는 end 가 idx 기준 왼쪽이면
            int diff;

            if (idx > end) diff = idx - end;
            else diff = end - idx;

            ans = Math.min(ans, cnt + diff);

            return;
        }

        // 아래 층으로 이동해야 함
        if (idx + tree[idx].level <= MAX_NODE && !visited[idx + tree[idx].level]){
            solve(idx + tree[idx].level, end, cnt + 1, visited);
        }
        if (idx + tree[idx].level + 1 <= MAX_NODE && !visited[idx + tree[idx].level + 1]) {
            solve(idx + tree[idx].level + 1, end, cnt + 1, visited);
        }
    }

    static void mkTree() {
        int level = 1;
        int cnt = 0;

        for (int i = 1; i <= MAX_NODE; i++) {
            tree[i] = new Node(i, level);

            cnt++;

            if (cnt == level) {
                level++;
                cnt = 0;
            }
        }
    }

    static class Node {
        int idx, level;

        public Node(int idx, int level) {
            this.idx = idx;
            this.level = level;
        }
    }
}
