package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class No_17_영준이의_진짜_BFS {

    static int N;           // N의 최대 범위 10^5 -> 10만, 2의 17승이면 충분하나 넉넉하게 18을 최대로.
    static long res;

    static int[] depths;
    static int[][] parents;
    static ArrayList<ArrayList<Integer>> adjustList;
    static List<Integer> searchList;

    static final int MAX_DEPTH = 18;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        searchList = new ArrayList<>();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            sb.append('#').append(t).append(' ');
            init();

            st = new StringTokenizer(br.readLine());
            // 1 번 노드를 제외한 모든 노드들은 첫번째 인덱스의 자기 자신의 부모 노드가 저장되고
            // 나머지는 자신의 자식노드들이다.
            for (int i = 2; i <= N; i++) {
                int nodeNum = Integer.parseInt(st.nextToken());
                adjustList.get(i).add(nodeNum);
                adjustList.get(nodeNum).add(i);
            }


            BFS(1);
            setParent();

            int size = searchList.size();
            for (int i = 0; i < size - 1; i++) {
                int node1 = searchList.get(i);
                int node2 = searchList.get(i + 1);
                int sameParent = LCA(node1, node2);

                res += depths[node1] - depths[sameParent];
                res += depths[node2] - depths[sameParent];

            }

            sb.append(res).append('\n');
        }

        System.out.println(sb.toString());
    }

    static int LCA(int node1, int node2) {
        if (depths[node1] > depths[node2]) {
            int tmp = node1;
            node1 = node2;
            node2 = tmp;
        }

        for (int i = MAX_DEPTH; i >= 0; i--) {
            long diff = depths[node2] - depths[node1];
            if (diff >= (1L << i)) {
                node2 = parents[node2][i];
            }
        }

        if (node1 == node2) return node1;

        for (int i = MAX_DEPTH; i >= 0; i--) {
            if (parents[node2][i] != parents[node1][i]) {
                node1 = parents[node1][i];
                node2 = parents[node2][i];
            }
        }

        return parents[node2][0];
    }

    // BFS 를 통해 정보를 얻는다. (DFS 로도 가능)
    // 1번 노드부터 BFS 를 통해서 각 노드의 자식 노들을 순회하면서
    // 각 노드들의 부모 정보와 깊이를 저장한다.
    static void BFS(int vertex) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        q.add(vertex);

        while (!q.isEmpty()) {
            int pollNode = q.poll();
            visited[pollNode] = true;
            searchList.add(pollNode);

            // 뽑힌 노드의 자식노드 개수 만큼 진행
            int size = adjustList.get(pollNode).size();
            for (int i = 0; i < size; i++) {
                int child = adjustList.get(pollNode).get(i);

                if (visited[child]) continue;

                // 자식 노드의 첫번째 부모노드 저장
                parents[child][0] = pollNode;
                depths[child] = depths[pollNode] + 1;
                q.add(child);
            }
        }
    }

    static void setParent() {
        for (int p = 1; p <= MAX_DEPTH; p++) {
            for (int cur = 1; cur <= N; cur++) {
                parents[cur][p] = parents[parents[cur][p-1]][p-1];
            }
        }

    }

    static void init() {
        parents = new int[N + 1][MAX_DEPTH + 1];
        depths = new int[N + 1];
        res = 0;
        adjustList = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            adjustList.add(new ArrayList<>());
        }
        searchList.clear();
    }

}
