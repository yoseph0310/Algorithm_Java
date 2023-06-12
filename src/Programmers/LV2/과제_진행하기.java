package Programmers.LV2;
import java.util.*;

public class 과제_진행하기 {
    public List<String> solution(String[][] plans) {
        List<String> answer = new ArrayList<>();

        PriorityQueue<Subject> pq = new PriorityQueue<>(
                (o1, o2) -> (o1.start - o2.start)
        );

        for (int i = 0; i < plans.length; i++) {
            String name = plans[i][0];

            String[] sT = plans[i][1].split(":");
            int h = Integer.parseInt(sT[0]) * 60;
            int m = Integer.parseInt(sT[1]);
            int start = h + m;

            int time = Integer.parseInt(plans[i][2]);

            pq.add(new Subject(name, start, time));
        }

        // 멈춘 과제 저장할 스택
        Stack<Subject> stopStack = new Stack<>();

        while (!pq.isEmpty()) {
            Subject curSubject = pq.poll();

            String curName = curSubject.name;
            int curStart = curSubject.start;
            int curPlaytime = curSubject.playtime;

            // 현재 시각
            int curTime = curStart;

            // 새로운 과제가 남아있는 경우 (진행중인 과제 제외)
            if (!pq.isEmpty()) {
                Subject nextSubject = pq.peek();

                // 지금 과제를 끝내도 다음 과제까지 시간이 남는 경우
                if (curTime + curPlaytime < nextSubject.start) {
                    answer.add(curName);
                    curTime += curPlaytime;

                    // 잠시 멈춘 과제가 남아있다면 남는 시간동안 해결
                    while(!stopStack.isEmpty()) {
                        Subject stopSubject = stopStack.pop();

                        // 다음 과제까지 다 끝낼 수 있는 경우
                        if (curTime + stopSubject.playtime <= nextSubject.start) {
                            curTime += stopSubject.playtime;
                            answer.add(stopSubject.name);
                            continue;
                        }
                        // 못 끝내는 경우
                        else {
                            int t = stopSubject.playtime - (nextSubject.start - curTime);

                            stopStack.push(new Subject(stopSubject.name, t));
                            break;
                        }
                    }

                }
                // 지금 과제 끝내면 새 과제 시간인 경우
                else if (curStart + curPlaytime == nextSubject.start) {
                    answer.add(curName);
                    continue;
                }
                // 새 과제 시작 전까지 지금 과제 못끝내는 경우
                else {
                    int t = nextSubject.start - curTime;
                    stopStack.push(new Subject(curName, curPlaytime - t));
                }
            }

            // 더 이상 남은 새 과제가 없으면
            else {
                // 남아있는 과제도 없는 경우
                if (stopStack.isEmpty()) {
                    curTime += curPlaytime;
                    answer.add(curName);
                }
                // 남아있는 과제는 있는 경우
                else {
                    answer.add(curName);

                    while (!stopStack.isEmpty()) {
                        Subject stopSub = stopStack.pop();
                        answer.add(stopSub.name);
                    }
                }
            }
        }

        return answer;
    }

    class Subject {
        String name;
        int start, playtime;

        public Subject(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }

        public Subject(String name, int playtime) {
            this.name = name;
            this.playtime = playtime;
        }
    }
}
