package BackJoon.골드.G3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_2406_안정적인_네트워크 {

    static class Edge implements Comparable<Edge> {
        int s, e, weight;

        public Edge(int s, int e, int weight) {
            this.s = s;
            this.e = e;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }
    }

    static int V, M;
    static int[] parents;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        V = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[V + 1];
        pq = new PriorityQueue<>();

        for (int i = 0; i <= V; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (find(x) == find(y)) continue;

            union(x, y);
        }

        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= V; j++) {
                int x = Integer.parseInt(st.nextToken());

                if (i == 1 || j == 1 || i == j) continue;

                pq.add(new Edge(i, j, x));
            }
        }

        int X = 0;      // 최소 비용
        int K = 0;      // 연결할 컴퓨터 쌍의 개수
        List<Edge> list = new ArrayList<>();

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            int s = cur.s;
            int e = cur.e;
            int weight = cur.weight;

            if (find(s) != find(e)) {
                union(s, e);
                X += weight;
                K++;
                list.add(cur);
            }
        }

        sb.append(X).append(" ").append(K).append('\n');
        for (Edge e: list) {
            sb.append(e.s).append(" ").append(e.e).append('\n');
        }

        System.out.println(sb);
    }

    static void union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);

        if (p1 < p2) parents[p2] = p1;
        else parents[p1] = p2;
    }

    static int find(int node) {
        if (parents[node] == node) return node;
        else return parents[node] = find(parents[node]);
    }
}
