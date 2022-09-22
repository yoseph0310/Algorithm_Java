package Programmers.LV2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 영어끝말잇기 {
    public static void main(String[] args) {
        int n = 5;
        String[] words = {"hello", "observe", "effect", "take", "either", "recognize",
                "encourage", "ensure", "establish", "hang", "gather", "refer",
                "reference", "estimate", "executive"};

        System.out.println(Arrays.toString(solution(n, words)));
    }

    public static int[] solution(int n, String[] words){
        int[] answer = new int[2];
        List<String> list = new ArrayList<>();
        boolean flag = true;

        for (int i = 0; i < words.length; i++) {
            if (i > 0 && words[i-1].charAt(words[i-1].length() -1) != words[i].charAt(0) || list.contains(words[i])){
                answer[0] = (i%n) + 1;
                answer[1] = (i/n) + 1;
                flag = false;
                break;
            }
            list.add(words[i]);
        }
        if (flag) return new int[]{0, 0};

        return answer;
    }
}

// 순서대로 말을 하다가 (words의 길이만큼 반복하다가)
    // 이전 단어의 맨 뒷단어와 현재 단어의 맨 앞단어가 같거나 이미 말한거라면
        // answer 에 i % n + 1, i / n + 1 담고 종료
    // 아니면 말한 단어 리스트에 추가
// 모두 진행됐으면 0,0 반환