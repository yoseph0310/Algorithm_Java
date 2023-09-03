package Programmers.LV3;

public class 추석_트래픽 {
    class Solution {
        public int solution(String[] lines) {

            int max = 0;
            int len = lines.length;
            int[] cnts = new int[len];

            for (int i = 0; i < len; i++) {
                // 이전 로그 종료 시점
                String[] pre = lines[i].split(" ");
                long preEnd = getTime(pre[1]);

                for (int j = i; j < len; j++) {
                    // 다음 로그 시작 시점 = 다음 로그 종료 시점 - 다음 로그 시작 시점 + 1ms
                    String[] next = lines[j].split(" ");
                    long nextEndDate = getTime(next[1]);
                    double sec = Double.parseDouble(next[2].substring(0, next[2].length() - 1));

                    long nextStart = (long) (nextEndDate - sec * 1000 + 1);

                    if (preEnd + 1000 > nextStart) {
                        cnts[i] += 1;
                        max = Math.max(max, cnts[i]);
                    }
                }
            }

            return max;
        }

        long getTime(String time) {
            String[] t1 = time.split("\\.");
            String[] hms = t1[0].split(":");

            long h = Long.parseLong(hms[0]);
            long m = Long.parseLong(hms[1]);
            long s = Long.parseLong(hms[2]);
            long ms = Long.parseLong(t1[1]);

            return ms + s * 1_000 + m * 60 * 1_000 + h * 60 * 60 * 1_000;
        }
    }
}
