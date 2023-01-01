package BackJoon.골드.G3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_2812_크게_만들기 {

    static int N, K;
    static int[] num_arr;
    static Stack<Integer> stack;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        num_arr = new int[N];
        stack = new Stack<>();

        String num = br.readLine();
        for (int i = 0; i < N; i++) {
            int n = num.charAt(i) - '0';

            if (!stack.isEmpty()) {
                while(!stack.isEmpty() && K > 0 && stack.peek() < n) {
                    stack.pop();
                    K--;
                }
            }

            stack.push(n);
        }

        while (K-- > 0) {
            stack.pop();
        }

        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.reverse().toString());
    }

}
