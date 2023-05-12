package Programmers.LV2;
import java.util.*;

public class 괄호_회전하기 {
    public int solution(String s) {
        int answer = 0;

        Queue<Character> q = new LinkedList<>();
        // s 의 길이 만큼 반복하는데 왼쪽에있는 값을 오른쪽으로 보낸다.
        // 그럴때마다 올바른 문자열인지 확인한다.
        // stack에 차례대로 넣으면서 stack에 값이 남아있지 않으면 true, 아니면 false를 반환한다.
        // 올바른 문자열임이 확인되면 cnt를 1 증가시킨다.
        System.out.println(isRight(s));

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            q.add(c);
        }

        for (int i = 0; i < s.length(); i++) {
            if (isRight(s)) {
                answer++;
            }

            q.add(q.poll());
            int size = q.size();

            // 새로운 String 만들기
            s = "";
            for (int j = 0; j < size; j++) {
                char c = q.poll();
                q.add(c);

                s += c;
            }
        }

        return answer;
    }

    boolean isRight(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            // System.out.println(c1);

            if (stack.isEmpty()) {
                stack.push(c1);
            }
            else {
                char c2 = stack.peek();

                if ((c2 == '{' && c1 == '}') || (c2 == '[' && c1 == ']') || (c2 == '(' && c1 == ')') ) {
                    stack.pop();
                } else {
                    stack.push(c1);
                }


            }
        }

        return stack.isEmpty();
    }
}
