package Programmers.LV1;
import java.util.*;

public class 성격_유형_검사하기 {
    public static void main(String[] args) {

    }

    public String solution(String[] survey, int[] choices) {
        StringBuilder sb = new StringBuilder();

        String answer = "";
        int len = survey.length;

        int[][] mbti = new int[4][2];
        char[][] jipyo = {{'R','T'},{'C','F'},{'J','M'},{'A','N'}};

        for (int i = 0; i < len; i++) {
            String[] s = survey[i].split("");

            String left = s[0];
            String right = s[1];

            int row = 0;
            switch(survey[i]) {
                case "RT", "TR":
                    row = 0;
                    break;
                case "CF", "FC":
                    row = 1;
                    break;
                case "JM", "MJ":
                    row = 2;
                    break;
                case "AN", "NA":
                    row = 3;
                    break;
            }


        /*
            [0][0] : R / [0][1] : T
            [1][0] : C / [1][1] : F
            [2][0] : J / [2][1] : M
            [3][0] : A / [3][1] : N
        */

            if (left.equals("R") || left.equals("C") || left.equals("J") || left.equals("A")) {
                if (choices[i] == 1) mbti[row][0] += 3;
                else if (choices[i] == 2) mbti[row][0] += 2;
                else if (choices[i] == 3) mbti[row][0] += 1;
                else if (choices[i] == 5) mbti[row][1] += 1;
                else if (choices[i] == 6) mbti[row][1] += 2;
                else if (choices[i] == 7) mbti[row][1] += 3;
            } else if (left.equals("T") || left.equals("F") || left.equals("M") || left.equals("N")) {
                if (choices[i] == 1) mbti[row][1] += 3;
                else if (choices[i] == 2) mbti[row][1] += 2;
                else if (choices[i] == 3) mbti[row][1] += 1;
                else if (choices[i] == 5) mbti[row][0] += 1;
                else if (choices[i] == 6) mbti[row][0] += 2;
                else if (choices[i] == 7) mbti[row][0] += 3;
            }

        }

        for (int i = 0; i < 4; i++) {
            if (mbti[i][0] > mbti[i][1]){
                sb.append(jipyo[i][0]);
            } else if (mbti[i][0] < mbti[i][1]) {
                sb.append(jipyo[i][1]);
            } else {
                if (jipyo[i][0] < jipyo[i][1]) {
                    sb.append(jipyo[i][0]);
                } else if (jipyo[i][0] > jipyo[i][1]) {
                    sb.append(jipyo[i][1]);
                }
            }
        }

        answer = sb.toString();
        return answer;
    }

}
