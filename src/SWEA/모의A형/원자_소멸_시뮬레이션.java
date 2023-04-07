package SWEA.모의A형;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 원자_소멸_시뮬레이션 {

    static int N, ans;

    static ArrayList<Atom> atomList = new ArrayList<>();
    static int[][] board = new int[4001][4001];

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int energy = Integer.parseInt(st.nextToken());

                atomList.add(new Atom(x, y, dir, energy));
                board[x][y] = energy;
            }

            solve();
            System.out.println("#" + t + " " + ans);
        }
    }

    static void solve() {
        int sum = 0;

        while (!atomList.isEmpty()) {
            for (int i = 0; i < atomList.size(); i++) {
                Atom a = atomList.get(i);

                board[a.x][a.y] = 0;

                a.x += dx[a.dir];
                a.y += dy[a.dir];

                if (isNotBoundary(a.x, a.y)) {
                    atomList.remove(i);
                    i--;
                    continue;
                }

                board[a.x][a.y] += a.energy;
            }

            for (int i = 0; i < atomList.size(); i++) {
                Atom a = atomList.get(i);

                if (board[a.x][a.y] != a.energy) {
                    sum += board[a.x][a.y];
                    board[a.x][a.y] = 0;
                    atomList.remove(i);
                    i--;
                }
            }
        }

        ans = sum;
    }

    static boolean isNotBoundary(int x, int y) {
        return !(0 <= x && x <= 4000 && 0 <= y && y <= 4000);
    }

    static class Atom {
        int x, y, dir, energy;

        public Atom(int x, int y, int dir, int energy) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
        }
    }
}
