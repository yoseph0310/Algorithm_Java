package Programmers.LV2;

public class 타겟_넘버 {
    public int solution(int[] numbers, int target) {
        int answer = 0;

        answer = dfs(numbers, target, 0, 0);
        return answer;
    }

    public int dfs(int [] numbers, int target, int idx, int num){
        if ( idx == numbers.length){
            return num == target ? 1 : 0;
        } else {
            return dfs(numbers, target, idx + 1 , num + numbers[idx]) +
                    dfs(numbers, target, idx + 1 , num - numbers[idx]);
        }


    }
}
