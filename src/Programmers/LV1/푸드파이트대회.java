package Programmers.LV1;

public class 푸드파이트대회 {
    public static void main(String[] args) {
        int[] food = new int[]{1,3,4,6};

        System.out.println(solution(food));
    }

    static String solution(int[] food) {
        StringBuilder sb = new StringBuilder();

        String ans = "";
        String str = "";

        for (int i = 1; i < food.length; i++) {
            if (food[i] < 2) continue;

            int mid = food[i] / 2;

            for (int j = 0; j < mid; j++) {
                sb.append(i);
            }
        }
        str = sb.reverse().toString();
        sb.reverse();
        sb.append(0).append(str);
        ans = sb.toString();
        return ans;
    }
}
