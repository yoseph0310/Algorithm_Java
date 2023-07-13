package SWEA.DX특강사전문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_2_부자의_꿈 {

    static int N, M, Q, ans;
    static int[][] map;

    static int[] maxRow;        // ex) maxRow[r] : r 행의 최댓값의 열 번호
    static int[] maxCol;        // ex) maxCol[c] : c 열의 최댓값의 행 번호

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            Q = Integer.parseInt(st.nextToken());
            ans = 0;

            map = new int[N][M];

            maxRow = new int[N];
            maxCol = new int[M];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // maxRow, maxCol 최댓값으로 초기화 (값이 아닌 그 행의 최대값인 열 번호를 저장)
            for (int i = 0; i < N; i++) {
                int tmp = -1;
                for (int j = 0; j < M; j++) {
                    if (tmp < map[i][j]) {
                        tmp = map[i][j];
                        maxRow[i] = j;
                    }
                }
            }
            for (int i = 0; i < M; i++) {
                int tmp = -1;
                for (int j = 0; j < N; j++) {
                    if (tmp < map[j][i]) {
                        tmp = map[j][i];
                        maxCol[i] = j;
                    }
                }
            }

            // 안전 영역의 개수 초기화
            int safeArea = 0;
            for (int i = 0; i < N; i++) {
                // i 행의 최댓값인 열의 최댓값이 i 라면 그것은 안전지역임
                if (maxCol[maxRow[i]] == i) {
                    safeArea++;
                }
            }

            // Q 번의 업데이트
            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());

                int r = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                int val = Integer.parseInt(st.nextToken());

                map[r][c] = val;

                // 입력으로 주어진 r과 c가 최댓값인 행과 열이라면 안전지역임
                if (maxRow[r] == c && maxCol[c] == r) {
                    ans += safeArea;
                    continue;
                }

                // r 행의 최댓값이 val 보다 작은데 안전지역이었을 때
                if (map[r][maxRow[r]] < val && maxCol[maxRow[r]] == r) {
                    safeArea--;
                }

                // c 열의 최댓값이 val 보다 작은데 안전지역이었을 때
                if (map[maxCol[c]][c] < val && maxRow[maxCol[c]] == c) {
                    safeArea--;
                }

                // 새로 입력된 r 행의 최댓값의 열이 val 보다 작으면 현재 들어온 c 로 바꾼다.
                if (map[r][maxRow[r]] < val) {
                    maxRow[r] = c;
                }

                // 새로 입력된 c 열의 최댓값의 행이 val 보다 작으면 현재 들어온 r 로 바꾼다.
                if (map[maxCol[c]][c] < val) {
                    maxCol[c] = r;
                }

                // 행과 열의 최댓값 번호를 갱신 후 안전지역이면 1 추가한다.
                if (maxRow[r] == c && maxCol[c] == r) safeArea += 1;

                ans += safeArea;
            }

            System.out.println("#" + t + " " + ans);
        }   // end of testcase

    }   // end of main
}
