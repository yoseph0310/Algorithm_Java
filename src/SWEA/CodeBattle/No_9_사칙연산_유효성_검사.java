package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_9_사칙연산_유효성_검사 {

    static int N, ans;
    static String[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 1; t <= 10; t++) {
            N = Integer.parseInt(br.readLine());
            tree = new String[N + 1];
            ans = 1;

            for (int i = 0; i < N; i++) {
                String[] node_info = br.readLine().split(" ");

                // 단말 노드가 아니면서 숫자이거나, 단말 노드인데 연산자이면 계산 안됨
                if (node_info.length >= 3 && node_info[1].charAt(0) >= '0' ||
                node_info.length == 2 && node_info[1].charAt(0) < '0'){
                    for (int j = i+1; j < N; j++) {
                        br.readLine();
                    }
                    ans = 0;
                    break;
                }
            }

            System.out.println("#" + t + " " + ans);
        }

    }


}
