package SWEA.모의A형;

import Z_DataStructure.LinkedList.Doubly.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 무선_충전 {

    static int M, BC, ans;

    static ArrayList<BatteryCharge>[][] board;
    static ArrayList<BatteryCharge> bcList;

    static int[] personA;
    static int[] personB;

    static int[] dx = {0, -1, 0, 1, 0};
    static int[] dy = {0, 0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            BC = Integer.parseInt(st.nextToken());

            personA = new int[] {1, 1};
            personB = new int[] {10, 10};

            board = new ArrayList[11][11];
            bcList = new ArrayList<>();
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

                bcList.add(new BatteryCharge(x, y, c, p));
            }

            for (BatteryCharge bc : bcList) {
                init(bc);
            }

            charge(personA, personB);

            for (int i = 0; i < M; i++) {
                movePerson(A, B, i);
                charge(personA, personB);
            }

            System.out.println("#"+t+" "+ans);
        }
    }

    static void init(BatteryCharge bc) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {bc.x, bc.y});

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

                    if (isNotBoundary(nx, ny)) continue;
                    if (board[nx][ny] == null) board[nx][ny] = new ArrayList<>();
                    board[nx][ny].add(bc);
                    q.add(new int[]{nx, ny});
                }
            }

            coverage++;
            if (coverage == bc.coverage) {
                break;
            }
        }
    }

    static void charge(int[] A, int[] B) {
        // 두 사람의 배터리를 충전해나간다.
        int max = Integer.MIN_VALUE;
        // A 위치에 BC 리스트가 있을 때
        if (board[A[0]][A[1]] != null) {
            // B 위치에 BC 리스트가 있을 때
            if (board[B[0]][B[1]] != null) {
                for (BatteryCharge bcA: board[A[0]][A[1]]) {
                    for (BatteryCharge bcB: board[B[0]][B[1]]) {
                        if (bcA.equals(bcB)) {
                            max = Math.max(max, bcA.p);
                        } else {
                            max = Math.max(max, bcA.p + bcB.p);
                        }
                    }
                }
            }
            // B 위치에 BC 리스트가 없을 때 ( A 만 충전 범위일 때 )
            else {
                for (BatteryCharge bcA: board[A[0]][A[1]]) {
                    max = Math.max(max, bcA.p);
                }
            }
        }
        // A 위치에 BC 리스트가 없을 때
        else {
            // B 위치에 BC 리스트가 있을 때
            if (board[B[0]][B[1]] != null) {
                for (BatteryCharge bcB: board[B[0]][B[1]]) {
                    max = Math.max(max, bcB.p);
                }
            }
            // B 위치에 BC 리스트가 없을 때
            else {
                max = 0;
            }
        }
        ans += max;
    }

    static void movePerson(int[] A, int[] B, int idx) {
        personA[0] += dx[A[idx]];
        personA[1] += dy[A[idx]];
        personB[0] += dx[B[idx]];
        personB[1] += dy[B[idx]];
    }

    static boolean isNotBoundary(int x, int y) {
        return !(1 <= x && x <= 10 && 1 <= y && y <= 10);
    }

    static class BatteryCharge {
        int x, y, coverage, p;

        public BatteryCharge(int x, int y, int coverage, int p) {
            this.x = x;
            this.y = y;
            this.coverage = coverage;
            this.p = p;
        }
    }
}