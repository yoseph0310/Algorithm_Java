package Programmers.LV3;
import java.util.*;

public class 캠핑 {

    public int solution(int n, int[][] data) {
        ArrayList<Integer> xList = new ArrayList<>();
        ArrayList<Integer> yList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            xList.add(data[i][0]);
            yList.add(data[i][1]);
        }

        ArrayList<Integer> uniqueXList = new ArrayList<>(new HashSet<>(xList));
        ArrayList<Integer> uniqueYList = new ArrayList<>(new HashSet<>(yList));

        Collections.sort(uniqueXList);
        Collections.sort(uniqueYList);

        int[][] S = new int[n][n];

        for(int i = 0; i < n; i++) {
            int x = uniqueXList.indexOf(new Integer(data[i][0]));
            int y = uniqueYList.indexOf(new Integer(data[i][1]));

            data[i][0] = x;
            data[i][1] = y;

            S[x][y] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                S[i][j] += (i-1 >= 0 ? S[i-1][j] : 0) + (j-1 >= 0 ? S[i][j-1] : 0)
                        - (i-1 >= 0 && j-1 >= 0 ? S[i-1][j-1] : 0);
            }
        }

        int ans = 0;

        for(int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (data[i][0] == data[j][0] || data[i][1] == data[j][1]) continue;

                int sx, sy, ex, ey;

                sx = Math.min(data[i][0], data[j][0]);
                sy = Math.min(data[i][1], data[j][1]);
                ex = Math.max(data[i][0], data[j][0]);
                ey = Math.max(data[i][1], data[j][1]);

                int cnt;

                if (sx + 1 > ex - 1 || sy + 1 > ey - 1) cnt = 0;
                else cnt = S[ex - 1][ey - 1] - S[ex - 1][sy] - S[sx][ey - 1] + S[sx][sy];

                if (cnt == 0) ans++;
            }
        }

        return ans;
    }
}
