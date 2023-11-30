package CodeTree.삼성스터디.예술성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * - 개체
 *  1. 격자
 *      - n * n 크기 격자
 *      - 각 칸의 수는 1이상 10이하
 *
 *  2. 그룹
 *      - 동일한 숫자가 상하좌우로 인접해있다면 동일 그룹이라 본다.
 *
 *  3. 예술 점수
 *      - 그룹 a, 그룹 b의 조화로움 : (그룹 a에 속한 칸의 수 + 그룹 b에 속한 칸의 수) * 그룹 a 숫자값 * 그룹 b 숫자값 * 그룹 a와 b가 맞닿아 있는 변의 수
 *      - 초기 예술 점수가 존재
 *
 * - 진행
 *  1. 초기 예술 점수를 구해놓는다.
 *  2. 회전
 *      - 중앙을 기준으로 두 선을 그어 십자모양과 그 외 부분으로 진행
 *      - 십자모양은 통째로 반시계 90도 회전
 *      - 십자모양을 제외한 4개의 사각형들은 개별적으로 시계방향으로 90도 회전
 *  3. 회전을 모두 마치면 예술 점수를 다시 구한다.
 *  4. 3회전을 진행한다.
 *  5. 초기 예술점수, 1회전, 2회전, 3회전 점수의 합을 구한다.
 */
public class 예술성_Print {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Group {
        int g_id, g_value;
        List<Integer> nearGroupIdList;
        List<Point> groupPointList;

        public Group(int g_id, int g_value) {
            this.g_id = g_id;
            this.g_value = g_value;
            this.nearGroupIdList = new ArrayList<>();
            this.groupPointList = new ArrayList<>();
        }
    }

    static int N, ans;
    static int[][] board, tmpBoard, groupBoard, nearBoard;
    static List<Group> groupList;

    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        tmpBoard = new int[N][N];
        groupBoard = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }

    public static void main(String[] args) throws Exception {
        input();

        // 초기 상태
//        System.out.println(":: 초기 상태 출력 ::");
//        printBoard(board);
        getScore();

        // 3번 회전
        for (int i = 0; i < 3; i++) {
//            System.out.println((i+1) + " 번째 회전");
            rotateCross();
            rotateEtc();
//            printBoard(board);
            getScore();
        }

        System.out.println(ans);
    }

    static void getScore() {
        groupList = new ArrayList<>();

        // 그룹을 짓는다.
        grouping();

        int score = 0;

        for (Group g: groupList) {
            List<Integer> nearGroupIdxList = g.nearGroupIdList;

            for (int idx: nearGroupIdxList) {
                Group vg = groupList.get(idx);

//                (그룹 a에 속한 칸의 수 + 그룹 b에 속한 칸의 수) * 그룹 a 숫자값 * 그룹 b 숫자값 * 그룹 a와 b가 맞닿아 있는 변의 수
                score += (g.groupPointList.size() + vg.groupPointList.size()) * g.g_value * vg.g_value * nearBoard[g.g_id][vg.g_id];
//                System.out.println((g.groupPointList.size() + vg.groupPointList.size()) * g.g_value * vg.g_value * nearBoard[g.g_id][vg.g_id]);
            }
        }

        score /= 2;

        ans += score;
    }

    static void grouping() {
        boolean[][] g_visited = new boolean[N][N];
        int g_id = 0;

        // 그룹 정보 생성
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int value = board[i][j];

                if (!g_visited[i][j]) {
                    Group group = new Group(g_id, value);
                    groupList.add(group);

                    BFS(i, j, g_visited, group);

                    g_visited[i][j] = true;
                    g_id++;
                }
            }
        }
        nearBoard = new int[groupList.size()][groupList.size()];
//        printGroupStatus();
//
//        System.out.println(":: Group Board STATUS :: ");
//        printBoard(groupBoard);

        // 인접 그룹 정보 추가
        for (Group g: groupList) {
            getNearGroup(g);
        }
        // nearBoard 보정
        for (int i = 0; i < nearBoard.length; i++) {
            for (int j = 0; j < nearBoard[i].length; j++) {
                if (nearBoard[i][j] != 0) nearBoard[i][j] = nearBoard[i][j] / 2;
            }
        }

