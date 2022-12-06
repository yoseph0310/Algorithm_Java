package BackJoon.실버.S3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_10799_쇠막대기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        Stack<Character> stack = new Stack<>();

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
                continue;
            }
            if (s.charAt(i) == ')') {
                stack.pop();

                if (s.charAt(i - 1) == '('){
                    res += stack.size();
                } else {
                    res += 1;
                }
            }

        }

        System.out.println(res);
    }
}
