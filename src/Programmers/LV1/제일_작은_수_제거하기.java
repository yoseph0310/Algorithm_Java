package Programmers.LV1;

public class 제일_작은_수_제거하기 {
    public int[] solution(int[] arr) {
        if(arr.length == 1) return new int[]{-1};

        int min = Integer.MAX_VALUE;
        for (int a : arr){
            if (min > a) min = a;
        }

        int [] newArr = new int[arr.length - 1];
        int idx = 0;
        for (int a : arr){
            if (min != a) newArr[idx++] = a;
        }
        return newArr;
    }
}
