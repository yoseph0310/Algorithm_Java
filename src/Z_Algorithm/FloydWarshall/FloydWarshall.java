package Z_Algorithm.FloydWarshall;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 플로이드 워샬 알고리즘
 *
 * 음수 사이클이 없는 그래프 내의 각 모든 정점에서 각 모든 정점에 까지의 최단거리를 모두 구할 수 있는 알고리즘이다.
 * 다이나믹 프로그래밍 기법을 사용한 알고리즘이고 인접 행렬을 이용하여 각 노드간 최소 비용을 계산한다.
 * 모든 노드에서 모든 노드로 가는 최소 비용을 단계적으로 갱신하면서 진행되는 알고리즘이다.
 */
public class FloydWarshall {
    static int N, M;
    static int[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                    continue;
                }

                dist[i][j] = 100000000;
            }
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            dist[a][b] = Math.min(dist[a][b], cost);
            dist[b][a] = Math.min(dist[b][a], cost);
        }

        // 노드를 1개부터 N개까지 거쳐가는 경우를 모두 고려
        for (int k = 0; k < N; k++) {
            // 노드 i 에서 j 로 가는 경우
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // k 번째 노드를 거쳐 가는 비용이 기존 비용보다 더 작은 경우 갱신
                    // 또는 연결이 안되어 있던 연결 비용 갱신
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dist[i][j] == 100000000) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
