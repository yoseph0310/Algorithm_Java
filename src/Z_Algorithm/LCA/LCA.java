package Z_Algorithm.LCA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
sample input

15
0 1
0 2
0 3
1 4
4 9
3 5
3 11
3 6
3 7
5 10
7 12
7 13
7 8
8 14
3
4 3
10 7
12 14

 */
public class LCA {
    static ArrayList<Integer>[] tree;
    static int[][] parent;
    static int[] deep;
    static boolean[] visit;

    static int N;
    static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        parent = new int[N][21];
        deep = new int[N];
        visit = new boolean[N];

        int loop = N - 1;
        while (loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        dfs(0, 0);
        connect();

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            sb.append(LCA(node1, node2)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    static int LCA(int node1, int node2) {
        if (deep[node1] > deep[node2]) {
            int tmp = node1;
            node1 = node2;
            node2 = tmp;
        }

        for (int i = 20; i >= 0; i--) {
            int jump = 1 << i;
            if (deep[node2] - deep[node1] >= jump) node2 = parent[node2][i];
        }

        if (node1 == node2) return node1;

        for (int i = 20; i >= 0; i--) {
            if (parent[node1][i] == parent[node2][i]) continue;

            node1 = parent[node1][i];
            node2 = parent[node2][i];
        }

        return parent[node1][0];
    }

    static void dfs(int current, int depth) {
        visit[current] = true;
        deep[current] = depth;

        for (int next : tree[current]) {
            if (visit[next]) continue;

            parent[next][0] = current;
            dfs(next, depth + 1);
        }
    }

    static void connect() {
        for (int p = 1; p < 21; p++) {
            for (int cur = 0; cur < N; cur++) {
                parent[cur][p] = parent[parent[cur][p-1]][p - 1];
            }
        }
    }

    static void print_parent() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 21; j++) {
                System.out.print(parent[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void print_tree_info() {
        for (int i = 0; i < tree.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < tree[i].size(); j++) {
                System.out.print(tree[i].get(j) + " ");
            }
            System.out.println();
        }
    }
}
