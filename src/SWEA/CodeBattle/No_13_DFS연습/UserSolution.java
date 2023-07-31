package SWEA.CodeBattle.No_13_DFS연습;

public class UserSolution {

    static Node[] tree;

    /**
     * dfs 를 시작하기 위해 초기화 최초 한번만 실행됨
     *
     * @param N         : 인물 수 (2 <= N <= 40)
     * @param path      : 관계도 (path[i][0] : 부모,  path[i][1] : 자식
     */
    public void dfs_init(int N, int[][] path) {

        tree = new Node[101];
        for (int i = 0; i < 101; i++) {
            tree[i] = new Node(i);
        }

        for (int i = 0; i < N - 1; i++) {
            Node parent = tree[path[i][0]];
            Node child = tree[path[i][1]];

            parent.childList.add(child);
        }

    }

    public int dfs(int parent) {
        Node p = tree[parent];
        ArrayList childList = tree[parent].childList;

        int res = -1;

        if (childList.size() == 0) {
            return -1;
        } else {
            for (int i = 0; i < childList.size(); i++) {
                Node c = childList.get(i);

                res = DFS(parent, c.num);
                if (res != -1) break;
            }
        }
        return res;
    }

    private int DFS(int root, int child) {
        if (root < child) {
            return child;
        }

        int res = -1;

        if (tree[child].childList.size() == 0) {
            return -1;
        } else {
            for (int i = 0; i < tree[child].childList.size(); i++) {
                Node c = tree[child].childList.get(i);

                res = DFS(root, c.num);
                if (res != -1) break;
            }
        }

        return res;
    }

    static class Node {
        int num;
        ArrayList childList;

        public Node(int num) {
            this.num = num;
            this.childList = new ArrayList();
        }
    }

    static class ArrayList {
        private final int DEFAULT_CAPACITY = 10;
        private Node[] arr;
        private int size;

        public ArrayList() {
            this.arr = new Node[DEFAULT_CAPACITY];
            this.size = 0;
        }

        public void add(Node n) {
            if ((size + 1) == arr.length) {
                resize(arr.length * 2);
            }

            arr[size] = n;
            size++;
        }

        private void resize(int capacity) {
            Node[] newArr = new Node[capacity];

            for (int i = 0; i < size; i++) {
                newArr[i] = arr[i];
            }

            this.arr = null;
            this.arr = newArr;
        }

        public Node get(int num) {
            return arr[num];
        }

        public int size() {
            return size;
        }
    }
}