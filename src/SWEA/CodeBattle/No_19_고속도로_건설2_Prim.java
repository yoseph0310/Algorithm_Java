package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class No_19_고속도로_건설2_Prim {

    static int N, M;
    static boolean[] visited;
    static PriorityQueue<Node> pq;
    static ArrayList<ArrayList<Node>> nodeList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            visited = new boolean[N + 1];
            pq = new PriorityQueue<>();

            nodeList = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                nodeList.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                nodeList.get(s).add(new Node(e, c));
                nodeList.get(e).add(new Node(s, c));;
            }

            pq.add(new Node(1, 0));

            int ans = 0;
            while (!pq.isEmpty()) {
                Node cur = pq.poll();

                int to = cur.to;
                int c = cur.c;

                if (visited[to]) continue;

                visited[to] = true;
                ans += c;

                for (Node next: nodeList.get(to)) {
                    if (!visited[next.to]) pq.add(next);
                }
            }

            System.out.println("#" + t + " " + ans);
        }

    }

    static class Node implements Comparable<Node> {
        int to;
        int c;

        public Node(int to, int c) {
            this.to = to;
            this.c = c;
        }

        @Override
        public int compareTo(Node n) {
            return c - n.c;
        }
    }
}
