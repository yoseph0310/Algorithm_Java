package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1197_최소_스패닝_트리_Prim {

    static int V, E;
    static boolean[] visited;                           // 정점 방문 여부
    static PriorityQueue<Node> pq;                      // 우선순위큐
    static ArrayList<ArrayList<Node>> nodeList;         // 정점 정보 저장

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        visited = new boolean[V + 1];
        pq = new PriorityQueue<Node>();

        nodeList = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            nodeList.add(new ArrayList<>());
        }

        // 간선 정보 입력 (정점 연결리스트)
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodeList.get(v1).add(new Node(v2, weight));
            nodeList.get(v2).add(new Node(v1, weight));
        }

        pq.add(new Node(1, 0));

        int sum = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            int to = cur.to;
            int weight = cur.weight;

            // 이미 방문한 정점이라면 탐색하지 않는다.
            if (visited[to]) continue;

            visited[to] = true;
            sum += weight;

            // 연결되어 있는 방문하지 않은 정점을 큐에 추가
            for (Node next: nodeList.get(to)) {
                if (!visited[next.to]) pq.add(next);
            }
        }

        System.out.println(sum);
    }

    static class Node implements Comparable<Node> {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return weight - n.weight;
        }
    }
}
