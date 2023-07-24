package SWEA.CodeBattle.No_13_DFS연습;

public class UserSolution {

    static int[][] p;
    static int e;

    static Tree tree;

    public void dfs_init(int E, int[][] path) {
        e = E;          // 주어진 관계 수
        p = path;

        tree = new Tree(E);

        int idx = 0;
        System.out.println();
        System.out.println("TREE SIZE :: " + tree.size());
        for (int i = 1; i < e; i++) {
//            System.out.println(path[i-1][0] + " " + path[i-1][1]);
            int parentV = path[i-1][0];
            int childV = path[i-1][1];

            int parentIdx = idx++;
            int childIdx = idx++;

            // 주어진 부모노드값이 트리에 없으면 추가
            if (!tree.contains(parentV)) {
                tree.add(parentIdx, parentV);

            }

            // 주어진 자식노드값이 트리에 없으면 추가
            if (!tree.contains(childV)) {
                tree.add(childIdx, childV);
            }

//            tree.printTreeInfo();

            System.out.println();
            System.out.println("parent INFO  :: [" + parentIdx + "] : " + parentV);
            System.out.println("child INFO  :: [" + childIdx + "] : " + childV);
            System.out.println("AFTER ADD TREE SIZE :: " + tree.size());
        }


    }

    public int dfs(int K) {
        // N 은 인덱스가 아니라 왕위 계승을 할 왕
        // N 에서 시작해서 전위 순회를 하면서 처음으로 N 보다 큰 값을 반환하도록 해야함

        // 자식노드를 구해서 반환해야 하므로 parent 의 idx 가 필요하다.

        return 0;
    }

    class Node {
        int idx, data;                   // 자신의 인덱스와 데이터
        Node[] child_arr;      // 자신의 자식 노드들

        public Node() {}

        public Node(int idx, int data) {
            this.idx = idx;
            this.data = data;
            this.child_arr = new Node[5];        // 자식노드는 최대 5개
        }
    }

    // Tree 의 Node[] 는 여러 노드 자체를 담기 위함.
    class Tree {
        Node[] arr;
        int idx;

        public Tree(int size) {
            this.arr = new Node[size];
            for (int i = 0; i < size; i++) {
                this.arr[i] = new Node();
            }

            this.idx = 0;
        }

        // Tree 안에 이미 등록된 노드 (부모이거나 자식으로써 이미 등록된 노드) 인지 찾는 메소드
        public boolean contains(int value) {
            for (Node node: this.arr) {
                if (node.data == value) return true;
            }
            return false;
        }

        // 노드 추가 메소드 : this.arr 에 value 가 인덱스 1씩 늘려서 삽입되고
        public void add(int idx, int value) {
            Node newNode = new Node(idx, value);

            arr[idx] = newNode;
        }

        public int size() {
            return idx;
        }

        public void printTreeInfo() {
            for (Node node: this.arr) {
                System.out.println("[" + node.idx + "]" + " : " + node.data);
                for (Node child: node.child_arr) {
                    System.out.print(node.data + " 's child" + child.data);
                }
                System.out.println();
            }
        }
    }

    public void printTree() {
        System.out.println();
        for (int i = 0; i < e - 1; i++) {
            System.out.println("부모 노드 값 : " + p[i][0] + " - 자식 노드 값 : " + p[i][1]);
        }
    }
}