//        printGroupStatus();
//        printBoard(nearBoard);

    }
    static void getNearGroup(Group g) {
        List<Point> pointList = g.groupPointList;

        for (Point p: pointList) {
            for (int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if (isNotBoundary(nx, ny)) continue;
                if (groupBoard[nx][ny] != g.g_id) {
                    if (!g.nearGroupIdList.contains(groupBoard[nx][ny])) {
                        g.nearGroupIdList.add(groupBoard[nx][ny]);
                    }

                    nearBoard[g.g_id][groupBoard[nx][ny]]++;
                    nearBoard[groupBoard[nx][ny]][g.g_id]++;
                }
            }
        }
    }

    static void BFS(int x, int y, boolean[][] g_visited, Group group) {
        Queue<Point> q = new LinkedList<>();

        Point point = new Point(x, y);
        q.add(point);
        g_visited[x][y] = true;
        group.groupPointList.add(point);
        groupBoard[x][y] = group.g_id;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            int cx = cur.x;
            int cy = cur.y;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isNotBoundary(nx, ny)) continue;
                if (g_visited[nx][ny]) continue;
                if (board[nx][ny] != group.g_value) continue;

                Point nPoint = new Point(nx, ny);

                q.add(nPoint);
                g_visited[nx][ny] = true;

                groupBoard[nx][ny] = group.g_id;
                groupList.get(group.g_id).groupPointList.add(nPoint);
            }
        }
    }
    static boolean isNotBoundary(int x, int y) {
        return !(0 <= x && x < N && 0 <= y && y < N);
    }

    static void rotateCross() {
//        System.out.println("1. 십자가 회전");
        copyBoard(tmpBoard, board);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 세로일 경우
                if (j == N / 2) tmpBoard[j][i] = board[i][j];
                // 가로일 경우
                if (i == N / 2) tmpBoard[N - j - 1][i] = board[i][j];
            }
        }

        copyBoard(board, tmpBoard);
    }

    static void rotateEtc() {
//        System.out.println("2. 나머지 사각형들 회전");

        copyBoard(tmpBoard, board);

        // 1
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                tmpBoard[i][j] = board[N / 2 - 1 - j][i];
            }
        }

        // 2
        for (int i = 0; i < N / 2; i++) {
            for (int j = N / 2 + 1; j < N; j++) {
                tmpBoard[i][j] = board[N - 1 - j][N / 2 + 1 + i];
            }
        }

        // 3
        for (int i = 0; i < N / 2; i++) {
            for (int j = N / 2 + 1; j < N; j++) {
                tmpBoard[N / 2 + 1 + i][N - 1 - j] = board[j][i];
            }
        }

        // 4
        for (int i = N / 2 + 1; i < N; i++) {
            for (int j = 0; j < N / 2; j++) {
                tmpBoard[i][N / 2 + 1 + j] = board[N - 1 - j][i];
            }
        }

        copyBoard(board, tmpBoard);

    }

    static void copyBoard(int[][] to, int[][] from) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                to[i][j] = from[i][j];
            }
        }
    }

    static void printGroupStatus() {
        for (Group g: groupList) {
            System.out.print("[" + g.g_id + "] 번 그룹 Info : ");
            System.out.println("g_value = " + g.g_value);

            System.out.println("<인접 그룹> ");
            for (int num: g.nearGroupIdList) {
                System.out.print(num + "번 ");
            }
            System.out.println();

            System.out.println(": Point List : ");
            for (Point p: g.groupPointList) {
                System.out.print("(" + p.x + ", " + p.y + ") ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printBoard(int[][] board) {
        System.out.println(":: BOARD STATUS ::");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printBoard(boolean[][] c) {
        System.out.println(":: 십자 모양 확인 ::");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (c[i][j]) System.out.print(1 + "\t");
                else System.out.print(0 + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
