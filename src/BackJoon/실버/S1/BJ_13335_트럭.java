package BackJoon.실버.S1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_13335_트럭 {

    static int N, W, L, ans;
    static Queue<Integer> trucks;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());       // 트럭 수
        W = Integer.parseInt(st.nextToken());       // 다리 길이
        L = Integer.parseInt(st.nextToken());       // 다리 하중

        trucks = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trucks.add(Integer.parseInt(st.nextToken()));
        }

        solve();
        System.out.println(ans);
    }

    static void solve() {

        int bridgeWeight = 0;

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < W; i++) {
            q.add(0);
        }

        while(!q.isEmpty()) {
            ans++;
            bridgeWeight -= q.poll();
            if (!trucks.isEmpty()) {
                if (trucks.peek() + bridgeWeight <= L) {
                    bridgeWeight += trucks.peek();
                    q.add(trucks.poll());
                } else {
                    q.add(0);
                }
            }
        }

    }

}
