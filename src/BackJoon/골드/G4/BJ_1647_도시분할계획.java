package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1647_도시분할계획 {

    static class Node implements Comparable<Node> {
        int to, weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return this.weight - n.weight;
        }
    }

    static int V, E;
    static boolean[] visited;
    static PriorityQueue<Node> pq;
    static ArrayList<ArrayList<Node>> nodeList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        visited = new boolean[V + 1];
        pq = new PriorityQueue<>();
        nodeList = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            nodeList.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodeList.get(s).add(new Node(e, weight));
            nodeList.get(e).add(new Node(s, weight));
        }

        pq.add(new Node(1, 0));

        int sum = 0;
        int maxWeight = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            int to = cur.to;
            int weight = cur.weight;

            if (visited[to]) continue;

            visited[to] = true;
            sum += weight;
            maxWeight = Math.max(maxWeight, weight);

            for (Node next: nodeList.get(to)) {
                if (!visited[next.to]) pq.add(next);
            }
        }

        System.out.println(sum - maxWeight);
    }
}
