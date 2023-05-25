package Programmers.LV2;

public class 양궁대회 {
    int[] answer = {-1};
    int[] lion;
    int max = -1;

    public int[] solution(int n, int[] info) {
        lion = new int[11];

        dfs(n, 0, info);

        return answer;
    }

    void dfs(int n, int depth, int[] info) {
        if (depth == n) {
            int apeach_p = 0;
            int lion_p = 0;

            for (int i = 0; i <= 10; i++) {
                if (info[i] == 0 && lion[i] == 0) continue;

                if (info[i] < lion[i]) lion_p += 10 - i;
                else apeach_p += 10 - i;
            }

            if (lion_p > apeach_p) {
                if (lion_p - apeach_p >= max) {
                    answer = lion.clone();
                    max = lion_p - apeach_p;
                }
            }

            return;
        }

        for (int i = 0; i <= 10 && lion[i] <= info[i]; i++) {
            lion[i]++;
            dfs(n, depth+1, info);
            lion[i]--;
        }
    }
}
