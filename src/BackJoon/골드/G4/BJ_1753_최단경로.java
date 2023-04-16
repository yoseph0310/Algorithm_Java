package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_1753_최단경로 {

    static final int INF = 11;
    static int V, E;
    static int start;

    static boolean[] visited;
    static int[] dist;
    static ArrayList<Node>[] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());

        visited = new boolean[V + 1];
        dist = new int[V + 1];
        board = new ArrayList[V + 1];

        // 최댓값으로 board 초기화
        for (int i = 0; i <= V; i++) {
            board[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            board[a].add(new Node(b, w));
        }

        // 거리 배열 초기화
        for (int i = 1; i <= V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        
        dist[start] = 0;

        for (int i = 0; i < V; i++) {
            int minIdx = 0;
            int minValue = Integer.MAX_VALUE;

            for (int j = 1; j <= V; j++) {
                if (!visited[j] && dist[j] < minValue) {
                    minIdx = j;
                    minValue = dist[j];
                }
            }
            visited[minIdx] = true;

            for (int j = 0; j < board[minIdx].size(); j++) {
                Node node = board[minIdx].get(j);

                if (dist[node.v] > dist[minIdx] + node.w) {
                    dist[node.v] = dist[minIdx] + node.w;
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (dist[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }

    static class Node {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
