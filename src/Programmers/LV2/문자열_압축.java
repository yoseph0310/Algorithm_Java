package Programmers.LV2;

public class 문자열_압축 {
    public int solution(String s) {
        int answer = s.length();
        int cnt = 1;

        for (int i = 1; i <= s.length() / 2; i++) {
            StringBuilder res = new StringBuilder();
            String base = s.substring(0, i);

            for (int j = i; j <= s.length(); j+=i) {
                int endIdx = Math.min(j + i, s.length());
                String compare = s.substring(j, endIdx);

                if (base.equals(compare)) {
                    cnt++;
                } else {
                    if (cnt >= 2) {
                        res.append(cnt);
                    }
                    res.append(base);
                    base = compare;
                    cnt = 1;
                }
            }
            res.append(base);
            answer = Math.min(answer, res.length());
        }

        return answer;
    }
}
