package Programmers.LV2;

public class 카펫 {
    public int[] solution(int brown, int yellow) {

        int height = 0;
        int width = 0;
        for ( height = 3; height <= (int) (brown + 4) / 2; height++){
            width = ((brown+4)/2) - height;
            if(width < height){
                break;
            }

            int yellowCnt = ( width - 2 ) * (height - 2 );
            if ( yellow == yellowCnt){
                break;
            }
        }
        int[] answer = new int[]{width, height};
        return answer;
    }

    public int[] solution2(int brown, int yellow) {
        int[] answer = new int[2];
        int sum = brown + yellow;

        for (int i = 3; i < sum; i++) {
            int j = sum / i;

            if (sum % i == 0 && j >= 3) {
                int col = Math.max(i, j);
                int row = Math.min(i, j);

                int center = (col - 2) * (row - 2);

                if (center == yellow) {
                    answer[0] = col;
                    answer[1] = row;
                    return answer;
                }
            }
        }

        return answer;
    }
}
