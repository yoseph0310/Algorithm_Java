package Programmers.LV1;

public class 카드_뭉치 {
    public static String solution(String[] cards1, String[] cards2, String[] goal) {
        int card1Idx = 0;
        int card1IdxMax = cards1.length;
        int card2Idx = 0;
        int card2IdxMax = cards2.length;

        for (int i = 0; i < goal.length; i++) {
            String curStr = goal[i];

            if (card1Idx < card1IdxMax && curStr.equals(cards1[card1Idx])) {
                card1Idx++;
            } else if (card2Idx < card2IdxMax && curStr.equals(cards2[card2Idx])) {
                card2Idx++;
            } else {
                return "No";
            }
        }

        return "Yes";
    }

    public static void main(String[] args) {
        String[] cards1 = {"i", "drink", "water"};
        String[] cards2 = {"want", "to"};
        String[] goal1 = {"i", "want", "to", "drink", "water"};

        String[] cards3 = {"i", "water", "drink"};
        String[] cards4 = {"want", "to"};
        String[] goal2 = {"i", "want", "to", "drink", "water"};

        System.out.println(solution(cards1, cards2, goal1));
        System.out.println(solution(cards3, cards4, goal2));
    }
}
