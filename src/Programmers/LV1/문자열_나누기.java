package Programmers.LV1;

public class 문자열_나누기 {
    public int solution(String s) {
        int answer = 0;

        char x = s.charAt(0);

        int sameCnt = 0;
        int diffCnt = 0;

        for (int i = 0; i < s.length(); i++) {
            if (sameCnt == diffCnt) {
                answer++;
                x = s.charAt(i);
            }
            if (s.charAt(i) == x) sameCnt++;
            else diffCnt++;
        }

        return answer;
    }
}
