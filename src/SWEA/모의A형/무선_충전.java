package SWEA.모의A형;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 무선_충전 {

    static ArrayList<BatteryCharge>[][] board;
    static ArrayList<BatteryCharge> list;

    static int[] dx = {0, -1, 0, 1, 0};
    static int[] dy = {0, 0, 1, 0, -1};
    static int[] personA;
    static int[] personB;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int BC = Integer.parseInt(st.nextToken());
            personA = new int[] {1, 1};
            personB = new int[] {10, 10};
            board = new ArrayList[11][11];
            list = new ArrayList<>();
            ans = 0;

            int[] A = new int[M];
            int[] B = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < BC; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                list.add(new BatteryCharge(x, y, c, p));
            }

            for (BatteryCharge bc: list) {
                bfs(bc);
            }

            choiceBC(personA, personB);

            for (int i = 0; i < M; i++) {
                move(A, B, i);
                choiceBC(personA, personB);
            }

            System.out.println("#"+t+" "+ans);
        }
    }

    static void move(int[] A, int[] B, int idx){
        personA[0] += dx[A[idx]];
        personA[1] += dy[A[idx]];
        personB[0] += dx[B[idx]];
        personB[1] += dy[B[idx]];
    }

    static void choiceBC(int[] personA, int[] personB){
        int tempMax = Integer.MIN_VALUE;

        // A의 위치에 BC 리스트가 있을 때
        if (board[personA[0]][personA[1]] != null){
            // B의 위치에 BC 리스트가 있을 때
            if (board[personB[0]][personB[1]] != null) {
                for (BatteryCharge bcA : board[personA[0]][personA[1]]) {
                    for (BatteryCharge bcB : board[personB[0]][personB[1]]) {
                        if (bcA.equals(bcB)) {
                            tempMax = Math.max(tempMax, bcA.p);
                        } else {
                            tempMax = Math.max(tempMax, bcA.p + bcB.p);
                        }
                    }
                }
            } else {
                for (BatteryCharge bcA: board[personA[0]][personA[1]]) {
                    tempMax = Math.max(tempMax, bcA.p);
                }
            }
        }
        // A의 위치에 BC 리스트가 없을 때
        else {
            if (board[personB[0]][personB[1]] != null) {
                for (BatteryCharge bcB: board[personB[0]][personB[1]]) {
                    tempMax = Math.max(tempMax, bcB.p);
                }
            } else {
                tempMax = 0;
            }
        }
        ans += tempMax;
    }

    static void bfs(BatteryCharge bc){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[11][11];

        q.add(new int[] {bc.x, bc.y});
        visited[bc.x][bc.y] = true;

        if (board[bc.x][bc.y] == null) {
            board[bc.x][bc.y] = new ArrayList<>();
        }
        board[bc.x][bc.y].add(bc);
        int coverage = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] temp = q.poll();

                int x = temp[0];
                int y = temp[1];

                for (int d = 1; d < 5; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (1 <= nx && nx <= 10 && 1 <= ny && ny <= 10 && !visited[nx][ny]){
                        if (board[nx][ny] == null) {
                            board[nx][ny] = new ArrayList<>();
                        }
                        board[nx][ny].add(bc);
                        visited[nx][ny] = true;
                        q.add(new int[] {nx, ny});
                    }
                }
            }

            coverage++;
            if (coverage == bc.coverage){
                break;
            }
        }
    }

    static class BatteryCharge {
        int x, y, coverage, p;

        public BatteryCharge(int x, int y, int coverage, int p){
            this.x = x;
            this.y = y;
            this.coverage = coverage;
            this.p = p;
        }
    }
}