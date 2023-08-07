package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_41_3차원_농부 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int cowX = Integer.parseInt(st.nextToken());        // 소 X 좌표
            int horseX = Integer.parseInt(st.nextToken());      // 말 X 좌표

            int dx = Math.abs(cowX - horseX);                   // 맨하탄 거리를 구하기 위한 X 거리를 먼저 구한다.

            int[] cows = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                cows[i] = Integer.parseInt(st.nextToken());         // 소들의 z 좌표
            }
            Arrays.sort(cows);

            int min = Integer.MAX_VALUE;
            int cnt = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int hPos = Integer.parseInt(st.nextToken());        // 말들의 z 좌표
                int cIdx = binSearch(cows, hPos);                   // 말 좌표와 최소가 되는 소의 인덱스를 구한다.

                if (0 <= cIdx && cIdx < N) {
                    int cPos = cows[cIdx];
                    int dz = Math.abs(cPos - hPos);
                    if (min > dz) {
                        min = dz;
                        cnt = 1;
                    } else if (min == dz) {
                        cnt++;
                    }
                }

                if (0 < cIdx && cIdx < N && cows[cIdx] != hPos) {
                    int cPos = cows[cIdx - 1];
                    int dz = Math.abs(cPos - hPos);
                    if (min > dz) {
                        min = dz;
                        cnt = 1;
                    } else if (min == dz) {
                        cnt++;
                    }
                }
            }

            System.out.println("#" + t + " " + (dx + min) + " " + cnt);
        }
    }

    static int binSearch(int[] cows, int hPos) {
        int L = 0;
        int H = cows.length - 1;
        int mid = (L + H) / 2;

        if (hPos < cows[L]) return 0;
        if (cows[H] < hPos) return cows.length - 1;

        while (L <= H) {
            mid = (L + H) / 2;

            if (cows[mid] == hPos) return mid;
            else if (cows[mid] < hPos) L = mid + 1;
            else H = mid - 1;
        }

        if (cows[mid] < hPos) mid++;

        return mid;
    }
}
