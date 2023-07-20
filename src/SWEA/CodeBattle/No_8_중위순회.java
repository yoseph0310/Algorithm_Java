package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_8_중위순회 {

    static int N;
    static String[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 1; t <= 10; t++) {

            N = Integer.parseInt(br.readLine());
            tree = new String[N+1];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int node = Integer.parseInt(st.nextToken());
                String data = st.nextToken();

                tree[node] = data;
            }

            System.out.print("#" + t + " ");
            inOrder(1);
            System.out.println();
        }
    }

    static void inOrder(int idx) {
        if (idx * 2 <= N) inOrder(idx * 2);
        System.out.print(tree[idx]);
        if (idx * 2 + 1 <= N) inOrder(idx * 2 + 1);
    }

}
