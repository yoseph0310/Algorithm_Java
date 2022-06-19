package BackJoon.실버.S4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10866 {

    static int[] deque = new int[20001];
    static int size = 0;
    static int front = 10000;
    static int back = 10000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        while(N-- > 0){
            st = new StringTokenizer(br.readLine());

            switch (st.nextToken()){
                case "push_front":
                    push_front(Integer.parseInt(st.nextToken()));
                    break;

                case "push_back":
                    push_back(Integer.parseInt(st.nextToken()));
                    break;

                case "pop_front":
                    sb.append(pop_front()).append('\n');
                    break;

                case "pop_back":
                    sb.append(pop_back()).append('\n');
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
    public static void push_front(int num){
        deque[front] = num;
        front--;
        size++;
    }
    public static void push_back(int num){
        back++;
        size++;
        deque[back] = num;
    }
    public static int pop_front(){
        if (size == 0){
            return -1;
        }
        else {
            int res = deque[front + 1];
            front++;
            size--;
            return res;
        }
    }
    public static int pop_back(){
        if (size == 0){
            return -1;
        }
        else {
            int res = deque[back];
            size--;
            back--;
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
            return deque[front + 1];
        }
    }

    public static int back(){
        if (size == 0){
            return -1;
        }
        else {
            return deque[back];
        }
    }

}
