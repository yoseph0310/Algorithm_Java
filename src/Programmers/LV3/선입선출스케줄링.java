package Programmers.LV3;

public class 선입선출스케줄링 {
    public int solution(int n, int[] cores) {
        int answer = 0;

        int min = 0;
        int max = 10_000 * n / cores.length;

        int time = 0;
        int m = 0;

        while (true) {
            int mid = (min + max) / 2;

            int cnt = calculate(mid, cores);

            if (min > max) break;

            if (cnt >= n) {
                max = mid - 1;
                time = mid;
                m = cnt;
            } else {
                min = mid + 1;
            }
        }

        m -= n;
        for (int i = cores.length - 1; i >= 0; i--) {
            if (time % cores[i] == 0) {
                if (m == 0) {
                    answer = i + 1;
                    break;
                }
                m--;
            }
        }


        return answer;
    }

    int calculate(int time, int[] cores) {
        if (time == 0) {
            return cores.length;
        }

        int cnt = cores.length;

        for (int i = 0; i < cores.length; i++) {
            cnt += (time / cores[i]);
        }

        return cnt;
    }
}

