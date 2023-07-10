package BackJoon.실버.S1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_5107_마니또 {
    static int[][] map;
    static int N;
    static int cnt;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = 1;

        while(true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) return;

            ArrayList<String> name = new ArrayList<>();
            map = new int[N][N];
            visited = new boolean[N];
            cnt = 0;

            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");

                if (!name.contains(input[0])) {
                    name.add(input[0]);
                }
                if (!name.contains(input[1])) {
                    name.add(input[1]);
                }

                map[name.indexOf(input[0])][name.indexOf(input[1])] = 1;
            }

            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    for (int j = 0; j < N; j++) {
                        if (map[i][j] == 1 && !visited[j]) {
                            visited[i] = true;
                            solve(j);
                        }
                    }
                }
            }

            System.out.println(T + " " + cnt);
            T++;
        }
    }

    static void solve(int x) {
        visited[x] = true;

        for (int i = 0; i < N; i++) {
            if (map[x][i] == 1) {
                if (!visited[i]) solve(i);
                else cnt++;
                break;
            }
        }
    }
}
