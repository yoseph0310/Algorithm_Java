package Z_DataStructure.Tree.Basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 클래스로 구현한 이진 트리의 전, 중, 후 순휘 결과 출력
 * Class 에 data, left, right 값을 저장하여 구현
 *
 * 6
 * 0 1 2
 * 1 3 4
 * 2 -1 5
 * 3 -1 -1
 * 4 -1 -1
 * 5 -1 -1
 *
 */
public class Tree_Use_Class {
    public Node root;       // 초기 root 는 Null;

    public void createNode(int data, int leftData, int rightData) {
        // 초기 상태 - 루트 노드 생성
        if (root == null) {
            root = new Node(data);

            // 좌우 값이 있는 경우, 즉 -1이 아닌 경우 노드 생성
            if (leftData != -1) {
                root.left = new Node(leftData);
            }
            if (rightData != -1) {
                root.right = new Node(rightData);
            }
        }
        // 초기 상태가 아니라면, 루트 노드 생성 이후 만들어진 노드 중 어떤 것인지 찾는다.
        else {
            searchNode(root, data, leftData, rightData);
        }
    }

    public void searchNode(Node node, int data, int leftData, int rightData) {
        if (node == null) return;       // 도착한 노드가 Null 이면 재귀 종료 - 찾을 노드 X

        // 들어갈 위치를 찾았다면
        else if (node.data == data) {
            if (leftData != -1) {       // 값이 있는 경우에만 좌우 노드 생성
                node.left = new Node(leftData);
            }
            if (rightData != -1) {
                node.right = new Node(rightData);
            }
        }
        // 아직 찾지 못하고 탐색할 노드가 더 남았다면
        else {
            searchNode(node.left, data, leftData, rightData);
            searchNode(node.right, data, leftData, rightData);
        }
    }

    // 전위 : Root -> Left -> Right
    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            if (node.left != null) preOrder(node.left);
            if (node.right != null) preOrder(node.right);
        }
    }

    // 중위 : Left -> Root -> Right
    public void inOrder(Node node) {
        if (node != null) {
            if (node.left != null) inOrder(node.left);
            System.out.print(node.data + " ");
            if (node.right != null) inOrder(node.right);
        }
    }

    // 후위 : Left -> Right -> Root
    public void postOrder(Node node) {
        if (node != null) {
            if (node.left != null) inOrder(node.left);
            if (node.right != null) inOrder(node.right);
            System.out.print(node.data + " ");
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Tree_Use_Class tree = new Tree_Use_Class();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            tree.createNode(a, b, c);
        }

        System.out.println("전위 순회");
        tree.preOrder(tree.root);
        System.out.println();

        System.out.println("중위 순회");
        tree.inOrder(tree.root);
        System.out.println();

        System.out.println("후위 순회");
        tree.postOrder(tree.root);
        System.out.println();
    }
}
