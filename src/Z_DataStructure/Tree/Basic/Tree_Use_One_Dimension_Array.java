package Z_DataStructure.Tree.Basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 루트가 1인 완전 이진 트리, 포화 이진 트리를 1차원 배열에 저장 후 각 인덱스의 부모 노드를 출력한다.
 * 입력 : 첫 번째 줄에 트리 노드 개수 n 이 주어진다.
 * 출력 : 각 인덱스의 부모 노드를 출력한다.
 *
 * 포화 이진 트리이거나 완전 이진 트리이기 때문에 1차원 배열 n+1 만큼 번호 순서대로 저장하면 된다.
 */

public class Tree_Use_One_Dimension_Array {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] parent = new int[N + 1];      // 트리 저장을 위한 1차원 배열.

        for (int i = 2; i <= N; i++) {      // 1은 루트이므로 2부터 시작 (tree[1] = 0)
            parent[i] = i / 2;              // 노드 i 의 부모 인덱스 = i / 2
        }

        System.out.println(Arrays.toString(parent));
    }
}
