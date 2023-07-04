package TossNEXT;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 멋쟁이_숫자_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int ans = 0;

        for (int i = 0; i < input.length() - 2; i++) {
            if (input.charAt(i) == input.charAt(i+1) && input.charAt(i+1) == input.charAt(i+2)) {
                ans = Math.max(ans, Integer.parseInt(input.substring(i, i+3)));
            }
        }

        System.out.println(ans);
    }
}
