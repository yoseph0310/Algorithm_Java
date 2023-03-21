package BackJoon.브론즈.B3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_5086_배수와_약수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            /*
                a가 b의 약수라면 : factor
                a가 b의 배수라면 : multiple
                둘 다 아니라면 : neither
             */
            if (a == 0 && b == 0) {
                break;
            }

            if (a < b) {
                if (b % a == 0) System.out.println("factor");
                else System.out.println("neither");
            } else if (a > b) {
                if (a % b == 0) System.out.println("multiple");
                else System.out.println("neither");
            } else {
                System.out.println("factor");
            }


        }
    }
}
