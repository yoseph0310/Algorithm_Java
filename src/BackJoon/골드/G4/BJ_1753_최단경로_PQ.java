package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1753_최단경로_PQ {

    static final int INF = Integer.MAX_VALUE;
    static int V, E, start;

    static boolean[] visited;
    static int[] dist;

    static ArrayList<ArrayList<Node>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());

        graph = new ArrayList<ArrayList<Node>>();
        visited = new boolean[V + 1];
        dist = new int[V + 1];

        Arrays.fill(dist, INF);

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<Node>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, w));
        }

        dijkstra(start);

        for (int i = 1; i <= V; i++) {
            if (dist[i] == INF) bw.write("INF" + '\n');
            else bw.write(dist[i] + "\n");
        }

        bw.flush();
        bw.close();
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.v]) continue;
            visited[cur.v] = true;

            for (Node node : graph.get(cur.v)) {
                if (dist[node.v] > dist[cur.v] + node.w) {
                    dist[node.v] = dist[cur.v] + node.w;
                    pq.offer(new Node(node.v, dist[node.v]));
                }
            }
        }

    }

    static class Node implements Comparable<Node>{
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public int compareTo(Node o) {
            return w - o.w;
        }
    }
}
