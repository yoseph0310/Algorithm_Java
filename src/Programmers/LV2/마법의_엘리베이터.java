package Programmers.LV2;

public class 마법의_엘리베이터 {
    public int solution(int storey) {
        int answer = 0;

        String str = String.valueOf(storey);
        char[] chars = str.toCharArray();
        int[] i_storey = new int[chars.length];

        for (int i = 0; i < chars.length; i++) {
            i_storey[i] = chars[i] - '0';
        }

        for (int i = i_storey.length - 1; i >= 0; i--) {

            int n = i_storey[i];

            if (n >= 6) {
                answer += (10 - n);
                if (i - 1 < 0) {
                    answer += 1;
                    continue;
                }
                i_storey[i - 1]++;
            } else if (n <= 4) {
                answer += n;
            } else if (n == 5) {
                if (i - 1 < 0) {
                    answer += 5;
                    continue;
                } else {
                    if (i_storey[i-1] < 5) {
                        answer += n;
                    } else {
                        answer += 5;
                        i_storey[i-1]++;
                    }
                }
            }
        }

        return answer;
    }
}
