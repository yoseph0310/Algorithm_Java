package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    가중치를 최소로 하며 N 개의 모든 섬 연결 -> MST (Kruskal or Prim)

    인덱스로 정점을 구분해야함
    간선의 가중치(weight) - E * (dist ^ 2)
 */
public class No_18_하나로 {

    static int N;
    static double E;
    static int[] parents;
    static PriorityQueue<Edge> pq;

    static int[][] pos;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());            // 정점 개수

            pq = new PriorityQueue<>();
            pos = new int[N+1][2];           // 각 정점의 좌표를 받을 2차원 배열 0:x 1:y

            // 부모 노드 초기화
            parents = new int[N+1];
            for (int i = 1; i <= N; i++) {
                parents[i] = i;
            }

            // 정점의 좌표 입력 - 한줄은 X, 한줄은 Y로 각각 주어짐
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                pos[i][0] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                pos[i][1] = Integer.parseInt(st.nextToken());
            }

            E = Double.parseDouble(br.readLine());            // 환경 부담 세율

            // 간선을 모두 구해야함
            // 최소가 되어야 하므로 두 노드간 거리가 작은 간선들 부터 정렬하여 pq에 넣으면 됨
            for (int i = 1; i <= N; i++) {
                for (int j = i+1; j <= N; j++) {
                    long distX = Math.abs(pos[i][0] - pos[j][0]);
                    long distY = Math.abs(pos[i][1] - pos[j][1]);

                    pq.add(new Edge(i, j, distX * distX + distY * distY));
                }
            }

            long ans = 0;
            while (!pq.isEmpty()) {
                Edge cur = pq.poll();
                // 싸이클이 아니면
                if (find(cur.v1) != find(cur.v2)) {
                    union(cur.v1, cur.v2);
                    ans += cur.dist;
                }
            }

            System.out.println("#" + t + " " + Math.round(ans * E));
        }

    }

    static void union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);

        if (p1 < p2) parents[p2] = p1;
        else parents[p1] = p2;
    }

    static int find(int n) {
        if (parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }


    static class Edge implements Comparable<Edge> {
        int v1, v2;
        long dist;

        public Edge(int v1, int v2, long dist) {
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge e) {
            return Long.compare(this.dist, e.dist);
        }
    }
}
