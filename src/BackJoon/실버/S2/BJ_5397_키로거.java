package BackJoon.실버.S2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_5397_키로거 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            Stack<Character> left = new Stack<>();
            Stack<Character> right = new Stack<>();

            String input = br.readLine();
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);

                switch (c) {
                    case '<':
                        if (!left.isEmpty()) {
                            right.push(left.pop());
                        }
                        break;

                    case '>':
                        if (!right.isEmpty()) {
                            left.push(right.pop());
                        }
                        break;

                    case '-':
                        if (!left.isEmpty()) {
                            left.pop();
                        }
                        break;

                    default:
                        left.push(c);
                }
            }

            while(!left.isEmpty()) {
                right.push(left.pop());
            }
            while (!right.isEmpty()) {
                sb.append(right.pop());
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
