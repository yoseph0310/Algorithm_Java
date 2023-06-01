package Programmers.LV2;
import java.util.*;

public class 테이블_해시_함수 {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;

        col -= 1;
        row_begin -= 1;

        int finalCol = col;
        Arrays.sort(data, (o1, o2) -> {
            if (o1[finalCol] == o2[finalCol]) return o2[0] - o1[0];
            return o1[finalCol] - o2[finalCol];
        });

        for (int i = row_begin; i < row_end; i++) {
            int idx = i + 1;
            int dataTotal = Arrays.stream(data[i]).map(j -> j % idx).sum();

            answer = (answer ^ dataTotal);
        }

        return answer;
    }
}
