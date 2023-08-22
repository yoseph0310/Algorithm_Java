package SWEA.CodeBattle.실전실습.No_8_공지방송;

import java.util.HashMap;

class UserSolution {

    int[] time;
    HashMap<Integer, Long> employeeHM;

    public void init() {
        time = new int[86401];
        employeeHM = new HashMap<>();
    }


    public int add(int mId, int mStart, int mEnd) {
        employeeHM.put(mId, ((long) mStart << 32) + mEnd + 1);
        return employeeHM.size();
    }

    public int remove(int mId) {
        employeeHM.remove(mId);
        return employeeHM.size();
    }

    int announce(int mDuration, int M) {
        for (int i = 0; i < 86401; i++) {
            time[i] = 0;
        }

        for (Long l : employeeHM.values()) {
            int s = (int) (l >> 32);
            int e = (int) (l & 0xffffffffL);
            if (e - mDuration >= s) {
                time[s]++;
                time[e - mDuration + 1]--;
            }
        }

        int cur = 0;
        for (int i = 0; i < 86400; i++) {
            cur += time[i];
            if (cur >= M) {
                return i;
            }
        }
        return -1;
    }

}