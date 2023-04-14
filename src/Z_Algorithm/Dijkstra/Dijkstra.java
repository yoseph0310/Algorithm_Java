package Z_Algorithm.Dijkstra;

import Z_DataStructure.Array.Array;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

public class Dijkstra {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // V : 노드, E : 간선
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 출발 노드
        int start = Integer.parseInt(br.readLine());

        // 1. 인접리스트를 이용한 그래프 초기화
        ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();

        // 입력값에서 노드의 번호는 1부터 시작. 따라서 0 번은 임의로 만들어 두기만 한다.
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프에 초기 입력값을 입력한다.
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            // a -> b 의 비용은 cost
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, cost));
        }

        // 2. 방문 여부를 확인할 boolean 배열, start ~ end 까지의 최소 거리를 저장할 배열을 만든다.
        boolean[] visited = new boolean[V + 1];
        int[] dist = new int[V + 1];

        // 3. 최소 거리 담을 배열을 매우 큰 값으로 초기화 한다. 상황에 따라 노드가 가질 수 있는 최댓값을 넣어도 된다.
        for (int i = 0; i <= V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        // 출발 지점은 다시 0으로 시작한다.
        dist[start] = 0;

        // 4. 다익스트라 알고리즘 진행
        // 모든 노드를 방문하면 종료. 노드 개수만큼 반복 진행
        for (int i = 0; i < V; i++) {
            // 4-1. 현재 거리 비용 중 최소 지점 선택
            int nodeIdx = 0;
            int nodeValue = Integer.MAX_VALUE;

            for (int j = 1; j <= V; j++) {
                if (!visited[j] && dist[j] < nodeValue) {
                    nodeValue = dist[j];
                    nodeIdx = j;
                }
            }

            visited[nodeIdx] = true;

            // 4-2. 해당 지점을 기준으로 인접 노드의 최소 거리 값 갱신
            for (int j = 0; j < graph.get(nodeIdx).size(); j++) {
                Node nextNode = graph.get(nodeIdx).get(j);

                // 현재 노드 값 + 현재 노드에서 인접 노드로 가는 값을 비교
                if (dist[nextNode.idx] > dist[nodeIdx] + nextNode.cost) {
                    dist[nextNode.idx] = dist[nodeIdx] + nextNode.cost;
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }

    }
}

class Node {
    int idx;
    int cost;

    public Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}
