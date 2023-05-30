package Programmers.LV2;

public class 우박수열_정적분 {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];

        int cnt = count(k);

        double[] yValue = new double[cnt+1];
        yValue[0] = k;
        for (int i = 1; i <= cnt; i++) {
            yValue[i] = getYValue(yValue[i-1]);
        }

        double[] area = new double[cnt+1];
        for (int i = 1; i <= cnt; i++) {
            area[i] = (yValue[i] + yValue[i-1]) / 2;
        }

        double[] sumArea = new double[cnt+1];
        sumArea[1] = area[1];
        for (int i = 2; i <= cnt; i++) {
            sumArea[i] = area[i] + sumArea[i-1];
        }

        for (int i = 0; i < ranges.length; i++) {
            int s = ranges[i][0];
            int e = cnt + ranges[i][1];

            if (s < e) {
                double val = sumArea[e] - sumArea[s];
                String str = String.format("%.1f", val);
                answer[i] = (Double.parseDouble(str));
            } else if (s > e) {
                answer[i] = -1.0;
            } else {
                answer[i] = 0.0;
            }
        }

        return answer;
    }

    double getYValue(double n) {
        if (n % 2 == 0) return n / 2;
        else return (n * 3) + 1;
    }

    int count(int k) {
        int cnt = 0;
        while (k > 1) {
            if (k % 2 == 0) k /= 2;
            else k = (k*3) + 1;
            cnt++;
        }
        return cnt;
    }
}
