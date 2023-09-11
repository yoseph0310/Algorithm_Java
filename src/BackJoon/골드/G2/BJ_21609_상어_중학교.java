package BackJoon.골드.G2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_21609_상어_중학교 {

    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static PriorityQueue<Group> pq;

    static int max;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        visited = new boolean[N][N];
        pq = new PriorityQueue<>();

        max = 0;

        // 격자 정보 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 블록 그룹 생성
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && board[i][j] != -1 && board[i][j] != 0) {
                    bfs(i, j, board[i][j]);
                }
            }
        }

        if (pq.size() == 0) return;

        //
    }

    static void bfs(int i, int j, int color) {
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(i, j));
        visited[i][j] = true;


    }


    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static class Group implements Comparable<Group> {
        Point standardBlock;
        List<Point> blockList;
        int rainbowCnt;

        public Group(int rainbowCnt) {
            this.standardBlock = null;
            this.rainbowCnt = rainbowCnt;
            this.blockList = new ArrayList<>();
        }

        @Override
        public int compareTo(Group g) {
            if (g.rainbowCnt == this.rainbowCnt) return g.standardBlock.x - this.standardBlock.x;
            else if (this.standardBlock.x == g.standardBlock.x) return g.standardBlock.y - this.standardBlock.y;
            else return g.rainbowCnt - this.rainbowCnt;
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void printBoard(int[][] b) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.println();
        }
    }
}
