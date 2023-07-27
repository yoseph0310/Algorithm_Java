package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    1. 최소 비용으로 모든 도시를 연결 -> MST
    2. 언덕등을 깎지 않아야 해서 어떤 도시끼리는 직접 도로를 이을 수 없다.
    3. 최소비용을 구해야한다. 최소신장트리의 모든 간선 비용을 구해야한다.

    정점의 개수 N : 2 <= N <= 50,000
    간선의 개수 M : 1 <= M <= 200,000

    s, e, c 와 같이 각각 (출발, 도착, 비용) 의 간선 정보가 주어진다.
 */
public class No_19_고속도로_건설2_Kruskal {

    static int N, M;
    static int[] parents;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            parents = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                parents[i] = i;
            }

            pq = new PriorityQueue<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                pq.add(new Edge(s, e, c));
            }

            int ans = 0;
            while (!pq.isEmpty()) {
                Edge cur = pq.poll();

                if (find(cur.s) != find(cur.e)) {
                    union(cur.s, cur.e);
                    ans += cur.c;
                }
            }

            System.out.println("#" + t + " " + ans);
        }
    }

    static void union(int idx1, int idx2) {
        int p1 = find(idx1);
        int p2 = find(idx2);

        if (p1 < p2) parents[p2] = p1;
        else parents[p1] = p2;
    }

    static int find(int idx) {
        if (parents[idx] == idx) return idx;
        return parents[idx] = find(parents[idx]);
    }

    static class Edge implements Comparable<Edge> {
        int s, e, c;

        public Edge(int s, int e, int c) {
            this.s = s;
            this.e = e;
            this.c = c;
        }

        @Override
        public int compareTo(Edge e) {
            return c - e.c;
        }
    }

}
