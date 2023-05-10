package Programmers.LV2;

public class 이진_변환_반복하기 {
    public int[] solution(String s) {
        int[] answer = new int[2];      // 0 : 이진변환횟수 1 : 제거된 모든 0의 개수

        int sLen = 0;
        String bString = "";

        while (!s.equals("1")) {
            // 제거될 0 카운트 하기
            for (int i = 0; i < s.length(); i++) {
                String st = s.charAt(i)+"";

                if (st.equals("0")) answer[1]++;
            }

            // 0 제거하기
            s = s.replace("0", "");
            sLen = s.length();

            // 제거된 문자열의 길이를 이진법으로 변환하기
            s = Integer.toBinaryString(sLen);
            answer[0]++;
        }

        return answer;
    }
}
