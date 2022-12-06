package BackJoon.실버.S4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_10773_제로_스택구현 {

    static int size = 0;
    static int[] stack;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        int ans = 0;

        stack = new int[100000];

        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                pop();
            } else {
                push(num);
            }
        }

        while(size != 0) {
            ans += pop();
        }

        System.out.println(ans);
    }

    static void push(int num) {
        stack[size] = num;
        size++;
    }

    static int pop() {
        int pop_num = stack[size - 1];
        stack[size - 1] = 0;
        size--;

        return pop_num;
    }
}
