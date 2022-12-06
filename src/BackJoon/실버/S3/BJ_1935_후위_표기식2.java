package BackJoon.실버.S3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_1935_후위_표기식2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Double> stack = new Stack<>();
        int N = Integer.parseInt(br.readLine());
        String data = br.readLine();

        double[] arr = new double[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Double.parseDouble(br.readLine());
        }

        double res = 0;
        for (int i = 0; i < data.length(); i++) {
            if ('A' <= data.charAt(i) && data.charAt(i) <= 'Z') {
                stack.push(arr[data.charAt(i) - 'A']);
            } else {
                double n1 = stack.pop();
                double n2 = stack.pop();
                switch (data.charAt(i)) {
                    case '+':
                        res = n2 + n1;
                        stack.push(res);
                        break;
                    case '-':
                        res = n2 - n1;
                        stack.push(res);
                        break;
                    case '*':
                        res = n2 * n1;
                        stack.push(res);
                        break;
                    case '/':
                        res = n2 / n1;
                        stack.push(res);
                        break;
                }
            }
        }

        System.out.printf("%.2f", stack.pop());
    }
}