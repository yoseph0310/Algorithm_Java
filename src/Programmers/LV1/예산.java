package Programmers.LV1;

import java.util.Arrays;

public class 예산 {
    public int sol(int[] d, int budget){
        int ans = 0;
        int res = 0;

        Arrays.sort(d);

        for (int i = 0; i < d.length; i++) {
            res += d[i];
            if (res > budget){
                ans = i;
                break;
            }
        }
        if (res <= budget){
            ans = d.length;
        }
        return ans;
    }
}
