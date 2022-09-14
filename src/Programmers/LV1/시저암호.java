package Programmers.LV1;

public class 시저암호 {
    public static void main(String[] args) {
        String s = "abcDeFGHiJklm";

        System.out.println(solution(s, 3));
    }

    public static String solution(String s, int n){
        String ans = "";

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isLowerCase(ch)){
                ch = (char)((ch - 'a' + n) % 26 + 'a');
            } else if (Character.isUpperCase(ch)){
                ch = (char)((ch - 'A' + n) % 26 + 'A');
            }
            ans += ch;
        }
        return ans;
    }
}
