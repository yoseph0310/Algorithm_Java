package SWEA.DX특강사전문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
1
3
1000000 1000000 1000000
1000000 1000000 1000000
1000000 1000000 1000000
 */
public class No_2_AI의반란최후의전쟁 {

    static final int MAX_N = 50;
    static final int INF = 1_000_000 * 2 * MAX_N + 1;   // int 값 벗어나지 않도록 능력치 최댓값(10 ^ 6)으로 최대 50명이 2개씩.

    static int N;
    static Agent[] agents;
    static int[][][] DP;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            agents = new Agent[N];
            DP = new int[N + 1][MAX_N + 1][MAX_N + 1];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                agents[i] = new Agent(a, b, c);
            }

            // 가동할 수 있으면 소멸되는 능력치를 더할때 최소가 되는 값. 혹은 -1
            // DP : i 번째 사람이 j, k 능력을 선택하지 않아 소멸했을 때 최소값
            for (int i = 0; i < N + 1; i++) {
                for (int j = 0; j < MAX_N + 1; j++) {
                    Arrays.fill(DP[i][j], -1);
                }
            }

            int ans = solve(0, 0, 0);

            ans = (ans >= INF) ? -1 : ans;
            System.out.println("#" + t + " " + ans);
        }

    }

    static int solve(int idx, int avCnt1, int avCnt2) {
//        System.out.println("["+idx+"] 번 요원 - a 횟수 : " + avCnt1 + " || b 횟수 : " + avCnt2 + " || c 횟수 : " + (N - (avCnt1 + avCnt2)));
        if (idx == N) {
            // 능력들을 모두 한번쯤은 선택해야 가동이 가능
            return (avCnt1 >= 1 && avCnt2 >= 1 && N - avCnt1 - avCnt2 >= 1) ? 0 : INF;
        }

        // 값이 갱신 되어있다면 해당 값 리턴
        if (DP[idx][avCnt1][avCnt2] != -1) return DP[idx][avCnt1][avCnt2];

        int res = INF;

        // a, b, c 중 하나를 선택하여 나머지 2개의 능력을 더하는 경우의 수중 최소가 되는 경우를 구한다.
        res = Math.min(res, agents[idx].a + agents[idx].b + solve(idx + 1, avCnt1, avCnt2));
        res = Math.min(res, agents[idx].a + agents[idx].c + solve(idx + 1, avCnt1, avCnt2 + 1));
        res = Math.min(res, agents[idx].b + agents[idx].c + solve(idx + 1, avCnt1 + 1, avCnt2));

        return DP[idx][avCnt1][avCnt2] = res;
    }

    static class Agent {
        int a, b, c;

        public Agent(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
