package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 트리 직접 구현 코드
 */
public class No_10_사칙연산_트리직접구현 {
    static int N;
    static Tree tree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 1; t <= 10; t++) {
            N = Integer.parseInt(br.readLine());
            tree = new Tree();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int node = Integer.parseInt(st.nextToken());
                String data = st.nextToken();

                int leftIdx = 0;
                int rightIdx = 0;

                if (st.countTokens() == 2) {
                    leftIdx = Integer.parseInt(st.nextToken());
                    rightIdx = Integer.parseInt(st.nextToken());
                }

                tree.add(node, data, leftIdx, rightIdx);
            }

            System.out.println("#" + t + " " + (int)tree.inOrder(tree.root));
        }
    }

    static class Node {
        int idx;
        String data;
        Node left;
        Node right;

        public Node(int idx) {
            this.idx = idx;
            this.data = "";
            left = null;
            right = null;
        }
    }

    static class Tree {
        Node root = null;

        public void add(int idx, String data, int leftIdx, int rightIdx) {
            // 트리 최초 생성
            if (root == null) {
                root = new Node(idx);
                root.data = data;
                if (leftIdx != 0) root.left = new Node(leftIdx);
                if (rightIdx != 0) root.right = new Node(rightIdx);
            } else {
                search(root, idx, data, leftIdx, rightIdx);
            }
        }

        public void search(Node root, int idx, String data, int leftIdx, int rightIdx) {
            // 찾았을 때 데이터 저장 및 노드 연결
            if (root.idx == idx) {
                root.data = data;
                if (leftIdx != 0) root.left = new Node(leftIdx);
                if (rightIdx != 0) root.right = new Node(rightIdx);
            } else {
                if (root.left != null) search(root.left, idx, data, leftIdx, rightIdx);
                if (root.right != null) search(root.right, idx, data, leftIdx, rightIdx);
            }
        }

        // left -> root -> right
        public double inOrder(Node root) {
            String cur = root.data;
            double leftV = 0;
            double rightV = 0;
            double res = 0;

            if (root.left.left == null) {
                leftV = Double.parseDouble(root.left.data);
            } else {
                leftV = inOrder(root.left);
            }

            if (root.right.right == null) {
                rightV = Double.parseDouble(root.right.data);
            } else {
                rightV = inOrder(root.right);
            }

            switch (cur) {
                case "+":
                    res = leftV + rightV;
                    break;
                case "-":
                    res = leftV - rightV;
                    break;
                case "*":
                    res = leftV * rightV;
                    break;
                case "/":
                    res = leftV / rightV;
                    break;
            }

            return res;
        }
    }
}
