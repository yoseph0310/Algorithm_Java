package BackJoon.실버.S2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_6603 {

    static int[] S;
    static int[] res;
    static int k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if(k == 0) {
                break;
            }

            S = new int[k];
            res = new int[k];

            for (int i = 0; i < k; i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }
            dfs(0, 0);

            System.out.println();
        }
    }

    private static void dfs(int start, int depth){
        if(depth == 6) {
            print();
        }
        for (int i = start; i < k; i++) {
            res[i] = 1;
            dfs(i + 1, depth + 1);
            res[i] = 0;
        }
    }

    private static void print() {
        for (int i = 0; i < k; i++) {
            if(res[i] == 1){
                System.out.print(S[i] + " ");
            }
        }
        System.out.println();
    }
}
