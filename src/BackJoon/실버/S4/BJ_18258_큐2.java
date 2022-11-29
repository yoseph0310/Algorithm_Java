package BackJoon.실버.S4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_18258_큐2 {

    static int N;
    static int[] q = new int[2000000];
    static int size = 0;
    static int front = 0;
    static int back = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "push":
                    push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(pop()).append('\n');
                    break;
                case "size":
                    sb.append(size()).append('\n');
                    break;
                case "empty":
                    sb.append(empty()).append('\n');
                    break;
                case "front":
                    sb.append(front()).append('\n');
                    break;
                case "back":
                    sb.append(back()).append('\n');
                    break;
            }
        }

        System.out.println(sb);
    }

    static void push(int n){
        q[back] = n;
        back++;
        size++;
    }
    static int pop() {
        if (size == 0) return -1;
        else {
            int res = q[front];
            size--;
            front++;
            return res;
        }
    }
    static int size() {
        return size;
    }
    static int empty() {
        if (size == 0) return 1;
        else return 0;
    }
    static int front() {
        if (size == 0) return -1;
        else return q[front];
    }
    static int back() {
        if (size == 0) return -1;
        else return q[back-1];
    }
}
