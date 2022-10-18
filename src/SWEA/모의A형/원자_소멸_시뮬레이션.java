package SWEA.모의A형;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 원자_소멸_시뮬레이션 {

    static int N, ans;
    static int[][] board = new int[4001][4001];           // 원자들의 처음 위치는 -1,000 이상 1,000 이하의 정수로 주어짐, 0.5초 충돌도 해야함
    static List<Atom> atomList;

    static int[] dx = {0, 0, -1, 1};           // 상(0), 하(1), 좌(2), 우(3)
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        atomList = new ArrayList<>();

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());            // 원자 개수
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;       // 원자 x 위치
                int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;       // 원자 y 위치
                int dir = Integer.parseInt(st.nextToken());     // 원자 이동 방향
                int K = Integer.parseInt(st.nextToken());       // 원자 보유 에너지

                board[x][y] = K;                                // 원자 위치 마스킹
                atomList.add(new Atom(x, y, dir, K));
            }

            ans = solve();
            System.out.println("#" + t + " " + ans);
        }
    }

    static int solve(){
        int sum = 0;
        while (!atomList.isEmpty()){
            for (int i = 0; i < atomList.size(); i++) {
                Atom a = atomList.get(i);
                board[a.x][a.y] = 0;
                a.x += dx[a.dir];
                a.y += dy[a.dir];
                if (a.x > 4000 || a.x < 0 || a.y > 4000 || a.y < 0) {
                    atomList.remove(i);
                    i--;
                    continue;
                }

                board[a.x][a.y] += a.energy;

            }

            for (int i = 0; i < atomList.size(); i++) {
                Atom a = atomList.get(i);
                if (board[a.x][a.y] != a.energy){
                    sum += board[a.x][a.y];
                    board[a.x][a.y] = 0;
                    atomList.remove(i);
                    i--;
                }
            }
        }
        return sum;
    }

    static class Atom{
        int x, y, dir, energy;

        public Atom(int x, int y, int dir, int energy){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
        }
    }
}
