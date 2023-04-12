package Programmers.LV1;

public class 둘만의_암호 {
    public static String solution(String s, String skip, int index) {
        String answer = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int cnt = 1;
            while (cnt <= index) {
                ++c;
                if (c > 'z') {
                    c -= 26;
                }
                if (skip.contains(c+""))
                    continue;
                else
                    cnt++;
            }
            answer += c;
        }
        return answer;
    }


    public static void main(String[] args) {
        String s = "aukks";
        String skip = "wbqd";
        int index = 5;

        System.out.println(solution(s, skip, index));
    }
}
