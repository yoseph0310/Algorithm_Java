package CodeTree.삼성스터디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 점심_식사시간 {

    static int N, ans;

    static ArrayList<Person> peopleList;
    static Stair[] stairs;
    static Queue<Person>[] stairQ;

    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());

            peopleList = new ArrayList<>();
            stairs = new Stair[2];
            stairQ = new LinkedList[2];
            for (int i = 0; i < 2; i++) {
                stairQ[i] = new LinkedList<>();
            }
            ans = Integer.MAX_VALUE;

            int stairIdx = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());

                    if (num == 0) continue;

                    // 사람이면
                    else if (num == 1) {
                        peopleList.add(new Person(i, j));
                    }

                    // 계단이면 (계단은 2개)
                    else {
                        stairs[stairIdx] = new Stair(i, j, num);
                        stairIdx++;
                    }
                }
            }

            move(0);

            System.out.println("#" + t + " " + ans);
        }
    }

    static void move(int idx) {
        // 모든 사람의 목적 계단이 정해지면
        if (idx == peopleList.size()) {
            visited = new boolean[peopleList.size()];

            int moveCnt = goDown();

            ans = ans > moveCnt ? moveCnt : ans;

            return;
        }

        // 0 계단으로 가기
        peopleList.get(idx).stair = 0;
        peopleList.get(idx).getArriveTime();
        move(idx + 1);

        // 1 계단으로 가기
        peopleList.get(idx).stair = 1;
        peopleList.get(idx).getArriveTime();
        move(idx + 1);
    }

    static int goDown() {
        int cnt = 0;
        int time = 1;

        while (true) {
            for (Queue<Person> q: stairQ) {
                int size = q.size();

                for (int i = 0; i < size; i++) {
                    Person p = q.poll();
                    Stair s = stairs[p.stair];

                    if (p.stairTime + s.k <= time) continue;

                    q.add(p);
                }

            }

            // 모든 사람이 내려갔다면 time 반환
            if (cnt == peopleList.size() && stairQ[0].isEmpty() && stairQ[1].isEmpty()) return time;

            for (int i = 0; i < peopleList.size(); i++) {
                if (visited[i]) continue;

                Person p = peopleList.get(i);
                Queue<Person> q = stairQ[p.stair];

                if (p.arriveTime + 1 <= time && q.size() < 3) {
                    visited[i] = true;
                    q.add(p);
                    p.stairTime = time;
                    cnt++;
                }
            }
            time++;
        }
    }

    static class Person {
        int x, y, stair;
        int arriveTime;
        int stairTime;

        public Person(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private void getArriveTime() {
            this.arriveTime = Math.abs(x - stairs[stair].x) + Math.abs(y - stairs[stair].y);
        }
    }

    static class Stair {
        int x, y, k;

        public Stair(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }
}
