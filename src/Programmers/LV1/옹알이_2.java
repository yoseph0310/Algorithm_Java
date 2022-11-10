package Programmers.LV1;

public class 옹알이_2 {
    public static void main(String[] args) {
        String[] babbling = {"aya", "yee", "u"};

        System.out.println(solutions(babbling));
    }
    static int solutions(String[] babbling) {
        int answer = 0;

        String[] originBabList = {"aya", "ye", "woo", "ma"};
        String[] repeatBabList = {"ayaaya", "yeye", "woowoo", "mama"};

        for (String bab : babbling) {
            for (String rBab : repeatBabList) {
                bab = bab.replace(rBab, "X");
            }

            for (String oBab: originBabList) {
                bab = bab.replace(oBab, "?");
            }

            int sign = 0;
            for (int i = 0; i < bab.length(); i++) {
                if (!bab.subSequence(i, i+1).equals("?")) {
                    sign = 1;
                    break;
                }
            }

            if (sign == 0) answer++;
        }

        return answer;
    }
}
