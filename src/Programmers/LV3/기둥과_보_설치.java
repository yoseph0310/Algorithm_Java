package Programmers.LV3;

public class 기둥과_보_설치 {
    class Solution {

        boolean[][] pillar;
        boolean[][] slab;

        public int[][] solution(int n, int[][] build_frame) {
            pillar = new boolean[n + 1][n + 1];
            slab = new boolean[n + 1][n + 1];

            int cnt = 0;
            for (int i = 0; i < build_frame.length; i++) {
                int x = build_frame[i][0];
                int y = build_frame[i][1];
                int type = build_frame[i][2];
                int command = build_frame[i][3];

                // 기둥
                if (type == 0) {
                    // 설치
                    if (command == 1) {
                        if (isPossiblePillar(x, y)) {
                            pillar[x][y] = true;
                            cnt++;
                        }
                    } else {
                        pillar[x][y] = false;
                        if (canDelete(n)) cnt--;
                        else pillar[x][y] = true;
                    }
                }
                // 보
                else {
                    // 설치
                    if (command == 1) {
                        if (isPossibleSlab(x, y)) {
                            slab[x][y] = true;
                            cnt++;
                        }
                    } else {
                        slab[x][y] = false;
                        if (canDelete(n)) cnt--;
                        else slab[x][y] = true;
                    }
                }
            }

            int[][] answer = new int[cnt][3];
            int idx = 0;
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    if (pillar[i][j]) {
                        answer[idx][0] = i;
                        answer[idx][1] = j;
                        answer[idx++][2] = 0;
                    }
                    if (slab[i][j]) {
                        answer[idx][0] = i;
                        answer[idx][1] = j;
                        answer[idx++][2] = 1;
                    }
                }
            }

            return answer;
        }

        boolean isPossiblePillar(int x, int y) {
            if (y == 0) return true;
            else if (y > 0 && pillar[x][y - 1]) return true;
            else if (x > 0 && slab[x - 1][y] || slab[x][y])  return true;
            return false;
        }

        boolean isPossibleSlab(int x, int y) {
            if (y > 0 && pillar[x][y - 1] || pillar[x + 1][y - 1]) return true;
            else if (x > 0 && slab[x - 1][y] && slab[x + 1][y]) return true;
            return false;
        }

        boolean canDelete(int n) {
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    if (pillar[i][j] && !isPossiblePillar(i, j)) return false;
                    else if (slab[i][j] && !isPossibleSlab(i, j)) return false;
                }
            }
            return true;
        }
    }
}
