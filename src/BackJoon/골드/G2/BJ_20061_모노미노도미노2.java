package BackJoon.골드.G2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20061_모노미노도미노2 {

    static int score;
    static int[][] green, blue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        green = new int[6][4];
        blue = new int[4][6];

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            moveBlock(t, x, y);
            getScore();
            pushGreen(checkGreen());
            pushBlue(checkBlue());
        }



        System.out.println(score);
        System.out.println(count());
    }

    static void moveBlock(int t, int x, int y) {
        int idx;

        switch (t) {
            case 1:
                blue[x][0] = 1;
                green[0][y] = 1;
                idx = 1;

                while (idx < 6 && blue[x][idx] == 0) {
                    blue[x][idx-1] = 0;
                    blue[x][idx] = 1;
                    idx++;
                }

                idx = 1;
                while (idx < 6 && green[idx][y] == 0) {
                    green[idx-1][y] = 0;
                    green[idx][y] = 1;
                    idx++;
                }
                break;

            case 2:
                blue[x][0] = 1;
                blue[x][1] = 1;
                green[0][y] = 1;
                green[0][y+1] = 1;
                idx = 2;

                while (idx < 6 && blue[x][idx] == 0) {
                    blue[x][idx-2] = 0;
                    blue[x][idx] = 1;
                    idx++;
                }

                idx = 1;
                while (idx < 6 && green[idx][y] == 0 && green[idx][y+1] == 0) {
                    green[idx - 1][y] = 0;
                    green[idx - 1][y+1] = 0;
                    green[idx][y] = 1;
                    green[idx][y+1] = 1;
                    idx++;
                }
                break;

            case 3:
                blue[x][0] = 1;
                blue[x+1][0] = 1;
                green[0][y] = 1;
                green[1][y] = 1;
                idx = 1;

                while (idx < 6 && blue[x][idx] == 0 && blue[x+1][idx] == 0) {
                    blue[x][idx-1] = 0;
                    blue[x+1][idx-1] = 0;
                    blue[x][idx] = 1;
                    blue[x+1][idx] = 1;
                    idx++;
                }

                idx = 2;
                while (idx < 6 && green[idx][y] == 0) {
                    green[idx-2][y] = 0;
                    green[idx][y] = 1;
                    idx++;
                }
                break;

        }
    }

    static void getScore() {
        for (int i = 5; i >= 2; i--) {
            int cnt = 0;
            for (int j = 0; j < 4; j++) {
                if (green[i][j] == 0) break;
                else cnt++;
            }

            if (cnt == 4) {
                score++;
                cleanGreen(i);
                i++;
            }
        }

        for (int i = 5; i >= 2; i--) {
            int cnt = 0;
            for (int j = 0; j < 4; j++) {
                if (blue[j][i] == 0) break;
                else cnt++;
            }

            if (cnt == 4) {
                score++;
                cleanBlue(i);
                i++;
            }
        }
    }

    static void cleanGreen(int line) {
        for (int i = line; i > 0 ; i--) {
            for (int j = 0; j < 4; j++) {
                green[i][j] = green[i-1][j];
            }
        }
    }

    static void cleanBlue(int line) {
        for (int i = line; i > 0; i--) {
            for (int j = 0; j < 4; j++) {
                blue[j][i] = blue[j][i-1];
            }
        }
    }

    static void pushGreen(int cnt) {
        for (int i = 5; i >= 2; i--) {
            for (int j = 0; j < 4; j++) {
                green[i][j] = green[i-cnt][j];
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                green[i][j] = 0;
            }
        }

    }

    static void pushBlue(int cnt) {
        for (int i = 5; i >= 2; i--) {
            for (int j = 0; j < 4; j++) {
                blue[j][i] = blue[j][i-cnt];
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                blue[j][i] = 0;
            }
        }
    }

    static int checkGreen() {
        int cnt = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[i][j] == 1) {
                    cnt++;
                    break;
                }
            }
        }

        return cnt;
    }

    static int checkBlue() {
        int cnt = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (blue[j][i] == 1) {
                    cnt++;
                    break;
                }
            }
        }

        return cnt;
    }

    static int count() {
        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (blue[i][j] == 1) cnt++;
                if (green[j][i] == 1) cnt++;
            }
        }

        return cnt;
    }

    static void print_board(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}