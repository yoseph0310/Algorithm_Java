package Programmers.LV1;

public class 핸드폰번호가리기 {
    public String solution(String p_n){
        String ans = "";

        int len = p_n.length();

        for (int i = 0; i < len; i++) {
            if ( i < len - 4) {
                ans += "*";
            }
            else {
                ans += p_n.charAt(i);
            }
        }

        return ans;
    }
}
