package BackJoon.골드.G3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16235_나무_제태크_2번째풀이 {

    static int N, M, K;

    static int[][] material;
    static int[][] board;
    static Deque<Tree> treeList;
    static Queue<Tree> deadTreeList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());           // 땅의 크기
        M = Integer.parseInt(st.nextToken());           // 나무 개수
        K = Integer.parseInt(st.nextToken());           // K 년
        board = new int[N][N];                          // 변화하는 양분 상태를 나타내는 board

        // 땅의 초기 양분 상태 입력
        material = new int[N][N];                       // S2D2가 추가할 양분 상태 및 초기 양분 상태
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                material[i][j] = Integer.parseInt(st.nextToken());
                board[i][j] = 5;
            }
        }

        treeList = new LinkedList<>();
        // M 개 만큼 나무의 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());

            treeList.add(new Tree(x, y, age));
        }

        // K 년을 보낸다.
        for (int i = 0; i < K; i++) {
            deadTreeList = new LinkedList<>();
            // 봄
            spring();
            // 여름
            summer();
            // 가을
            autumn();
            // 겨울
            winter();
        }

        // 살아남은 나무의 수를 출력한다.
        System.out.println(treeList.size());
    }

    /**
     * 나무가 자신의 나이만큼 양분을 먹고 나이가 1 증가
     * 여러개의 나무가 있다면 나이가 어린 나무부터 먹는다.
     * 땅(board)에 양분이 부족하여 나무가 양분을 못먹으면 죽는다.
     */
    static void spring() {

        for (int i = 0; i < treeList.size();) {
            Tree t = treeList.poll();

            // 자신 나이만큼 양분을 못먹으면 죽는다.
            if (board[t.x][t.y] < t.age) {
                deadTreeList.add(t);
            } else {
                board[t.x][t.y] -= t.age;
                t.age++;
                i++;
                treeList.add(t);
            }
        }
    }

    /**
     *  죽은 나무(deadTreeList) 나이의 2를 나눈 값(소수점 아래 버림)을 원래있던 칸(board)에 더한다.
     */
    static void summer() {
        for (Tree t: deadTreeList) {
            int newMaterial = t.age / 2;

            board[t.x][t.y] += newMaterial;
        }
    }

    /**
     * 살아있는 나무(treeList)들 중에서 나이가 5의 배수이면 자기 주변(8방)에 새로운 나무를 번식시킨다.
     * 땅(board)을 벗어난다면 생기지 않는다.
     */
    static void autumn() {
        int[] dx = {-1, 0, 1, 0, 1, 1, -1, -1};
        int[] dy = {0, 1, 0, -1, -1, 1, -1, 1};

        Queue<Tree> temp = new LinkedList<>();
        for (Tree t : treeList) {
            if (t.age % 5 == 0) {
                temp.add(t);
            }
        }

        while(!temp.isEmpty()) {
            Tree t = temp.poll();

            for (int d = 0; d < 8; d++) {
                int nx = t.x + dx[d];
                int ny = t.y + dy[d];

                if (isNotBoundary(nx, ny)) continue;

                treeList.addFirst(new Tree(nx, ny, 1));
            }
        }

    }

    static boolean isNotBoundary(int x, int y) {
        return !(0 <= x && x < N && 0 <= y && y < N);
    }

    /**
     * S2D2가 양분(material)을 땅(board)에 추가한다.
     */
    static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] += material[i][j];
            }
        }
    }

    static class Tree implements Comparable<Tree>{
        int x, y, age;

        public Tree(int x, int y, int age) {
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
