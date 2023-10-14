package Programmers.LV3;

public class 자물쇠와_열쇠 {
    class Solution {
        public boolean solution(int[][] key, int[][] lock) {
            int point = key.length - 1;

            for (int x = 0; x < point + lock.length; x++) {
                for (int y = 0; y < point + lock.length; y++) {
                    // key 회전
                    for (int r = 0; r < 4; r++) {
                        int[][] newLock = new int[lock.length + point * 2][lock.length + point * 2];

                        // 확장 배열에 원배열 위치
                        for (int i = 0; i < lock.length; i++) {
                            for (int j = 0; j < lock.length; j++) {
                                newLock[i + point][j + point] = lock[i][j];
                            }
                        }

                        // 확장배열에 r 회전한 key를 열쇠와 겹치는 부분 x, y 기준으로 일치시킨다.
                        match(newLock, key, r, x, y);
                        if (isPossible(newLock, point, lock.length)) return true;
                    }
                }
            }

            return false;
        }

        boolean isPossible(int[][] lock, int point, int len) {
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (lock[point + i][point + j] != 1) return false;
                }
            }
            return true;
        }

        void match(int[][] lock, int[][] key, int r, int x, int y) {
            int len = key.length;

            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    // 한번도 회전하지 않은 경우 -> r이 1증가할 때마다 90도 회전
                    if (r == 0) {
                        lock[x + i][y + j] += key[i][j];
                    } else if (r == 1) {
                        lock[x + i][y + j] += key[j][len - i - 1];
                    } else if (r == 2) {
                        lock[x + i][y + j] += key[len - i - 1][len - j - 1];
                    } else {
                        lock[x + i][y + j] += key[len - j - 1][i];
                    }
                }
            }
        }

        void printBoard(int[][] b) {
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
