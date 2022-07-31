package Programmers.LV1;

public class 평균_구하기 {
    public double solution(int[] arr) {
        double ans = 0;

        for (double num : arr){
            ans += num;
        }

        ans /= arr.length;

        return ans;
    }

}
