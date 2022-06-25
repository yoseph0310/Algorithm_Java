package BackJoon.실버.S2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_1406 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String str = br.readLine();
        int M = Integer.parseInt(br.readLine());

        Stack<String> leftSt = new Stack<>();
        Stack<String> rightSt = new Stack<>();

        String[] arr = str.split("");
        for (String s : arr) {
            leftSt.push(s);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String c = st.nextToken();

            switch (c){
                case "L":
                    if (!leftSt.isEmpty()){
                        rightSt.push(leftSt.pop());
                    }
                    break;

                case "D":
                    if(!rightSt.isEmpty()){
                        leftSt.push(rightSt.pop());
                    }
                    break;

                case "B":
                    if(!leftSt.isEmpty()){
                        leftSt.pop();
                    }
                    break;

                case "P":
                    String t = st.nextToken();
                    leftSt.push(t);
                    break;

                default:
                    break;
            }
        }

        while(!leftSt.isEmpty()){
            rightSt.push(leftSt.pop());
        }
        while(!rightSt.isEmpty()){
            bw.write(rightSt.pop());
        }
        bw.flush();
        bw.close();

    }
}
