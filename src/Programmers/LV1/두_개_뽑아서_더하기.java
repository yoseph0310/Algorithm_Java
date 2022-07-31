package Programmers.LV1;

import java.util.ArrayList;
import java.util.Arrays;

public class 두_개_뽑아서_더하기 {
    public int[] solution(int[] numbers){
        ArrayList<Integer> list = new ArrayList<>();
        int len = numbers.length;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int a = numbers[i] + numbers[j];
                if (list.indexOf(a) < 0){
                    list.add(a);
                }
            }
        }

        int [] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        Arrays.sort(ans);

        return ans;
    }
}
