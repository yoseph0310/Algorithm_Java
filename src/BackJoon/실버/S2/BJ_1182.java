package BackJoon.실버.S2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BJ_1182 {

    static int N, S, ans = 0;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//        StringTokenizer st = new StringTokenizer(br.readLine());

//        N = Integer.parseInt(st.nextToken());
//        S = Integer.parseInt(st.nextToken());
        N = sc.nextInt();
        S = sc.nextInt();
        arr = new int[N];

        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
            arr[i] = sc.nextInt();
        }

        dfs(0, 0);

        if (S == 0){
            ans--;
            System.out.println(ans);
        }
        else{
            System.out.println(ans);
        }
    }

    public static void dfs(int start, int num){
        if (start == N){
            if (num == S){
                ans++;
            }
            return;
        }
        dfs(start+1, num+arr[start]);
        dfs(start+1, num);
    }
}
