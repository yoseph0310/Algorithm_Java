package Programmers.LV3;
import java.util.*;
/*
    MST, 크루스칼 사용
 */
public class 섬_연결하기 {
    class Solution {
        int[] parents;

        public int solution(int n, int[][] costs) {
            Arrays.sort(costs, (int[] c1, int[] c2) -> c1[2] - c2[2]);

            parents = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }

            int total = 0;
            for (int[] edge: costs) {
                int from = edge[0];
                int to = edge[1];
                int cost = edge[2];

                int fromParent = findParent(from);
                int toParent = findParent(to);

                if (fromParent == toParent) continue;

                total += cost;
                parents[toParent] = fromParent;
            }


            return total;
        }

        int findParent(int node) {
            if (parents[node] == node) return node;
            return parents[node] = findParent(parents[node]);
        }
    }
}
