package BackJoon.실버.S4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10845 {

    public static int[] queue;
    public static int size = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        queue = new int[N];

        while (N-- > 0){
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
    public static void push(int num){
        queue[size] = num;
        size++;
    }
    public static int pop(){
        if (size == 0){
            return -1;
        }
        else {
            int res = queue[0];
            for (int i = 1; i < size; i++) {
                if (queue[i] == 0){
                    break;
                }
                else {
                    queue[i-1] = queue[i];
                    queue[i] = 0;
                }
            }
            size--;
            return res;
        }
    }
    public static int size(){
        return size;
    }
    public static int empty(){
        if (size == 0){
            return 1;
        }
        else {
            return 0;
        }
    }
    public static int front(){
        if (size == 0){
            return -1;
        }
        else {
            return queue[0];
        }
    }
    public static int back(){
        if (size == 0){
            return -1;
        }
        else {
            return queue[size-1];
        }
    }
}
