package Programmers.LV3;
import java.util.*;

public class 셔틀버스 {
    class Solution {
        public String solution(int n, int t, int m, String[] timetable) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (String table : timetable) {
                int time = Integer.parseInt(table.substring(0, 2)) * 60 + Integer.parseInt(table.substring(3));
                pq.add(time);
            }

            int startTime = 9 * 60;
            int lastTime = 0;
            int total = 0;

            for (int i = 0; i < n; i++) {
                total = 0;
                while (!pq.isEmpty()) {
                    int curTime = pq.peek();
                    if (curTime <= startTime && total < m) {
                        pq.poll();
                        total++;
                    } else break;
                    lastTime = curTime - 1;
                }
                startTime += t;
            }

            if (total < m) lastTime = startTime - t;

            int hour = lastTime / 60;
            int minute = lastTime % 60;

            String HH = "";
            String MM = "";
            if (hour < 10) HH = "0" + hour;
            else HH = String.valueOf(hour);

            if (minute < 10) MM = "0" + minute;
            else MM = String.valueOf(minute);

            return HH + ":" + MM;
        }
    }
}
