package Programmers.LV2;

public class 덧칠하기 {

    public static void main(String[] args) {
        int[] section = {2, 3, 6};
        System.out.println(solution(8, 4, section));
    }

    static int solution(int n, int m, int[] section) {
        int ans = 0;
        int max = 0;

        for (int w: section) {
            if (w < max) continue;

            ans++;
            max = w + m;
        }

        return ans;
    }
}
