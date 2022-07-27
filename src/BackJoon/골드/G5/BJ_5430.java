package BackJoon.골드.G5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BJ_5430 {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> deque;

        while(T-- > 0){
            String command = br.readLine();
            int N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine(), "[],");
            deque = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                deque.add(Integer.parseInt(st.nextToken()));
            }

            AC(command, deque);
        }
        System.out.println(sb);
    }

    public static void AC(String command, ArrayDeque<Integer> deque) {
        boolean isRight = true;

        for (char cmd: command.toCharArray()) {
            if(cmd == 'R'){
                isRight = !isRight;
                continue;
            }
            if(isRight) {
                if(deque.pollFirst() == null){
                    sb.append("error\n");
                    return;
                }
            }
            else {
                if(deque.pollLast() == null) {
                    sb.append("error\n");
                    return;
                }
            }
        }
        printString(deque, isRight);
    }

    public static void printString(ArrayDeque<Integer> deque, boolean isRight){
        sb.append('[');

        if(deque.size() > 0) {
            if(isRight){
                sb.append(deque.pollFirst());

                while(!deque.isEmpty()){
                    sb.append(',').append(deque.pollFirst());
                }
            }
            else {
                sb.append(deque.pollLast());

                while(!deque.isEmpty()){
                    sb.append(',').append(deque.pollLast());
                }
            }
        }
        sb.append(']').append('\n');
    }
}
