package BackJoon.실버.S4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class BJ_9012 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            bw.write(vps(br.readLine()) + "\n");
        }
        bw.flush();
        bw.close();
    }
    public static String vps(String s){
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '('){
                stack.push(c);
            }
            else if (stack.isEmpty()){
                return "NO";
            }
            else {
                stack.pop();
            }
        }
        if (stack.isEmpty()){
            return "YES";
        }
        else{
            return "NO";
        }

    }
}
