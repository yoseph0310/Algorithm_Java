package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class No_40_광고_시간_정하기 {

    static int L, N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            L = Integer.parseInt(br.readLine());
            N = Integer.parseInt(br.readLine());

            List<Peek> peekList = new ArrayList<>();

            int period = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                period += e - s;
                peekList.add(new Peek(s, e, period));
            }

            int max = -1;

            for (int i = 0; i < N; i++) {
                Peek curPeek = peekList.get(i);

                int sum = 0;
                int start = curPeek.start;
                int end = start + L;

                Peek lastPeek = findPeek(peekList, end);

                sum = lastPeek.sum - curPeek.sum + curPeek.end - curPeek.start;

                if (lastPeek.end > end && lastPeek.start < end) {
                    sum -= lastPeek.end - end;
                } else if (lastPeek.end > end) {
                    sum -= lastPeek.end - lastPeek.start;
                }

                max = Math.max(max, sum);
            }

            System.out.println("#" + t + " " + max);
        }
    }

    static Peek findPeek(List<Peek> peekList, int target) {
        int start = 0;
        int end = peekList.size() - 1;

        while (end > start) {
            int mid = (start + end) / 2;

            if (peekList.get(mid).end >= target) end = mid;
            else start = mid + 1;
        }

        return peekList.get(end);
    }

    static class Peek {
        int start, end, sum;

        public Peek(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }

}
