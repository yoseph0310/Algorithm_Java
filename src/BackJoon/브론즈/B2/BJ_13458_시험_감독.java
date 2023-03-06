package BackJoon.브론즈.B2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_13458_시험_감독 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());                                        // 시험장 개수
        int[] students = new int[N];                                                      // 각 시험장의 응시자 수를 담은 배열

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            students[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());                                       // 총감독관 감시가능 수
        int C = Integer.parseInt(st.nextToken());                                       // 부감독관 감시가능 수

        long cnt = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i] <= B) {
                cnt++;
                continue;
            } else {
                cnt++;
                students[i] -= B;

                if (students[i] % C == 0) cnt += students[i] / C;
                else if (students[i] % C != 0) cnt += students[i] / C + 1;
            }
        }

        System.out.println(cnt);
    }
}
