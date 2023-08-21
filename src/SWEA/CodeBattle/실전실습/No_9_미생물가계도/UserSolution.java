package SWEA.CodeBattle.실전실습.No_9_미생물가계도;

import java.util.HashMap;

class UserSolution {

    static final int MAX_PARENT_LENGTH = 14;        // add 호출 횟수 12.000 -> 2^14 (약 12,000)
    static final int MAX_DAY = 1_048_576;           // mDay 범위 1,000,000. 가장 근접한 2의 제곱수 -> 2 ^ 20 (약 1,048,576)

    static class Node {
        int depth;
        String name;
        Node[] parent;

        public Node() {
            this.parent = new Node[MAX_PARENT_LENGTH];
        }

        public void init() {
            for (int i = 0; i < MAX_PARENT_LENGTH; i++) {
                parent[i] = new Node();
            }
        }
    }

    int[] power;
    HashMap<String, Node> treeHM;

    int N;
    int[] segTree;

    void segTreeAdd(int node, int left, int right, int start, int end) {
        if (right < start || end < left) return;
        if (start <= left && right <= end) {
            segTree[node] += 1;
            return;
        }

        int mid = (left + right) / 2;
        segTreeAdd(node * 2, left, mid, start, end);
        segTreeAdd(node * 2 + 1, mid + 1, right, start, end);
    }

    /**
     * 각 테스트 케이스의 처음에 호출된다.
     * 기존의 기록들은 모두 삭제된다.
     * mAncestor 는 선조의 이름이며, 선조의 생존 기간의 시작은 항상 0이다.
     *
     * @param mAncestor         : 선조의 이름 ( 3 ≤ |mAncestor| ≤ 11, |A|는 A 문자열의 길이를 의미한다 )
     * @param mLastDay         : 선조의 마지막 생존일 ( 0 ≤ mLastDay ≤ 1,000,000 )
     */
    void init(char[] mAncestor, int mLastDay) {
        String str_mAncestor = charToString(mAncestor);

        treeHM = new HashMap<>();

        Node node = new Node();
        node.init();
        node.name = str_mAncestor;
        treeHM.put(str_mAncestor, node);

        power = new int[MAX_PARENT_LENGTH];

        power[0] = 1;
        for (int i = 1; i < MAX_PARENT_LENGTH; i++) {
            power[i] = power[i - 1] * 2;
        }

        segTree = new int[MAX_DAY * 2];
        for (int i = 0; i < MAX_DAY * 2; i++) {
            segTree[i] = 0;
        }

        segTreeAdd(1,0, MAX_DAY - 1, 0, mLastDay);
    }

    /**
     * 이름이 mName 인 새로운 미생물 개체의 정보가 추가된다. 기존에 중복된 이름이 없다는 것이 보장된다.
     * 부모의 이름은 mParent 이고, 개체의 생존 기간의 시작은 mFirstDay, 마지막은 mLastDay 이다.
     * 이름이 mParent 인 개체가 앞서 입력된 기록에 존재한다.
     * 추가된 개체와 선조의 가계도 거리를 반환한다.
     *
     * @param mName             : 개체의 이름 ( 3 ≤ |mName| ≤ 11 )
     * @param mParent           : 개체의 부모의 이름 ( 3 ≤ |mParent| ≤ 11 )
     * @param mBirthday         : 개체의 첫 생존일 ( 부모의 FirstDay ≤ mFirstDay ≤ 부모의 LastDay )
     * @param mLastDay          : 개체의 마지막 생존일 ( mFirstDay ≤ mLastDay ≤ 1,000,000 )
     *
     * @return                  : 추가된 개체와 선조의 가계도 거리를 반환한다.
     */
    int add(char[] mName, char[] mParent, int mBirthday, int mLastDay) {
        String str_mName = charToString(mName);
        String str_mParent = charToString(mParent);

        Node node;
        if (!treeHM.containsKey(str_mName)) {
            node = new Node();
            treeHM.put(str_mName, node);
            node.init();
            node.name = str_mName;
        } else {
            node = treeHM.get(str_mName);
        }

        // 부모 노드는 있는 것이 보장됨
        Node parent = treeHM.get(str_mParent);

        node.parent[0] = parent;
        node.depth = node.parent[0].depth + 1;

        for (int i = 1; i < MAX_PARENT_LENGTH; i++) {
            if (node.parent[i - 1].name == null) break;
            else {
                node.parent[i] = node.parent[i - 1].parent[i - 1];
            }

        }

        segTreeAdd(1, 0, MAX_DAY - 1, mBirthday, mLastDay);

        return node.depth;
    }

    /**
     * 이름이 mName1인 개체와 mName2인 개체 간의 가계도 거리를 반환한다.
     * 이름이 mName1, mName2인 개체가 앞서 입력된 기록에 존재한다.
     *
     * @param mName1            : 가계도 거리를 알아내려는 두 개체의 이름 ( 3 ≤ |mName1|, |mName2| ≤ 11 )
     * @param mName2            : 가계도 거리를 알아내려는 두 개체의 이름 ( 3 ≤ |mName1|, |mName2| ≤ 11 )
     *
     * @return                  : 두 개체 간의 가계도 거리를 반환한다.
     */
    int distance(char[] mName1, char[] mName2) {

        String str_mName1 = charToString(mName1);
        String str_mName2 = charToString(mName2);

        int dist = 0;
        Node node1 = treeHM.get(str_mName1);
        Node node2 = treeHM.get(str_mName2);

        if (node1.depth < node2.depth) {
            Node tmp = node1;
            node1 = node2;
            node2 = tmp;
        }

        for (int i = MAX_PARENT_LENGTH - 1; i >= 0 ; i--) {
            if (node1.depth - node2.depth >= power[i]) {
                dist += power[i];
                node1 = node1.parent[i];
            }
        }

        for (int i = MAX_PARENT_LENGTH - 1; i >= 0 ; i--) {
            if (node1.parent[i].name == null || node2.parent[i].name == null) continue;

            if (!node1.parent[i].name.equals(node2.parent[i].name)) {
                dist += power[i] * 2;
                node1 = node1.parent[i];
                node2 = node2.parent[i];
            }
        }

        return dist + (node1 != node2 ? 2 : 0);
    }

    /**
     * 지금까지 기록된 미생물 중 생존 기간에 mDay 일이 포함되는 개체 수를 반환한다.
     *
     * @param mDay              : 생존한 개체 수를 조사하는 날짜 ( 0 ≤ mDay ≤ 1,000,000 )
     *
     * @return                  : 생존 기간에 mDay 일이 포함되는 개체 수를 반환한다.
     */
    int count(int mDay) {
        int idx = mDay + MAX_DAY;
        int cnt = 0;
        while (idx > 0) {
            cnt += segTree[idx];
            idx /= 2;
        }

        return cnt;
    }

    static String charToString(char[] a) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < a.length; i++) {
            if (a[i] == '\0') break;
            sb.append(a[i]);
        }

        return sb.toString();
    }
}