package Z_DataStructure.Tree.Basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이차원 배열로 구현한 트리의 전위, 중위, 후위 순회 결과 출력
 * 2차원 배열 [x][0] -> 노드 x 의 왼쪽 자식
 *          [x][1] -> 노드 x 의 오른쪽 자식
 *
 *          위 방법대로 저장하면서 트리 입력을 받는다.
 *
 * 입력 : 첫째 줄에 트리의 노드 개수 n 이 주어진다. (1 <= n <= 100)
 *      둘째 줄부터 트리의 정보가 주어진다. 각 줄은 3개의 숫자 a, b, c 로 이뤄진다.
 *      (노드 a의 왼쪽 자식 b, 오른쪽 자식 c
 *      자식노드가 존재하지 않다면 -1이 주어진다.
 *
 * 출력 : 첫째 줄에 전위순회, 둘째 줄에 중위순회, 셋째 줄에 후위순회 결과 출력
 *
 * 6
 * 0 1 2
 * 1 3 4
 * 2 -1 5
 * 3 -1 -1
 * 4 -1 -1
 * 5 -1 -1
 */
public class Tree_Use_Two_Dimension_Array {
    static int N;
    static int[][] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            tree[a][0] = b;
            tree[a][1] = c;
        }

        System.out.println("전위 순회");
        preOrder(0);
        System.out.println();

        System.out.println("중위 순회");
        inOrder(0);
        System.out.println();

        System.out.println("후위 순회");
        postOrder(0);
        System.out.println();
    }

    // 전위순회 preOrder : Root -> Left -> Right
    public static void preOrder(int x) {
        // 자식 노드가 모두 없다면 순회를 멈춘다.
        if (tree[x][0] == -1 && tree[x][1] == -1) {
            System.out.print(x + " ");
        } else {
            System.out.print(x + " ");
            if (tree[x][0] != -1) {
                preOrder(tree[x][0]);
            }
            if (tree[x][1] != -1) {
                preOrder(tree[x][1]);
            }
        }

    }

    // 중위순회 inOrder : Left -> Root -> Right
    public static void inOrder(int x) {
        if (tree[x][0] == -1 && tree[x][1] == -1) {
            System.out.print(x + " ");
        } else {
            if (tree[x][0] != -1) {
                inOrder(tree[x][0]);
            }
            System.out.print(x + " ");
            if (tree[x][1] != -1) {
                inOrder(tree[x][1]);
            }
        }
    }

    // 후위순회 postOrder : Left -> Right -> Root
    public static void postOrder(int x) {
        if (tree[x][0] == -1 && tree[x][1] == -1) {
            System.out.print(x + " ");
        } else {
            if (tree[x][0] != -1) {
                postOrder(tree[x][0]);
            }
            if (tree[x][1] != -1) {
                postOrder(tree[x][1]);
            }
            System.out.print(x + " ");
        }
    }

}
