package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_11_공통조상 {

    static int V, E, a, b, size;
    static Node[] tree;
    static boolean[] visited;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            tree = new Node[V + 1];
            visited = new boolean[V + 1];
            for (int i = 1; i <= V; i++) {
                tree[i] = new Node();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                if (tree[parent].leftChildIdx == 0) tree[parent].leftChildIdx = child;
                else tree[parent].rightChildIdx = child;

                tree[child].parentIdx = parent;
            }

            int commonParent = 1;
            while (true) {
                if (a != 1) {
                    int parent = tree[a].parentIdx;
                    if (visited[parent]) {
                        commonParent = parent;
                        break;
                    }

                    visited[parent] = true;
                    a = parent;
                }

                if (b != 1) {
                    int parent = tree[b].parentIdx;
                    if (visited[parent]) {
                        commonParent = parent;
                        break;
                    }

                    visited[parent] = true;
                    b = parent;
                }
            }

            size = 0;
            get(tree[commonParent]);
            System.out.println("#" + t + " " + commonParent + " " + size);
        }
    }

    // 공통 부모를 루트로 하는 "서브 트리"의 모든 노드의 개수를 세나가므로 전위 순회를 통해 구한다.
    static void get(Node node) {
        size++;
        if (node.leftChildIdx != 0) get(tree[node.leftChildIdx]);
        if (node.rightChildIdx != 0) get(tree[node.rightChildIdx]);
    }

    static class Node {
        int parentIdx, leftChildIdx, rightChildIdx;
    }
}
