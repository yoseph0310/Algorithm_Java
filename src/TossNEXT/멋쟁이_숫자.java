package TossNEXT;

import java.util.ArrayList;

public class 멋쟁이_숫자 {
    public int solution(String s) {
        int answer = -1;

        String [] s_arr = s.split("");
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < s_arr.length-2; i++) {
            String str = s_arr[i];
            if (str.equals(s_arr[i+1]) && str.equals(s_arr[i+2])){
                list.add(Integer.parseInt(str+s_arr[i+1]+s_arr[i+2]));
            }
        }

        for(int num : list){
            if(answer < num) answer = num;
        }

        return answer;
    }
}
