package Programmers.LV3;
import java.util.*;

public class 몸짱_트레이너_라이언의_고민 {
    /*
        1. n (격자 한변 길이) : 최대 10
        2. m (회원 수) : 최대 1,000
        3. timetable : new int[m][2] - 0 (입실시간), 1 (퇴실시간)
        4. 600(오전 10(10시)) <= t1(입실) < t2(퇴실) <= 1,320(오후 10시(22시))
    */
    class Solution {
        public int solution(int n, int m, int[][] timetable) {
            int[] customer = new int[721];

            for (int i = 0; i < m; i++) {
                customer[timetable[i][0] - 600]++;
                customer[timetable[i][1] - 600 + 1]--;
            }

            int sum = 0;
            int max = 0;
            for (int i = 0; i <= 720; i++) {
                sum += customer[i];
                customer[i] = sum;
                max = Math.max(max, customer[i]);
            }

            if (max <= 1) return 0;

            ArrayList<Point> list = new ArrayList<>();
            for (int dist = 2 * (n - 1); dist >= 1; dist--) {
                for (int sc = 0; sc < n; sc++) {
                    list.clear();
                    int cnt = 0;

                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            if (i == 0 && j < sc) continue;

                            boolean flag = true;
                            for (Point p : list) {
                                if (Math.abs(p.x - i) + Math.abs(p.y - j) >= dist) continue;
                                flag = false;
                                break;
                            }
                            if (flag) {
                                if (++cnt == max) {
                                    return dist;
                                }
                                list.add(new Point(i, j));
                            }
                        }
                    }
                }
            }

            return 0;
        }

        class Point {
            int x, y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        void printBoard(int[][] b) {
            System.out.println(" 락커 상태 출력 ---> ");
            for (int i = 0; i < b.length; i++) {
                for (int j = 0; j < b[i].length; j++) {
                    System.out.print(b[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
