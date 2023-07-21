package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 트리를 노드 클래스로 구성하면서 기존처럼 idx(root), idx * 2(left), idx * 2 + 1 (right) 로 접근하지 않은 이유
 *
 * 노드 개수 입력값인 N의 최대 크기를 1000인 배열로 트리를 구성했다.
 * 기존처럼 idx 를 통한 트리 접근도 가능하겠지만 입력값으로 제한했기 때문에 1000보다 인덱스가 더 큰 노드를 접근하게 된다.
 * 이는 NullPointerException 을 야기한다.
 *
 * 그러므로 idx 로 접근하려면 배열을 더 큰 사이즈로 할당해야한다.
 *
 * 하지만 입력값에서 친절히 node 번호를 제공하고 있기 때문에 굳이 더 많은 노드를 살펴볼 필요 없이
 * 입력단계에서 각 노드들의 left, right 등의 정보를 입력 받고 진행하는 것이 더 효율적이다.
 */
public class No_10_사칙연산_클래스_사용 {

    static Node[] node = new Node[1001];
    static int N, ans;

    static String operations = "+-/*";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 1; t <= 10; t++) {
            N = Integer.parseInt(br.readLine());

            for (int i = 1; i <= N; i++) {
                node[i] = new Node();
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int idx = Integer.parseInt(st.nextToken());
                String data = st.nextToken();

                // 연산자이면
                if (operations.contains(data)) {
                    node[idx].op = data;
                    node[idx].left = Integer.parseInt(st.nextToken());
                    node[idx].right = Integer.parseInt(st.nextToken());
                } else {
                    node[idx].data = Integer.parseInt(data);
                }
            }

            System.out.println("#" + t + " " + postOrder(1));
        }
    }

    static int postOrder(int idx) {
        String op = node[idx].op;

        if (op != null) {
            switch (op) {
                case "+":
                    ans = postOrder(node[idx].left) + postOrder(node[idx].right);
                    break;
                case "-":
                    ans = postOrder(node[idx].left) - postOrder(node[idx].right);
                    break;
                case "*":
                    ans = postOrder(node[idx].left) * postOrder(node[idx].right);
                    break;
                case "/":
                    ans = postOrder(node[idx].left) / postOrder(node[idx].right);
                    break;
            }
        } else {
            ans = node[idx].data;
        }

        return ans;
    }

    static class Node {
        int data;
        int left;
        int right;
        String op;
    }
}
