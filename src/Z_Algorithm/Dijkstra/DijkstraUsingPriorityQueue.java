package Z_Algorithm.Dijkstra;

import Z_DataStructure.PriorityQueue.PriorityQueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
sample input
5 6
1
5 1 1
1 2 2
1 3 3
2 3 4
2 4 5
3 4 6
 */
public class DijkstraUsingPriorityQueue {

    static int V, E, start;
    static ArrayList<ArrayList<Node>> graph;

    static class Node {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        graph = new ArrayList<ArrayList<Node>>();

        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<Node>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // s -> e 의 단방향이다.
            graph.get(s).add(new Node(e, c));
        }

        // 거리 배열 초기화
        int[] dist = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        // 다익스트라 알고리즘의 최소 비용을 기준으로 추출해야 한다.
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

        // start -> start 로 가는 것이 초기에는 최소 비용이다.
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            // pq 에서 꺼내진 노드는 현재 최소 비용을 갖는 노드이다.
            Node curNode = pq.poll();

            /**
             *  현재 노드의 비용이 기록된 비용보다 크다면 더 이상 확인할 필요가 없다.
             *  이 조건이 누락된다면 중복 방문이 일어나게 되어 완전그래프의 경우 시간복잡도가 E^2에 수렴할 가능성이 커진다.
             */
            if (dist[curNode.idx] < curNode.cost) {
                continue;
            }

            for (int i = 0; i < graph.get(curNode.idx).size(); i++) {
                Node nextNode = graph.get(curNode.idx).get(i);

                if (dist[nextNode.idx] > curNode.cost + nextNode.cost) {
                    dist[nextNode.idx] = curNode.cost + nextNode.cost;
                    pq.offer(new Node(nextNode.idx, nextNode.cost));
                }
            }

        }

        System.out.println(Arrays.toString(dist));
    }
}
