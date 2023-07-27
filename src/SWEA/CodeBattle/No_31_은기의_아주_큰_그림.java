package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    꿈에서 본 그림 : H * W
    선생님이 그린 그림 : N * M

    - 1 <= H <= N <= 2,000
    - 1 <= W <= M <= 2,000

    --> 꿈에서 본 그림은 선생님이 그린 그림보다 작거나 같다.

 */
public class No_31_은기의_아주_큰_그림 {

    static int H, W, N, M;

    static final int MAX_N = 2000;
    static final int HASH_SIZE = 1 << 30;
    static final int DIV = HASH_SIZE - 1;

    static int[][] dreamPic, teacherPic;
    static int[][] tmp, teacherHash;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            dreamPic = new int[MAX_N][MAX_N];
            teacherPic = new int[MAX_N][MAX_N];
            tmp = new int[MAX_N][MAX_N];
            teacherHash = new int[MAX_N][MAX_N];

            // o, x 만 주어지므로 각각 1, 0 으로 dream, teach 정보 입력
            String input = "";
            for (int i = 0; i < H; i++) {
                input = br.readLine();
                for (int j = 0; j < W; j++) {
                    char c = input.charAt(j);

                    if (c == 'o') dreamPic[i][j] = 1;
                    else dreamPic[i][j] = 0;
                }
            }

            for (int i = 0; i < N; i++) {
                input = br.readLine();
                for (int j = 0; j < M; j++) {
                    char c = input.charAt(j);

                    if (c == 'o') teacherPic[i][j] = 1;
                    else teacherPic[i][j] = 0;
                }
            }

            // dream Hash 구하기
            for (int i = 0; i < H; i++) {
                // dreamPic 의 i 행 (가로) 의 각각의 해시 값을 구한다.
                tmp[0][i] = getHash(dreamPic[i], W, 4);
            }
            int dreamHash = getHash(tmp[0], H, 5); // (하나의 해쉬값을 만든다)

            // teach Hash 구하기
            int mulC = calcMul(W, 4);
            int mulR = calcMul(H, 5);
            for (int i = 0; i < N; i++) {
                // teacherPic 의 i 행 (가로) 의 해시값을 구한다.
                tmp[0][i] = getHash(teacherPic[i], W, 4);

                // dream 가로 길이 만큼씩 다음 해시를 구한다.
                for (int j = 1; j < M - W + 1; j++) {
                    tmp[j][i] = getNext(tmp[j - 1][i], teacherPic[i][j - 1], mulC, teacherPic[i][j + W - 1], 4);
                }
            }

            for (int i = 0; i < M - W + 1; i++) {
                teacherHash[0][i] = getHash(tmp[i], H, 5);

                for (int j = 1; j < N - H + 1; j++) {
                    teacherHash[j][i] = getNext(teacherHash[j - 1][i], tmp[i][j - 1], mulR, tmp[i][j + H - 1], 5);
                }
            }

            // 같은 문자열 찾기
            int ans = 0;
            for (int i = 0; i < N - H + 1; i++) {
                for (int j = 0; j < M - W + 1; j++) {
                    if (teacherHash[i][j] == dreamHash) ans++;
                }
            }

            System.out.println("#" + t + " " + ans);
        } // end of testcase
    } // end of main

    static int getNext(int prev, int sub, int mul, int add, int shift) {
        long hash = prev - (sub * mul);
        hash = (hash << shift) + hash + add;
        return (int) (hash & DIV);
    }

    static int calcMul(int num, int shift) {
        long rev = 1;
        for (int i = 1; i < num; i++) {
            rev = (rev << shift) + rev;
        }
        return (int) (rev & DIV);
    }

    static int getHash(int[] arr, int num, int shift) {
        long hash = 0;

        for (int i = 0; i < num; i++) {
            hash = (hash << shift) + hash + arr[i];
        }

        return (int) (hash & DIV);
    }
}
