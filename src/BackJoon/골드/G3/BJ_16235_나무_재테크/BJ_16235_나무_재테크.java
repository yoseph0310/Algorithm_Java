package BackJoon.골드.G3.BJ_16235_나무_재테크;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_16235_나무_재테크 {

    static int N, M, K;
    static int[][] board;
    static int[][] nutrient;
    static Deque<Tree> treeList;
    static Queue<Tree> deadTreeList;

    // 상 우 하 좌
    static int[] dx = {-1, 0, 1, 0, -1, 1, 1, -1};
    static int[] dy = {0, 1, 0, -1, 1, 1, -1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());       // 땅의 가로, 세로 길이
        M = Integer.parseInt(st.nextToken());       // 심은 나무의 개수
        K = Integer.parseInt(st.nextToken());       // K년 후를 확인해야함

        board = new int[N + 1][N + 1];              // 양분
        nutrient = new int[N + 1][N + 1];
        treeList = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int amount = Integer.parseInt(st.nextToken());

                nutrient[i][j] = amount;
                board[i][j] = 5;
            }
        }

        // 나무 리스트에 추가
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            treeList.add(new Tree(x, y, age));
        }

        progress();
    }

    static void progress() {
        for (int i = 0; i < K; i++) {
            deadTreeList = new LinkedList<>();
            spring();
            summer();
            autumn();
            winter();
        }

        System.out.println(treeList.size());
    }

    static void spring() {

        for (int i = 0; i < treeList.size();) {
            Tree tree = treeList.poll();

            if (board[tree.x][tree.y] >= tree.age) {
                board[tree.x][tree.y] -= tree.age;
                tree.age++;
                i++;
                treeList.add(tree);
            } else {
                deadTreeList.add(tree);
            }
        }

    }

    static void summer() {

        for (Tree tree: deadTreeList) {
            int amount = tree.age / 2;
            board[tree.x][tree.y] += amount;
        }

    }

    static void autumn() {
        Queue<Tree> tempList = new LinkedList<>();
        for (Tree t: treeList) {
            if (t.age % 5 == 0) {
                tempList.add(t);
            }
        }

        while(!tempList.isEmpty()) {
            Tree t = tempList.poll();

            for (int d = 0; d < 8; d++) {
                int nx = t.x + dx[d];
                int ny = t.y + dy[d];

                if (isBoundary(nx, ny)) {
                    treeList.addFirst(new Tree(nx, ny, 1));
                }
            }
        }
    }

    static void winter() {
        // 각 칸마다 입력으로 주어진 양분을 추가로 더한다.
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                board[i][j] += nutrient[i][j];
            }
        }
    }

    static boolean isBoundary(int x, int y) {
        return 1 <= x && x <= N && 1 <= y && y <= N;
    }

    // 나이 어린 나무들 부터 양분을 먹으므로 Comparable 구현하여 age 오름차순으로 정렬
    static class Tree implements Comparable<Tree> {
        int x, y, age;

        public Tree(int x, int y, int age) {
            super();
            this.x = x;
            this.y = y;
            this.age = age;
        }


        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }
}
