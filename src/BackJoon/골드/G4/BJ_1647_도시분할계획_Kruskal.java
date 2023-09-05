package BackJoon.골드.G4;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1647_도시분할계획_Kruskal {

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

    static int V, E;
    static int[] parents;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parents = new int[V + 1];
        pq = new PriorityQueue<>();

        for (int i = 0; i <= V; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            pq.add(new Edge(s,e,weight));
        }

        int sum = 0;
        int maxWeight = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            int s = cur.s;
            int e = cur.e;
            int weight = cur.weight;

            if (find(s) != find(e)) {
                union(s, e);
                sum += weight;
                maxWeight = weight;
            }
        }

        System.out.println(sum - maxWeight);
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