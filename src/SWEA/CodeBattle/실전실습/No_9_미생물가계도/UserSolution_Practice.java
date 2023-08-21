package SWEA.CodeBattle.실전실습.No_9_미생물가계도;

import java.util.HashMap;

public class UserSolution_Practice {

    static final int MAX_PARENT_SIZE = 14;
    static final int MAX_DAY = 1_048_576;

    static class Node {
        String name;
        int depth;
        Node[] parents;

        public Node() {
            this.parents = new Node[MAX_PARENT_SIZE];
        }

        public void init(String name, Node parentNode) {
            this.name = name;

            for (int i = 0; i < MAX_PARENT_SIZE; i++) {
                this.parents[i] = new Node();
            }

            if (parentNode != null) {
                this.parents[0] = parentNode;
                this.depth = this.parents[0].depth + 1;

                for (int i = 1; i < MAX_PARENT_SIZE; i++) {
                    if (this.parents[i - 1].name == null) break;
                    else this.parents[i] = this.parents[i - 1].parents[i - 1];
                }
            }
        }
    }

    static HashMap<String, Node> treeHM;

    static int[] pow;           // [MAX_PARENT_SIZE]
    static int[] segTree;       // [MAX_DAY * 2];
    static int Order;

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

    void init(char[] mAncestor, int mLastDay) {
        Order = 0;
//        System.out.println(++Order + ". init()");

        String key_mAncestor = charToString(mAncestor);

        // 트리 해시맵 구성
        treeHM = new HashMap<>();

        Node ancestorNode = new Node();
        ancestorNode.init(key_mAncestor, null);

        treeHM.put(key_mAncestor, ancestorNode);

        // 세그먼트 트리 구성
        segTree = new int[MAX_DAY * 2];
        segTreeAdd(1, 0, MAX_DAY - 1, 0, mLastDay);

        // pow 배열 구성
        pow = new int[MAX_PARENT_SIZE];
        pow[0] = 1;
        for (int i = 1; i < MAX_PARENT_SIZE; i++) {
            pow[i] = pow[i-1] * 2;
        }
    }

    int add(char[] mName, char[] mParent, int mFirstDay, int mLastDay) {
//        System.out.println(++Order + ". add()");

        String key_mName = charToString(mName);
        String key_mParent = charToString(mParent);

        // 부모 노드 (앞서 존재하는 것이 보장됨)
        Node parentNode = treeHM.get(key_mParent);

        // 새로 추가될 노드
        Node newNode = new Node();
        newNode.init(key_mName, parentNode);

        // 해시맵 트리에 새로 추가된 노드 정보 기록
        treeHM.put(key_mName, newNode);

        // 세그먼트 트리에 새 노드 생존 범위 입력
        segTreeAdd(1, 0, MAX_DAY - 1, mFirstDay, mLastDay);

//        System.out.println("  " + newNode.depth);
        return newNode.depth;
    }

    int distance(char[] mName1, char[] mName2) {
//        System.out.println(++Order + ". distance()");

        String key_mName1 = charToString(mName1);
        String key_mName2 = charToString(mName2);

        Node node1 = treeHM.get(key_mName1);
        Node node2 = treeHM.get(key_mName2);

        if (node1.depth < node2.depth) {
            Node tmp = node1;
            node1 = node2;
            node2 = tmp;
        }

        int dist = 0;
        for (int i = MAX_PARENT_SIZE - 1; i >= 0; i--) {
            if (node1.depth - node2.depth >= pow[i]) {
                dist += pow[i];
                node1 = node1.parents[i];
            }
        }

        for (int i = MAX_PARENT_SIZE - 1; i >= 0; i--) {
            if (node1.parents[i].name == null || node2.parents[i].name == null) continue;

            if (node1.parents[i] != node2.parents[i]) {
                dist += pow[i] * 2;
                node1 = node1.parents[i];
                node2 = node2.parents[i];
            }
        }

//        System.out.println("  " + (dist + (node1 != node2 ? 2 : 0)));
        return dist + (node1 != node2 ? 2 : 0);
    }

    int count(int mDay) {
//        System.out.println(++Order + ". count()");

        int cnt = 0;
        int idx = mDay + MAX_DAY;
        while (idx > 0) {
            cnt += segTree[idx];
            idx /= 2;
        }

//        System.out.println("  " + cnt);
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
}
