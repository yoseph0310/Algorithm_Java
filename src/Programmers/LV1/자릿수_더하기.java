package Programmers.LV1;

public class 자릿수_더하기 {
    public int solution(int n){
        int ans = 0;

        while(n!=0){
            ans += n % 10;
            n /= 10;
        }
        return ans;
    }
}
