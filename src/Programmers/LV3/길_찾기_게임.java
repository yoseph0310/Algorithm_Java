package Programmers.LV3;
import java.util.*;

public class 길_찾기_게임 {
    class Solution {

        class Node implements Comparable<Node> {
            int x, y, idx;
            Node left;
            Node right;

            public Node(int x, int y, int idx, Node left, Node right) {
                this.x = x;
                this.y = y;
                this.idx = idx;
                this.left = left;
                this.right = right;
            }

            @Override
            public int compareTo(Node n) {
                if (n.y == this.y) return this.x - n.x;
                else return n.y - this.y;
            }
        }

        int[][] answer;
        int idx;

        public int[][] solution(int[][] nodeinfo) {

            Node[] node = new Node[nodeinfo.length];
            for (int i = 0; i < nodeinfo.length; i++) {
                node[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i+1, null, null);
            }

            // 레벨 내림차순, x 값 오름차순
            Arrays.sort(node);

            // 트리 구성
            Node root = node[0];
            for (int i = 1; i < node.length; i++) {
                insertNode(root, node[i]);
            }

            answer = new int[2][nodeinfo.length];
            idx = 0;
            preOrder(root);
            idx = 0;
            postOrder(root);

            return answer;
        }

        void insertNode(Node parent, Node child) {
            // child.x 값이 parent.x 보다 작으면 왼쪽자식
            if (child.x < parent.x) {
                if (parent.left == null) parent.left = child;
                else insertNode(parent.left, child);
            } else {
                if (parent.right == null) parent.right = child;
                else insertNode(parent.right, child);
            }
        }

        void preOrder(Node root) {
            if (root != null) {
                answer[0][idx++] = root.idx;
                preOrder(root.left);
                preOrder(root.right);
            }
        }

        void postOrder(Node root) {
            if (root != null) {
                postOrder(root.left);
                postOrder(root.right);
                answer[1][idx++] = root.idx;
            }
        }
    }
}
