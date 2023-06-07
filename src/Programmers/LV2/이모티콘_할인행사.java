package Programmers.LV2;

public class 이모티콘_할인행사 {
    int[] discount = {10, 20, 30, 40};
    int maxOfSubscribe;
    int maxOfCost;

    public int[] solution(int[][] users, int[] emoticons) {
        findAnswer(0, emoticons.length, new int[emoticons.length], users, emoticons);

        return new int[]{maxOfSubscribe, maxOfCost};
    }

    void findAnswer(int idx, int emoticonsLen, int[] discounts, int[][] users, int[] emoticons) {
        if (idx == emoticonsLen) {
            int subscribe = 0;
            int cost = 0;

            for (int[] user: users) {
                int userDis = user[0];
                int userCost = user[1];

                int sum = 0;

                for (int i = 0; i < emoticons.length; i++) {
                    if (discounts[i] >= userDis) {
                        sum += emoticons[i] / 100 * (100 - discounts[i]);
                    }
                }

                if (sum >= userCost) subscribe++;
                else cost += sum;
            }

            if (subscribe > maxOfSubscribe) {
                maxOfSubscribe = subscribe;
                maxOfCost = cost;
            } else if (subscribe == maxOfSubscribe) {
                maxOfCost = Math.max(maxOfCost, cost);
            }

            return;
        }

        for (int i = 0; i < 4; i++) {
            discounts[idx] = discount[i];
            findAnswer(idx+1, emoticonsLen, discounts, users, emoticons);
        }
    }
}
