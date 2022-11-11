package Programmers.LV1;

public class 숫자짝꿍 {
    public static void main(String[] args) {
        String X = "12321";
        String Y = "42531";

        System.out.println(solution(X, Y));
    }

    static String solution(String X, String Y) {
        StringBuilder answer = new StringBuilder();

        int[] arrX = new int[10];
        int[] arrY = new int[10];

        cntInArr(X, arrX);
        cntInArr(Y, arrY);

        for (int i = arrX.length - 1; i >= 0; i--) {
            while (arrX[i] >= 1 && arrY[i] >= 1) {
                arrX[i]--;
                arrY[i]--;

                answer.append(i);
            }
        }

        if (answer.toString().equals("")) {
            return "-1";
        } else if (answer.toString().startsWith("0")) {
            return "0";
        } else {
            return answer.toString();
        }
    }

    static void cntInArr(String str, int[] arr) {
        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - '0';

            arr[idx]++;
        }
    }
}
