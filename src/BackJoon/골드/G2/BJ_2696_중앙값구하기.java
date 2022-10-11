package BackJoon.골드.G2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2696_중앙값구하기 {

    static int M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            M = Integer.parseInt(br.readLine());


            for (int i = 0; i < M; i++) {
                if (i % 10 == 0){
                    st = new StringTokenizer(br.readLine());
                }
            }

            System.out.println((M + 1) / 2);

        }

    }
}

// 홀수번째 수를 읽을 때 마다, 지금까지 입력받은 값의 중앙값을 출력
