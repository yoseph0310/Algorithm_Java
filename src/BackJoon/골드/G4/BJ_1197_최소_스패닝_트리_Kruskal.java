package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1197_최소_스패닝_트리_Kruskal {

    static int V, E;
    static int[] parent;
    static PriorityQueue<Edge> pq;      // 간선 정보 저장

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());           // 정점 개수
        E = Integer.parseInt(st.nextToken());           // 간선 개수

        parent = new int[V + 1];
        pq = new PriorityQueue<>();

        // 부모 노드 세팅
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        // 간선 정보 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(v1, v2, weight));
        }

        // 사이클 확인 (union-find)
        int weight = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();       // 가중치가 가장 작은 간선 순대로

            // 부모노드가 다를때만 (사이클 X)
            if (find(cur.v1) != find(cur.v2)) {
                union(cur.v1, cur.v2);
                weight += cur.weight;
            }
        }

        System.out.println(weight);
    }

    // 조합
    static void union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);

        if (p1 < p2) parent[p2] = p1;
        else parent[p1] = p2;
    }

    // 부모 노드 찾기
    static int find(int n) {
        if (parent[n] == n) return n;
        return parent[n] = find(parent[n]);
    }

    static class Edge implements Comparable<Edge> {
        int v1;
        int v2;
        int weight;

        public Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return weight - e.weight;
        }
    }
}
