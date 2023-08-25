package BackJoon.실버.S4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * k 개의 로프를 사용해 w 중량의 물체를 들어올리면
 * 각 로프에는 w / k 만큼 하중이 걸린다.
 *
 */
public class BJ_2217_로프 {

    static int[] weights;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        weights = new int[N];     // 물건의 중량이 아닌 로프가 들수 있는 중량

        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(weights);

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            max = Math.max(max, weights[i] * (N - i));
        }

        System.out.println(max);
    }
}

