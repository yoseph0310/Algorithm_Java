package Programmers.LV2;
import java.util.*;

public class 교점에_별_만들기 {

    long minX = Long.MAX_VALUE, minY = Long.MAX_VALUE;
    long maxX = Long.MIN_VALUE, maxY = Long.MIN_VALUE;

    public String[] solution(int[][] line) {
        String[] answer = {};
        HashSet<Point> set = new HashSet<>();

        // x = (BF-ED) / (AD-BC)
        // y = (EC-AF) / (AD-BC)
        long x, y;
        for (int i = 0; i < line.length - 1; i++) {
            long a = line[i][0];
            long b = line[i][1];
            long e = line[i][2];
            for (int j = i + 1; j < line.length; j++) {
                long c = line[j][0];
                long d = line[j][1];
                long f = line[j][2];

                long adbc = a*d - b*c;
                if (adbc == 0) continue;    // 비교 직선과 평행하다.

                long bfed = b*f - e*d;
                if (bfed % adbc != 0) continue;     // 정수가 아니다.

                long ecaf = e*c - a*f;
                if (ecaf % adbc != 0) continue;     // 정수가 아니다.

                x = bfed / adbc;
                y = ecaf / adbc;
                set.add(new Point(x, y));

                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
            }
        }

        long height = maxY - minY + 1;
        long width = maxX - minX + 1;

        answer = new String[(int)height];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < width; i++) {
            sb.append('.');
        }
        Arrays.fill(answer, sb.toString());

        long anx, any;
        for (Point p : set) {
            any = maxY - p.y;
            anx = p.x - minX;

            answer[(int)any] = answer[(int)any].substring(0, (int)anx)+"*"+answer[(int)any].substring((int)anx+1);
        }

        return answer;
    }

    class Point {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}
