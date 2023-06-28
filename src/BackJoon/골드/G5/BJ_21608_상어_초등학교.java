package BackJoon.골드.G5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_21608_상어_초등학교 {

    static int N, sum;
    static int[] students;
    static int[][] map;
    static Map<Integer, Set<Integer>> preferences;

    // 상 우 하 좌
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        students = new int[N * N];
        map = new int[N][N];
        preferences = new HashMap<>();

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());

            int student = Integer.parseInt(st.nextToken());
            students[i] = student;
            preferences.put(students[i], new HashSet<>());

            for (int j = 0; j < 4; j++) {
                preferences.get(students[i]).add(Integer.parseInt(st.nextToken()));
            }
        }

        start();

    }

    static void start() {
        // 학생 자리 배치
        for (int i = 0; i < students.length; i++) {
            Seat seat = findSeat(students[i]);
            map[seat.x][seat.y] = students[i];
        }

        // 점수 합산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = getStudentSum(i, j, map[i][j]);
                if (cnt > 0) sum += (int) Math.pow(10, cnt - 1);
            }
        }

        System.out.println(sum);
    }

    static Seat findSeat(int student) {
        Seat seat = null;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0) continue;

                Seat cur = new Seat(i, j, getStudentSum(i, j, student), getEmptySum(i, j));

                if (seat == null) {
                    seat = cur;
                    continue;
                }

                if (seat.compareTo(cur) > 0) {
                    seat = cur;
                }
            }
        }

        return seat;
    }

    static int getStudentSum(int x, int y, int student) {
        int cnt = 0;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isNotBoundary(nx, ny)) continue;

            if (preferences.get(student).contains(map[nx][ny])) cnt++;
        }

        return cnt;
    }

    static int getEmptySum(int x, int y) {
        int cnt = 0;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isNotBoundary(nx, ny)) continue;

            if (map[nx][ny] == 0) cnt++;

        }

        return cnt;
    }

    static boolean isNotBoundary(int x, int y) {
        return !(0 <= x && x < N && 0 <= y && y < N);
    }

    static class Seat implements Comparable<Seat> {
        int x, y, studentSum, emptySum;

        public Seat(int x, int y, int studentSum, int emptySum) {
            this.x = x;
            this.y = y;
            this.studentSum = studentSum;
            this.emptySum = emptySum;
        }

        @Override
        public int compareTo(Seat o) {
            if (studentSum != o.studentSum) return o.studentSum - studentSum;
            if (emptySum != o.emptySum) return o.emptySum - emptySum;
            if (x != o.x) return x - o.x;
            return y - o.y;
        }
    }
}
