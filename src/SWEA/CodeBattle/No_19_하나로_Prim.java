package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class No_19_하나로_Prim {

    static int N;
    static double E;
    static int[][] pos;
    static boolean[] visited;
    static PriorityQueue<Node> pq;
    static ArrayList<ArrayList<Node>> nodeList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());

            visited = new boolean[N + 1];
            pos = new int[N + 1][2];
            pq = new PriorityQueue<>();

            nodeList = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                nodeList.add(new ArrayList<>());
            }

            // x
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                pos[i][0] = Integer.parseInt(st.nextToken());
            }
            // y
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                pos[i][1] = Integer.parseInt(st.nextToken());
            }

            E = Double.parseDouble(br.readLine());

            for (int i = 1; i <= N; i++) {
                for (int j = i+1; j <= N; j++) {
                    long distX = Math.abs(pos[i][0] - pos[j][0]);
                    long distY = Math.abs(pos[i][1] - pos[j][1]);
                    long c = distX * distX + distY * distY;

                    nodeList.get(i).add(new Node(j, c));
                    nodeList.get(j).add(new Node(i, c));
                }
            }

            pq.add(new Node(1, 0));

            long ans = 0;
            while (!pq.isEmpty()) {
                Node cur = pq.poll();

                int to = cur.to;
                long w = cur.w;

                if (visited[to]) continue;

                visited[to] = true;
                ans += w;

                for (Node next: nodeList.get(to)) {
                    if (!visited[next.to]) pq.add(next);
                }
            }

            System.out.println("#" + t + " " + Math.round(ans * E));
        }
    }

    static class Node implements Comparable<Node> {
        int to;
        long w;

        public Node(int to, long w) {
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node n) {
            return Long.compare(w, n.w);
        }
    }
}
