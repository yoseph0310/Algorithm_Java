package SWEA.CodeBattle.실전실습.No_9_미생물가계도;

import java.util.HashMap;

public class UserSolution_Practice {

    static final int MAX_DEPTH = 14;
    static final int MAX_DAYS = 1_048_576;

    static int[] segTree;
    static int[] pow;

    static HashMap<String, Node> nodeHM;
    static String key_mAncestor;

    static int Order;

    void init(char[] mAncestor, int mLastDay) {
        Order = 0;

        // 세그 트리 초기화
        segTree = new int[2 * MAX_DAYS];

        // 2의 제곱수 배열 초기화
        pow = new int[MAX_DAYS];
        pow[0] = 1;
        for (int i = 1; i < MAX_DAYS; i++) {
            pow[i] = pow[i - 1] * 2;
        }

        // 노드 해시맵 초기화
        nodeHM = new HashMap<>();

        // 선조 정보 추가
        key_mAncestor = charToString(mAncestor);
        Node node = new Node();
        node.init(key_mAncestor, null);
        nodeHM.put(key_mAncestor, node);

        segTreeAdd(1, 0, MAX_DAYS - 1, 0, mLastDay);
    }

    void segTreeAdd(int node, int left, int right, int start, int end) {
        if (end < left || right < start) return;
        if (start <= left && right <= end) {
            segTree[node] += 1;
            return;
        }

        int mid = (left + right) / 2;
        segTreeAdd(node * 2, left, mid, start, end);
        segTreeAdd(node * 2 + 1, mid + 1, right, start, end);
    }

    int add(char[] mName, char[] mParent, int mFirstDay, int mLastDay) {

        String key_newNode = charToString(mName);
        String key_parentNode = charToString(mParent);

        // 부모 노드 정보
        Node parentNode = nodeHM.get(key_parentNode);

        // 새 노드 정보 기록
        Node childNode = new Node();
        childNode.init(key_newNode, parentNode);

        nodeHM.put(key_newNode, childNode);
        segTreeAdd(1, 0, MAX_DAYS - 1, mFirstDay, mLastDay);

        return childNode.depth;
    }

    int distance(char[] mName1, char[] mName2) {
        String key_node1 = charToString(mName1);
        String key_node2 = charToString(mName2);

        int dist = 0;
        Node node1 = nodeHM.get(key_node1);
        Node node2 = nodeHM.get(key_node2);

        // 두 노드 깊이를 비교하여 무조건 큰것을 1로 옮겨둔다.
        // 이는 깊이가 깊은 노드를 찾는 것을 의미한다.
        if (node1.depth < node2.depth) {
            Node tmp = node1;
            node1 = node2;
            node2 = tmp;
        }

        // 두 노드의 깊이가 같아 질때 까지 깊이가 더 깊은 노드를 끌어올린다.
        // 같아지게 되면 멈춘다.
        for (int i = MAX_DEPTH - 1; i >= 0; i--) {
            if (node1.depth - node2.depth >= pow[i]) {
                dist += pow[i];
                node1 = node1.parents[i];
            }
        }

        // 이제 두 노드의 부모가 모두 같아질 때까지 반복한다.
        for (int i = MAX_DEPTH - 1; i >= 0; i--) {
            if (node1.parents[i].name == null && node2.parents[i].name == null) continue;

            if (!node1.parents[i].name.equals(node2.parents[i].name)) {
                dist += pow[i] * 2;
                node1 = node1.parents[i];
                node2 = node2.parents[i];
            }
        }

        return dist + (node1 != node2 ? 2 : 0);
    }

    int count(int mDay) {
        int cnt = 0;
        int idx = mDay + MAX_DAYS;
        while (idx > 0) {
            cnt += segTree[idx];
            idx /= 2;
        }

        return cnt;
    }

    String charToString(char[] a) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < a.length; i++) {
            if (a[i] == '\0') break;
            sb.append(a[i]);
        }

        return sb.toString();
    }

    /**
     * 트리의 루트 노드인 Ancestor 는 부모 배열을 지니고는 있지만 null 은 아니다.
     * 따라서 탐색할때 부모리스트의 이름이 없는 것으로 판단해야한다.
     */
    static class Node {
        String name;
        int depth;
        Node[] parents;

        public Node() {
            this.parents = new Node[MAX_DEPTH];
        }

        public void init(String name, Node parent) {
            this.name = name;

            for (int i = 0; i < MAX_DEPTH; i++) {
                parents[i] = new Node();
            }

            if (parent != null) {
                this.parents[0] = parent;
                for (int i = 1; i < MAX_DEPTH; i++) {
                    if (parents[i - 1].name == null) break;
                    this.parents[i] = this.parents[i - 1].parents[i - 1];
                }

                this.depth = this.parents[0].depth + 1;
            }


        }

    }
}
