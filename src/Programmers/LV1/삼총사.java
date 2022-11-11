package Programmers.LV1;

public class 삼총사 {
    public static void main(String[] args) {
        int[] number = new int[]{-3, -2, -1, 0, 1, 2, 3};

        System.out.println(solution(number));
    }
    static int solution(int[] number) {
        int ans = 0;

        for (int i = 0; i < number.length; i++) {
            for (int j = i + 1; j < number.length; j++) {
                for (int k = j + 1; k < number.length; k++) {
                    if (number[i] + number[j] + number[k] == 0) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
}
