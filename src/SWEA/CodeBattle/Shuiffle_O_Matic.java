package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Shuiffle_O_Matic {

    static int N, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            ans = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int [] cards = new int[N];

            for (int i = 0; i < N; i++) {
                cards[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, cards);

            System.out.println("#" + t + " " + (ans <= 5 ? ans : -1));
        }
    }

    public static void dfs(int cnt, int [] cards){
        if (cnt > 5) return;
        if (cnt >= ans) return;
        
        if (isSorted(cards)){
            ans = Math.min(ans, cnt);
        }

        int[] left = new int[N/2];
        int[] right = new int[N/2];

        for (int i = 0; i < N/2; i++) {
            left[i] = cards[i];
        }
        for (int i = N/2; i < N; i++) {
            right[i - N/2] = cards[i];
        }

        for (int x = 1; x < N; x++) {
            int[] next = x < N/2 ? shuffle(x, left, right) : shuffle(x - N/2, right, left);
            dfs(cnt + 1, next);
        }
    }

    public static int[] shuffle(int x, int [] left, int[] right){
        int[] next = new int[N];
        int idx = 0;
        int lIdx = 0;
        int rIdx = 0;

        while(lIdx < N/2 - x){
            next[idx++] = left[lIdx++];
        }

        int order = 0;
        while(lIdx < N/2 && rIdx < N/2){
            next[idx++] = order % 2 == 0 ? right[rIdx++] : left[lIdx++];
            order++;
        }

        while(rIdx < N/2){
            next[idx++] = right[rIdx++];
        }

        return next;
    }

    public static boolean isSorted(int[] cards){
        boolean isUp = true;
        boolean isDown = true;
        for (int i = 0; i < N; i++) {
            if(cards[i] != i+1) isUp = false;
            if(cards[i] != N - i) isDown = false;

            if(!isUp && !isDown) return false;
        }

        return true;
    }
}