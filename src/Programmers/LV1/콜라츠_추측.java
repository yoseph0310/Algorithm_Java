package Programmers.LV1;

public class 콜라츠_추측 {
    public int solution(int n) {
        long num = n;
        int ans = 0;

        if(num == 1) return 0;

        while(num != 1){
            ans++;
            if(ans == 500) return -1;
            num = (num % 2 == 0) ? num /= 2 : num * 3 + 1;

        }

        return ans;
    }
}
