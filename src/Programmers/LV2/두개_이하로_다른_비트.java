package Programmers.LV2;

public class 두개_이하로_다른_비트 {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        // 짝수인 경우 -> num + 1;
        // 홀수인 경우 -> num + 2 ^ ((1의 개수) - 1);

        for (int i = 0; i < numbers.length; i++) {
            // 짝수
            if (numbers[i] % 2 == 0) {
                answer[i] = numbers[i] + 1;
            }
            // 홀수
            else {
                long tmp = numbers[i];
                long bit = 1;
                int cnt = 0;
                // 연속된 1 찾기
                while (tmp > 0) {
                    if (tmp % 2 == 1) cnt++;
                    else break;
                    tmp /= 2;
                }

                for (int j = 0; j < cnt - 1; j++) {
                    bit *= 2;
                }

                answer[i] = numbers[i] + bit;
            }
        }

        return answer;
    }
}
